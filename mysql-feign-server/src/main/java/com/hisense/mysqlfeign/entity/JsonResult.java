package com.hisense.mysqlfeign.entity;

import lombok.Data;

/*
    记录返回结果
   @param toatl 列表分页时的总记录数，不分页可以不返回
   @param message 成功或者失败的信息
   @param status true是成功、false是失败
   @param data 返回的数据
 */
@Data
public class JsonResult {

    private Long total;
    private String message;
    private boolean status;
    private Object data;

    public JsonResult() {


    }
    public JsonResult(String message, boolean status) {

        this.message = message;
        this.status = status;
    }

    public JsonResult(Long total, String message, boolean status) {
        this.total = total;
        this.message = message;
        this.status = status;
    }

    public JsonResult(String message, boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public JsonResult(Long total, String message, boolean status, Object data) {
        this.total = total;
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
