package com.zneik.wavesblockexplorer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlockHeight {

    @SerializedName("height")
    @Expose
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
