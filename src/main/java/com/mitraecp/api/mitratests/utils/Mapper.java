package com.mitraecp.api.mitratests.utils;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Mapper {

    public static List<Object> convertArrayToList(String body) {
        Object[] objects = new Gson().fromJson(body, Object[].class);
        return Arrays.asList(objects);
    }
}
