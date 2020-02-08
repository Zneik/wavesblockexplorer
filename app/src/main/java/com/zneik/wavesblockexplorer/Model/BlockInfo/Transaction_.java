package com.zneik.wavesblockexplorer.Model.BlockInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction_ {

    @SerializedName("senderPublicKey")
    @Expose
    private String senderPublicKey;

    @SerializedName("call")
    @Expose
    private Call call;

    @SerializedName("dApp")
    @Expose
    private String dApp;

    @SerializedName("sender")
    @Expose
    private String sender;

    @SerializedName("feeAssetId")
    @Expose
    private Object feeAssetId;

    @SerializedName("proofs")
    @Expose
    private List<String> proofs = null;

    @SerializedName("fee")
    @Expose
    private Integer fee;

    @SerializedName("payment")
    @Expose
    private List<Payment> payment = null;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("type")
    @Expose
    private Integer type;

    @SerializedName("version")
    @Expose
    private Integer version;

    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    public String getSenderPublicKey() {
        return senderPublicKey;
    }

    public void setSenderPublicKey(String senderPublicKey) {
        this.senderPublicKey = senderPublicKey;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public String getDApp() {
        return dApp;
    }

    public void setDApp(String dApp) {
        this.dApp = dApp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Object getFeeAssetId() {
        return feeAssetId;
    }

    public void setFeeAssetId(Object feeAssetId) {
        this.feeAssetId = feeAssetId;
    }

    public List<String> getProofs() {
        return proofs;
    }

    public void setProofs(List<String> proofs) {
        this.proofs = proofs;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
