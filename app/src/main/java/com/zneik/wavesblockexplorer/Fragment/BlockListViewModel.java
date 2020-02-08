package com.zneik.wavesblockexplorer.Fragment;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zneik.wavesblockexplorer.Model.BlockHeight;
import com.zneik.wavesblockexplorer.Model.Header;
import com.zneik.wavesblockexplorer.NetworkService.BlockAPI;
import com.zneik.wavesblockexplorer.NetworkService.BlockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BlockListViewModel extends ViewModel {
    private static final Integer LOAD_BLOCKS_COUNT = 100;

    private CompositeDisposable compositeDisposable;
    private MutableLiveData<BlockHeight> lastBlockHeight;
    private BlockAPI blockAPI;
    private MutableLiveData<List<Header>> headersList;

    public BlockListViewModel() {
        this.lastBlockHeight = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();
        this.headersList = new MutableLiveData<>();
        this.headersList.setValue(new ArrayList<>());

        this.blockAPI = BlockService.getInstance()
                .getBlockAPI();

        updateLastBlockHeight();
//        loadBlocksLast();
    }

    @SuppressLint("CheckResult")
    public void updateLastBlockHeight() {

        Observable
//                .interval(0, 3, TimeUnit.SECONDS)
                .timer(5, TimeUnit.SECONDS)
                .flatMap(emiter -> {
                    return this.blockAPI.getBlockHeight();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BlockHeight>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BlockHeight blockHeight) {
                        lastBlockHeight.setValue(blockHeight);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

//        this.blockAPI.getBlockHeight()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError(throwable -> Log.i("TTT", Objects.requireNonNull(throwable.getMessage())))
//                .subscribe(new Observer<BlockHeight>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BlockHeight blockHeight) {
//                        lastBlockHeight.setValue(blockHeight);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
    }

    @SuppressLint("CheckResult")
    public void loadBlocksLast() {
        final int from = this.getLastBlockHeight().getValue().getHeight() - 10;
        final int to = this.getLastBlockHeight().getValue().getHeight();
        Log.i("TTT","from " + String.valueOf(from));
        Log.i("TTT", "to " + String.valueOf(to));
        this.blockAPI.getHeaders(from, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.d("TTT", throwable.getMessage()))
                .subscribe(new Observer<List<Header>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Header> headers) {
                        Log.i("TTT", String.valueOf(headers.size()));
                        List<Header> newHeaders = headersList.getValue();
                        newHeaders.addAll(headers);
                        headersList.setValue(newHeaders);
                        Log.i("TTT", String.valueOf(headersList.getValue().size()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

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
