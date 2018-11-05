package com.wongcu.ezvizapi.clients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wongcu.ezvizapi.common.HttpClientResult;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 云台控制
 * @author huangsiyao
 * @version 2018/11/3 13:57
 * @since 2018/11/3
 */
@Component
@Slf4j
public class EzvizPtzClient {

    /**
     * 开始云台控制
     * 对设备进行开始云台控制，开始云台控制之后必须先调用停止云台控制接口才能进行其他操作，包括其他方向的云台转动
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param deviceSerial 设备序列号
     * @param channelNo 通道号
     * @param direction 操作命令：0-上，1-下，2-左，3-右，4-左上，5-左下，6-右上，7-右下，8-放大，9-缩小，10-近焦距，11-远焦距
     * @param speed 云台速度：0-慢，1-适中，2-快
     * @return
     */
    public YSResult ptzStart(String host, String accessToken,String deviceSerial, Integer channelNo, Integer direction, Integer speed) throws IOException {
        Map<String, Object> params = new HashMap<>(5);
        params.put("accessToken", accessToken);
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", channelNo);
        params.put("direction", direction);
        params.put("speed", speed);
        final String url = host + "/api/lapp/device/ptz/start";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult>() {});
        return ysResult;
    }

    /**
     * 停止云台控制
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param deviceSerial 设备序列号
     * @param channelNo 通道号
     * @param direction 操作命令：0-上，1-下，2-左，3-右，4-左上，5-左下，6-右上，7-右下，8-放大，9-缩小，10-近焦距，11-远焦距
     * @return
     */
    public YSResult ptzStop(String host, String accessToken, String deviceSerial, Integer channelNo, Integer direction) throws IOException {
        Map<String, Object> params = new HashMap<>(4);
        params.put("accessToken", accessToken);
        params.put("deviceSerial", deviceSerial);
        params.put("channelNo", channelNo);
        params.put("direction", direction);
        final String url = host + "/api/lapp/device/ptz/stop";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult>() {});
        return ysResult;
    }
}
