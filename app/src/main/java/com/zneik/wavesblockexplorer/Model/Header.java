package com.zneik.wavesblockexplorer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header implements Comparable<Header> {

    @SerializedName("blocksize")
    @Expose
    private Integer blocksize;

    @SerializedName("reward")
    @Expose
    private Integer reward;

    @SerializedName("signature")
    @Expose
    private String signature;

    @SerializedName("generator")
    @Expose
    private String generator;

    @SerializedName("version")
    @Expose
    private Integer version;

    @SerializedName("references")
    @Expose
    private String references;

    //private List<> features;

    @SerializedName("totalFee")
    @Expose
    private Integer totalFee;

    @SerializedName("nxt-consensus")
    @Expose
    private NXTConsensus nxtConsensus;

    @SerializedName("desiredReward")
    @Expose
    private Integer desireReward;

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

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public NXTConsensus getNxtConsensus() {
        return nxtConsensus;
    }

    public void setNxtConsensus(NXTConsensus nxtConsensus) {
        this.nxtConsensus = nxtConsensus;
    }

    public Integer getDesireReward() {
        return desireReward;
    }

    public void setDesireReward(Integer desireReward) {
        this.desireReward = desireReward;
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

    @Override
    public int compareTo(Header o) {
        return this.getHeight().compareTo(o.getHeight());
    }
}
