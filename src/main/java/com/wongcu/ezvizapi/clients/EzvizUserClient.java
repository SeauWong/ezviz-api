package com.wongcu.ezvizapi.clients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wongcu.ezvizapi.common.HttpClientResult;
import com.wongcu.ezvizapi.common.Token;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
@Slf4j
public class EzvizUserClient {

    /**
     * 请求地址 https://open.ys7.com/api/lapp/token/get
     *
     * @param host      主机名
     * @param appKey
     * @param appSecret
     * @return
     * @throws IOException
     */
    public YSResult<Token> getAccessToken(String host, String appKey, String appSecret) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("appKey", appKey);
        params.put("appSecret", appSecret);
        final String url = host + "/api/lapp/token/get";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult<Token> ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult<Token>>() {
        });
        return ysResult;
    }
}
