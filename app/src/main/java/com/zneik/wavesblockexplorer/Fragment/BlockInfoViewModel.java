package com.zneik.wavesblockexplorer.Fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zneik.wavesblockexplorer.Model.BlockInfo.Transaction;
import com.zneik.wavesblockexplorer.NetworkService.BlockAPI;
import com.zneik.wavesblockexplorer.NetworkService.BlockService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BlockInfoViewModel extends ViewModel {
    private MutableLiveData<Integer> height;
    private MutableLiveData<Transaction> blockTransaction;
    private BlockAPI blockAPI;
    private CompositeDisposable compositeDisposable;

    public BlockInfoViewModel() {
        height = new MutableLiveData<>();
        blockTransaction = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        blockAPI = BlockService.getInstance()
                .getBlockAPI();
    }

    public void loadBlockInfo() {
        compositeDisposable.add(
                this.blockAPI.getHeightAt(this.height.getValue())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Transaction>() {
                            @Override
                            public void onSuccess(Transaction transaction) {
                                blockTransaction.setValue(transaction);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        })
        );


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

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
