package com.zben.manager.enums;

/**
 * @Author: zben
 * @Description:
 * @Date: 16:24 2019/6/12
 */
public enum ErrorEnums {

    ID_NOT_NULL("F001", "编号不可为空", false),
    UNKNOWN("999", "未知异常", false);

    ErrorEnums(String code, String message, boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    private String code;
    private String message;
    private boolean canRetry;

    public static ErrorEnums getByCode(String code) {
        for (ErrorEnums errorEnums : ErrorEnums.values()) {
            if (errorEnums.code.equals(code)) {
                return errorEnums;
            }
        }
        return UNKNOWN;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCanRetry() {
        return canRetry;
    }
}
