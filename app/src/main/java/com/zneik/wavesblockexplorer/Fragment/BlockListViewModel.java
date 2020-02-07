package com.zneik.wavesblockexplorer.Fragment;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zneik.wavesblockexplorer.Model.BlockHeight;
import com.zneik.wavesblockexplorer.NetworkService.BlockService;

import java.util.concurrent.Callable;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlockListViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<BlockHeight> lastBlockHeght;

    public BlockListViewModel() {
        this.lastBlockHeght = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();
        updateLastBlockHeight();
    }

    @SuppressLint("CheckResult")
    public void updateLastBlockHeight() {
        BlockService.getInstance()
                .getBlockAPI()
                .getBlockHeight()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.i("TTT", throwable.getMessage()))
                .subscribe(new Observer<BlockHeight>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BlockHeight blockHeight) {
                        Log.i("TTT", String.valueOf(blockHeight.getHeight()));
                        lastBlockHeght.setValue(blockHeight);

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
}
