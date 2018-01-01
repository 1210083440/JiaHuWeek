package com.bw.jiahuweek1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.jiahuweek1.activity.SecondActivity;
import com.bw.jiahuweek1.application.MyApplication;
import com.bw.jiahuweek1.bean.DataBean;
import com.bw.jiahuweek1.bean.DataBeanDao;
import com.bw.jiahuweek1.bean.DishBean;
import com.bw.jiahuweek1.httpservice.DishHttpService;
import com.bw.jiahuweek1.retrofit.MyConverterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

// http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
// 实现RecyclerView点击事件
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
     RecyclerView mRecyclerView;
    private DishAdapter mAdapter;
    private DataBeanDao mDataBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    mAdapter = new DishAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // 数据库
        mDataBeanDao = MyApplication.getsDaoSession()
                .getDataBeanDao();

        initData();
}

    // RecyclerView重item的点击事件
    private void onItemClick(int position, DataBean data) {
        // 使用greenDAO将被点击item数据的pic存入数据库
        // 设置主键
        data.set_id(System.currentTimeMillis());
        mDataBeanDao.insert(data);

        Toast.makeText(MainActivity.this, "pos: "+position, Toast.LENGTH_SHORT).show();
    }

    // RecyclerView中item的长点击事件
    private void onItemLongClick(int position, DataBean data) {
        // 跳转新界面，并传递pic地址
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("pic", data.getPic());
        startActivity(intent);

//        Toast.makeText(MainActivity.this, "pos: "+position, Toast.LENGTH_LONG).show();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.qubaobei.com/")
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new MyConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Observable<DishBean> observable = retrofit.create(DishHttpService.class)
                .getObservable(1);

        observable.subscribeOn(Schedulers.io())
                .map(new Function<DishBean, List<DataBean>>() {
                    @Override
                    public List<DataBean> apply(DishBean dishBean) throws Exception {
                        return dishBean.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataBean>>() {
                    @Override
                    public void accept(List<DataBean> dataBeans) throws Exception {
                        Toast.makeText(MainActivity.this, "size:"+dataBeans.size(), Toast.LENGTH_SHORT).show();
                        mAdapter.addDatas(dataBeans);
                    }
                });
    }

    // 为了应付考试，内部类我不加static修饰了
    class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> implements
            View.OnClickListener, View.OnLongClickListener {

        private Context mContext;
        private List<DataBean> mDatas;
        private RecyclerView mParentRecyclerView;

        public DishAdapter(Context mContext) {
            this.mContext = mContext;
            mDatas = new ArrayList<>();
        }

        // 添加数据
        public void addDatas(List<DataBean> datas) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_dish, parent, false);
            // 获取itemView的父容器，就是RecyclerView。通过父容器可以获取被点击item的位置
            mParentRecyclerView = ((RecyclerView) parent);
            // 给item绑定监听器
            initItemListener(itemView);
            return new ViewHolder(itemView);
        }

        // 给item添加点击事件和长点击事件
        private void initItemListener(View itemView) {
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            DataBean data = mDatas.get(position);

            holder.mTitleTextView.setText(data.getTitle());

            Glide.with(mContext)
                    .load(data.getPic())
                    .into(holder.mPicImageView);
        }

        @Override
        public int getItemCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

        @Override
        public void onClick(View view) {
            // 获取被点击itemView的位置
            int position = mParentRecyclerView.getChildLayoutPosition(view);
            onItemClick(position, mDatas.get(position));
        }

        @Override
        public boolean onLongClick(View view) {
            int position = mParentRecyclerView.getChildLayoutPosition(view);
            onItemLongClick(position, mDatas.get(position));
            return true;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.title_tv)
            TextView mTitleTextView;
            @BindView(R.id.pic_iv)
            ImageView mPicImageView;

            public ViewHolder(View itemView) {
                super(itemView);

                // 在adapter或fragment重，一定要选择2个参数的，第一个参数通常this
                ButterKnife.bind(this, itemView);
            }
        }

    }

}
