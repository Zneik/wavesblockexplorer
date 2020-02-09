package com.zneik.wavesblockexplorer.Fragment;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zneik.wavesblockexplorer.Model.BlockHeight;
import com.zneik.wavesblockexplorer.Model.BlockInfo.Transaction;
import com.zneik.wavesblockexplorer.Model.Header;
import com.zneik.wavesblockexplorer.NetworkService.BlockAPI;
import com.zneik.wavesblockexplorer.NetworkService.BlockService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.observers.DisposableSingleObserver;

public class BlockListViewModel extends ViewModel {
    private static final Integer LOAD_BLOCKS_COUNT = 100;
    public static final int DOWNLOAD_INTERVAL = 10;

    private CompositeDisposable compositeDisposable;
    private MutableLiveData<BlockHeight> lastBlockHeight;
    private BlockAPI blockAPI;
    private MutableLiveData<List<Header>> headersList;
    private MutableLiveData<Integer> downloadPosition;

    public BlockListViewModel() {
        this.lastBlockHeight = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();
        this.headersList = new MutableLiveData<>();
        this.headersList.setValue(new ArrayList<>());
        this.downloadPosition = new MutableLiveData<>();
        this.downloadPosition.setValue(0);

        this.blockAPI = BlockService.getInstance()
                .getBlockAPI();

        updateLastBlockHeight();
    }

    @SuppressLint("CheckResult")
    public void updateLastBlockHeight() {

        Single<BlockHeight> single = this.blockAPI.getBlockHeight();

        compositeDisposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BlockHeight>() {
                    @Override
                    public void onSuccess(BlockHeight blockHeight) {
                        lastBlockHeight.setValue(blockHeight);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @SuppressLint("CheckResult")
    public void loadBlocks() {

        Integer from;
        Integer to;

        from = this.getLastBlockHeight().getValue().getHeight()
                - this.downloadPosition.getValue() - DOWNLOAD_INTERVAL;
        to = this.getLastBlockHeight().getValue().getHeight() - this.downloadPosition.getValue();


        this.downloadPosition.setValue(
                this.downloadPosition.getValue() + DOWNLOAD_INTERVAL  + 1
        );

//        Log.i("TTT", "interval " + this.downloadPosition.getValue()+ "from " + from + " to " + to);
        compositeDisposable.add(
                this.blockAPI.getHeaders(from, to)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Header>>() {
                                           @Override
                                           public void onSuccess(List<Header> headers) {
                                               Collections.reverse(headers);
                                               headersList.setValue(headers);
                                           }

                                           @Override
                                           public void onError(Throwable e) {

                                           }
                                       }
                        ));

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public MutableLiveData<List<Header>> getHeadersList() {
        return headersList;
    }

    public void setHeadersList(MutableLiveData<List<Header>> headersList) {
        this.headersList = headersList;
    }

    public MutableLiveData<BlockHeight> getLastBlockHeight() {
        return lastBlockHeight;
    }

    public void setLastBlockHeight(MutableLiveData<BlockHeight> lastBlockHeight) {
        this.lastBlockHeight = lastBlockHeight;
    }
}
