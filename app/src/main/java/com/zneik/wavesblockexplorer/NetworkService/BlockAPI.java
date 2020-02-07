package com.zneik.wavesblockexplorer.NetworkService;

import com.zneik.wavesblockexplorer.Model.BlockHeight;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BlockAPI {

    @GET("/blocks/height")
    public Observable<BlockHeight> getBlockHeight();

}
