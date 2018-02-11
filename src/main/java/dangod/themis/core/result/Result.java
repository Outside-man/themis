package dangod.themis.core.result;

import com.alibaba.fastjson.JSON;

public class Result {
    private int status;
    private String message;
    private Object data;

    public Result setCode(ResultStatus resultStatus) {
        this.status = resultStatus.code;
        return this;
    }

    public int getCode() {
        return status;
    }

    public Result setCode(int code) {
        this.status = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Result(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    private Result(ResultStatus status, Object data) {
        this.status = status.code;
        this.data = data;
    }
    private Result(ResultStatus status, Object data, String message) {
        this.status = status.code;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static String ok(Object data) {
        return new Result(ResultStatus.SUCCESS, data).toString();
    }

    public static String ok(Object data, String message) {
        return new Result(ResultStatus.SUCCESS, data, message).toString();
    }

    public static String send(int status, Object data, String message) {
        return new Result(status, data, message).toString();
    }

    public static String error(Object data) {
        return new Result(ResultStatus.SERVER_ERROR, data).toString();
    }
    public static String error(Object data, String message) {
        return new Result(ResultStatus.SERVER_ERROR, data, message).toString();
    }
}
