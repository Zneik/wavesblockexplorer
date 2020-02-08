package com.zneik.wavesblockexplorer.Model.BlockInfo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("blocksize")
    @Expose
    private Integer blocksize;

    @SerializedName("reward")
    @Expose
    private Integer reward;

    @SerializedName("signature")
    @Expose
    private String signature;

    @SerializedName("fee")
    @Expose
    private Integer fee;

    @SerializedName("generator")
    @Expose
    private String generator;

    @SerializedName("transactions")
    @Expose
    private List<Transaction_> transactions = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    @SerializedName("reference")
    @Expose
    private String reference;

    @SerializedName("features")
    @Expose
    private List<Object> features = null;

    @SerializedName("totalFee")
    @Expose
    private Integer totalFee;

    @SerializedName("nxt-consensus")
    @Expose
    private NxtConsensus nxtConsensus;

    @SerializedName("desiredReward")
    @Expose
    private Integer desiredReward;

    @SerializedName("transactionCount")
    @Expose
    private Integer transactionCount;

    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(Integer blocksize) {
        this.blocksize = blocksize;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<Transaction_> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction_> transactions) {
        this.transactions = transactions;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Object> getFeatures() {
        return features;
    }

    public void setFeatures(List<Object> features) {
        this.features = features;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public NxtConsensus getNxtConsensus() {
        return nxtConsensus;
    }

    public void setNxtConsensus(NxtConsensus nxtConsensus) {
        this.nxtConsensus = nxtConsensus;
    }

    public Integer getDesiredReward() {
        return desiredReward;
    }

    public void setDesiredReward(Integer desiredReward) {
        this.desiredReward = desiredReward;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
