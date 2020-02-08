package com.zneik.wavesblockexplorer.Model.BlockInfo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Call {

    @SerializedName("function")
    @Expose
    private String function;

    @SerializedName("args")
    @Expose
    private List<Arg> args = null;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public List<Arg> getArgs() {
        return args;
    }

    public void setArgs(List<Arg> args) {
        this.args = args;
    }

}
