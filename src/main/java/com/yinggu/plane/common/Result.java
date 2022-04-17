package com.yinggu.plane.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/6 19:21
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;

    private String msg;

    private Object data;

    public static Result success(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(Object data){
        return success(200,"操作成功",data);
    }

    public static Result fail(int code, String msg, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg, Object data){
        return fail(400, msg, data);
    }

    public static Result fail(String msg){
        return fail(400, msg, null);
    }
}
