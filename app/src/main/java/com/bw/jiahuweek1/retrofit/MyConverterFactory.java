package com.bw.jiahuweek1.retrofit;

import android.support.annotation.Nullable;

import com.bw.jiahuweek1.bean.DishBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 自定义Json解析器
 */
public class MyConverterFactory extends Converter.Factory {

    // 重写响应体，从服务器返回的响应数据其实就是json字符串，我们需要将json字符串解析得到数据模型类bean
    @Nullable
    @Override
    public Converter<ResponseBody, DishBean> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new Converter<ResponseBody, DishBean>() {

//            将数据的转换过程写在这里，就是json解析过程
            @Override
            public DishBean convert(ResponseBody value) throws IOException {
                // 获取响应体重的原始数据，就是json字符串
                String json = value.string();
                DishBean dishBean = new Gson().fromJson(json, DishBean.class);
                return dishBean;
            }
        };
    }
}
