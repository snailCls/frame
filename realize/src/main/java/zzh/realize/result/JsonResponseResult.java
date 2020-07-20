package zzh.realize.result;

/**
 * @author snail
 */
public class JsonResponseResult<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JsonResponseResult() {}

    public JsonResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static JsonResponseResult errorResult(String message) {
        return new JsonResponseResult(-1, message);
    }

    public static JsonResponseResult successMessage() {
        JsonResponseResult responseResult = new JsonResponseResult(0, "success");
        return responseResult;
    }



}
