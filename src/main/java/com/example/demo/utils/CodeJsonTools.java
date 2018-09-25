package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;

public class CodeJsonTools {
    public static JSONObject success(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success!");
        return jsonObject;
    }
    public static String success(Object data){
        JSONObject jsonObject = success();
        jsonObject.put("data",data);
        return jsonObject.toString();
    }
}
