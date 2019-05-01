package com.yibo.springcloudconfigclient.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author: huangyibo
 * @Date: 2018/12/12 23:02
 * @Description:
 *
 * 看视频小马哥微服务Spring Cloud第一节学员写的
 */

@RestControllerAdvice(annotations = {RestController.class})
public class RestErrorHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Map<String,Object> processValidationError(WebExchangeBindException ex, ServerWebExchange exchange){
        Locale locale = exchange.getLocaleContext().getLocale();
        BindingResult result = ex.getBindingResult();
        List<FieldError> list = result.getFieldErrors();
        return processFieldErrors(locale,list);
    }

    private Map<String,Object> processFieldErrors(Locale locale,List<FieldError> list){
        Map<String,Object> map = new HashMap();
        for (FieldError fieldError : list) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(locale, fieldError);
            map.put(fieldError.getField(),localizedErrorMessage);
        }
        return map;
    }

    private String resolveLocalizedErrorMessage(Locale locale,FieldError fieldError){
        String LocalizedErrorMessage = messageSource.getMessage(fieldError, locale);
        return LocalizedErrorMessage;
    }
}
