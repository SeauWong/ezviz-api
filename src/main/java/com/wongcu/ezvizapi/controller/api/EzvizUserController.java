package com.wongcu.ezvizapi.controller.api;

import com.wongcu.ezvizapi.clients.EzvizUserClient;
import com.wongcu.ezvizapi.common.Token;
import com.wongcu.ezvizapi.common.YSResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wongcu
 * @version 2018/11/2 13:43
 * @since 2018/11/2
 */
@RestController
@RequestMapping("/ezviz/users")
@Slf4j
public class EzvizUserController {

    @Value("${ys.host}")
    private String ysHost;
    @Autowired
    private EzvizUserClient userClient;

    @GetMapping("/token")
    public YSResult<Token> getAccessToken(String appKey, String appSecret) {
        try {
            return userClient.getAccessToken(ysHost, appKey, appSecret);
        } catch (Exception e) {
            log.error("获取accessToken发生异常", e);
            return null;
        }
    }
}
