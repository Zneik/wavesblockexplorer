package com.zneik.wavesblockexplorer.NetworkService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlockService {
    private static BlockService mInstance;
    private static final String BASE_URL = "https://nodes.wavesplatform.com";
    private Retrofit mRetrofit;

    private BlockService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static BlockService getInstance() {
        if (mInstance == null) {
            mInstance = new BlockService();
        }
        return mInstance;
    }

    public BlockAPI getBlockAPI() {
        return mRetrofit.create(BlockAPI.class);
    }

}
