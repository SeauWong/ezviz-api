package com.wongcu.ezvizapi.clients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wongcu.ezvizapi.common.HttpClientResult;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.common.live.LiveAddress;
import com.wongcu.ezvizapi.common.live.LiveOpenResult;
import com.wongcu.ezvizapi.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wongcu
 * @version 2018/11/2 13:34
 * @since 2018/11/2
 */
@Component
@Slf4j
public class EzvizLiveClient {

    /**
     * 开通直播功能
     *
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param source      直播源，[设备序列号]:[通道号],[设备序列号]:[通道号]的形式，例如427734222:1,423344555:3，均采用英文符号
     * @return
     * @throws IOException
     */
    public YSResult<List<LiveOpenResult>> openLive(String host, String accessToken, String source) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("source", source);
        final String url = host + "/api/lapp/live/video/open";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult<List<LiveOpenResult>>>() {
        });
        return ysResult;
    }

    /**
     * 获取直播地址
     *
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param source      直播源，[设备序列号]:[通道号],[设备序列号]:[通道号]的形式，例如427734222:1,423344555:3，均采用英文符号
     * @return
     */
    public YSResult<List<LiveAddress>> getLiveAddress(String host, String accessToken, String source) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("source", source);
        final String url = host + "/api/lapp/live/address/get";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult<List<LiveAddress>>>() {
        });
        return ysResult;
    }

}
