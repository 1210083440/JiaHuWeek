package com.bw.jiahuweek1.httpservice;

import com.bw.jiahuweek1.bean.DishBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 小小云 on 2017/12/30.
 */

public interface DishHttpService {

    @GET("ios/cf/dish_list.php?stage_id=1&limit=20")
    Observable<DishBean> getObservable(@Query("page") int page);

}
