package com.zgss.grib.response;

public final class ResultUtil {

    public static ApiResult build(ResultEnum resultEnum, Object data) {
        ApiResult<Object> result = new ApiResult();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        result.setData(data);
        return result;
    }

    public static ApiResult build(ResultEnum resultEnum) {
        ApiResult<Object> result = new ApiResult();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        return result;
    }

    public static ApiResult success(String message) {
        ApiResult<Object> result = new ApiResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(message);
        return result;
    }

    public static ApiResult error(String message) {
        ApiResult<Object> result = new ApiResult();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMessage(message);
        return result;
    }
}
