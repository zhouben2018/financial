package com.zben.manager.error;

import com.zben.manager.enums.ErrorEnums;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: zben
 * @Description:自定义error
 * @Date: 16:12 2019/6/12
 */
public class MyErrorController extends BasicErrorController {

    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }


    /**
     {
     x"timestamp": "2019-06-12 16:11:06",
     x"status": 500,
     x"error": "Internal Server Error",
     x"exception": "java.lang.IllegalArgumentException",
     "message": "编号不可为空",
     x"path": "/manager/products"
     code
     canRetry
     }
     */
    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        Map<String, Object> attrs = super.getErrorAttributes(request, includeStackTrace);
        attrs.remove("timestamp");
        attrs.remove("status");
        attrs.remove("error");
        attrs.remove("exception");
        attrs.remove("path");

        String errorCode = (String) attrs.get("message");
        ErrorEnums errorEnums = ErrorEnums.getByCode(errorCode);
        attrs.put("code", errorEnums.getCode());
        attrs.put("message", errorEnums.getMessage());
        attrs.put("canRetry", errorEnums.isCanRetry());
        return attrs;
    }
}
