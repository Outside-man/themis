package dangod.themis.core.result;

public enum ResultStatus {
    //基础返回码
    SUCCESS(200),//请求成功
    FAIL(400),//请求失败
    NOT_FOUND(404),//接口不存在
    SERVER_ERROR(500);//服务器内部错误

    public int code;

    ResultStatus(int code) {
        this.code = code;
    }
}
