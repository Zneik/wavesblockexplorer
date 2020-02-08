package com.zneik.wavesblockexplorer.Model.BlockInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("amount")
    @Expose
    private Long amount;

    @SerializedName("assetId")
    @Expose
    private Object assetId;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Object getAssetId() {
        return assetId;
    }

    public void setAssetId(Object assetId) {
        this.assetId = assetId;
    }

}
