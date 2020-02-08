package com.zneik.wavesblockexplorer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NXTConsensus {

    @SerializedName("base-target")
    @Expose
    private Integer baseTarget;

    @SerializedName("generation-signature")
    @Expose
    private String generationSignature;

    public Integer getBaseTarget() {
        return baseTarget;
    }

    public void setBaseTarget(Integer baseTarget) {
        this.baseTarget = baseTarget;
    }

    public String getGenerationSignature() {
        return generationSignature;
    }

    public void setGenerationSignature(String generationSignature) {
        this.generationSignature = generationSignature;
    }
}
