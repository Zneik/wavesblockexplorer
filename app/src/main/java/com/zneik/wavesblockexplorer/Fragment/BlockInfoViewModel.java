package com.zneik.wavesblockexplorer.Fragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zneik.wavesblockexplorer.Model.BlockInfo.Transaction;
import com.zneik.wavesblockexplorer.NetworkService.BlockAPI;
import com.zneik.wavesblockexplorer.NetworkService.BlockService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BlockInfoViewModel extends ViewModel {
    private MutableLiveData<Integer> height;
    private MutableLiveData<Transaction> blockTransaction;
    private BlockAPI blockAPI;

    public BlockInfoViewModel() {
        height = new MutableLiveData<>();
        blockAPI = BlockService.getInstance()
                .getBlockAPI();
    }

    public void loadBlockInfo() {
        this.blockAPI.getHeightAt(this.height.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(th -> Log.i("TTT", th.getMessage()))
                .subscribe(new Observer<Transaction>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Transaction transaction) {
                        blockTransaction.setValue(transaction);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public MutableLiveData<Integer> getHeight() {
        return height;
    }

    public void setHeight(MutableLiveData<Integer> height) {
        this.height = height;
    }

    public MutableLiveData<Transaction> getBlockTransaction() {
        return blockTransaction;
    }

    public void setBlockTransaction(MutableLiveData<Transaction> blockTransaction) {
        this.blockTransaction = blockTransaction;
    }
}
