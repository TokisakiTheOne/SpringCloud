package com.yyh.application.handler;

import javafx.util.Builder;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 自定义异常处理类
 * SpringBoot 提供默认的异常处理类
 * 所以重写此类  返回统一的json格式
 *
 * @author YanYuHang
 * @create 2019-04-08-14:34
 */
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {
    public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /*获取异常属性*/
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        int code = 500;
        Throwable error = super.getError(request);
        if (error instanceof NotFoundException) {
            code = 404;
        }
        return response(code, this.buidMessage(request, error));
    }

    /*指定响应处理的方法*/
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /*根据code获取对应的HttpStatus*/
    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int) errorAttributes.get("code");
        return HttpStatus.valueOf(statusCode);
    }

    /*构建异常信息*/
    private String buidMessage(ServerRequest request, Throwable ex) {
        StringBuffer sb = new StringBuffer("Failed to handle request[");
        sb.append(request.methodName());
        sb.append(" ");
        sb.append("]");
        if (ex != null) {
            sb.append(": ");
            sb.append(ex.getMessage());
        }
        return sb.toString();
    }

    /*构建json的数据格式*/
    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status);
        map.put("message", errorMessage);
        map.put("data", null);
        return map;
    }
}
