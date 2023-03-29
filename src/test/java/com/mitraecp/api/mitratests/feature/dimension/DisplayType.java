package com.mitraecp.api.mitratests.feature.dimension;

public enum DisplayType {
    CODE, DESCR, BOTH;

    public static DisplayType getFromString(String s){
        if(DESCR.toString().equals(s)){
            return DESCR;
        }
        if(BOTH.toString().equals(s)){
            return BOTH;
        }
        if(CODE.toString().equals(s)){
            return CODE;
        }
        return DESCR;
    }
}
