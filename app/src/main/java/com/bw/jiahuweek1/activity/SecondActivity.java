package com.bw.jiahuweek1.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bw.jiahuweek1.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {

    private String mImgUrl;

    @BindView(R.id.pic_iv)
    ImageView mPicImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);

        mImgUrl = getIntent()
                .getStringExtra("pic");

        Observable<String> observable = Observable.just(mImgUrl);
        // 先跳转子线程
        // 数据变换：将String类型的url地址想办法转换为Bitmap
        // 传回主线程
        // 展示
        observable.subscribeOn(Schedulers.io())
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String imgUrl) throws Exception {
                        Bitmap bitmap = null;

                        URL url = new URL(imgUrl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        if (connection.getResponseCode() == 200) {
                            InputStream inputStream = connection.getInputStream();
                            // 使用字节数组输出流，将来自网络的图片数据从写入内存，方便转为Bitmap
                            ByteArrayOutputStream baos = null;
                            baos = new ByteArrayOutputStream();

                            byte[] buffer = new byte[1024];
                            int len = 0;

                            while ((len = inputStream.read(buffer)) != -1) {
                                baos.write(buffer, 0, len);
                            }
                            // 当把每次读取到的网络数据都写入内存后，就可以获取全部数据
                            byte[] bytes = baos.toByteArray();

                            // 字节转换为Bitmap
                             bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                        }
                        return bitmap;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        mPicImageView.setImageBitmap(bitmap);
                    }
                });
    }
}
