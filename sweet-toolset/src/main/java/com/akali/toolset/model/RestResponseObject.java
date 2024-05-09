package com.akali.toolset.model;

import com.akali.toolset.constants.ResponseConstants;
import lombok.Data;

/**
 * Description: 响应数据不为泛型的通用响应
 *
 * @author Jian-Jun Duan
 * @date 2023/5/24 17:00
 */
@Data
public class RestResponseObject {
    private String result;
    private String message;
    private Object data;

    public RestResponseObject(String result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public RestResponseObject setData(Object data){
        this.data = data;
        return this;
    }

    public static RestResponseObject error(String message, Object data) {
        return new RestResponseObject(ResponseConstants.FAILURE, message, data);
    }

    public static RestResponseObject error( String message ) {
        return new RestResponseObject(ResponseConstants.FAILURE, message, null);
    }

    public static RestResponseObject error( Object data) {
        return new RestResponseObject(ResponseConstants.FAILURE, ResponseConstants.MSG_INTERNAL_ERROR, data);
    }

    public static RestResponseObject error() {
        return new RestResponseObject(ResponseConstants.FAILURE, ResponseConstants.MSG_INTERNAL_ERROR, null);
    }

    public static RestResponseObject ok() {
        return new RestResponseObject(ResponseConstants.SUCCESS, ResponseConstants.MSG_QUERY_OK, null);
    }

    public static RestResponseObject ok(String message) {
        return new RestResponseObject(ResponseConstants.SUCCESS, message, null);
    }

    public static RestResponseObject ok(Object data) {
        return new RestResponseObject(ResponseConstants.SUCCESS, ResponseConstants.MSG_QUERY_OK, data);
    }

    public static RestResponseObject ok(String message, Object data) { return new RestResponseObject(ResponseConstants.SUCCESS, message, data); }
}
