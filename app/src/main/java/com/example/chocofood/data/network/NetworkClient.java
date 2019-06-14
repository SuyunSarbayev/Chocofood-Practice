package com.example.chocofood.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkClient {

    private static NetworkClient sInstance;

    private Retrofit mRetrofit;

    private NetworkClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static NetworkClient getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkClient();
        }
        return sInstance;
    }

    public NetworkApi getNetworkApi() {
        return mRetrofit.create(NetworkApi.class);
    }

}
