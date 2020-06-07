package com.smec.tam;

public class TamInfoModel {

    Boolean rec_res;
    String whyTam;

    public TamInfoModel(Boolean rec_res, String whyTam) {
        this.rec_res = rec_res;
        this.whyTam = whyTam;
    }

    public TamInfoModel() {
    }

    public Boolean getRec_res() {
        return rec_res;
    }

    public String getWhyTam() {
        return whyTam;
    }
}
