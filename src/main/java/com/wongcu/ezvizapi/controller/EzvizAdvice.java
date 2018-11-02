package com.wongcu.ezvizapi.controller;

import com.wongcu.ezvizapi.common.YSResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author wongcu
 * @version 2018/11/2 13:51
 * @since 2018/11/2
 */
@ControllerAdvice
@Slf4j
public class EzvizAdvice {

    public YSResult exceptionHandler(Exception e) {
        log.error("api调用发生异常", e);
        YSResult ysResult = new YSResult();
        ysResult.setCode("2");
        ysResult.setMsg(e.getMessage());
        return ysResult;
    }
}
