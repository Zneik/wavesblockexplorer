package com.zneik.wavesblockexplorer.NetworkService;

import com.zneik.wavesblockexplorer.Model.BlockHeight;
import com.zneik.wavesblockexplorer.Model.Header;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BlockAPI {

    @GET("/blocks/height")
    public Observable<BlockHeight> getBlockHeight();

    @GET("/blocks/headers/seq/{from}/{to}")
    public Observable<List<Header>> getHeaders(@Path("from") Integer from, @Path("to") Integer to);

}
