package com.wongcu.ezvizapi.clients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wongcu.ezvizapi.common.HttpClientResult;
import com.wongcu.ezvizapi.common.YSPageResult;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.common.device.Device;
import com.wongcu.ezvizapi.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wongcu
 * @version 2018/11/2 14:54
 * @since 2018/11/2
 */
@Component
@Slf4j
public class EzvizDeviceClient {

    /**
     * 获取设备列表
     *
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param pageStart   分页起始页，从0开始
     * @param pageSize    分页大小，默认为10，最大为50
     * @return
     */
    public YSPageResult<List<Device>> deviceList(String host, String accessToken, Integer pageStart, Integer pageSize) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("pageStart", pageStart);
        params.put("pageSize", pageSize);
        final String url = host + "/api/lapp/device/list";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSPageResult<>();
        }
        YSPageResult ysResult = JSON.parseObject(result.getData(), new TypeReference<YSPageResult<List<Device>>>() {
        });
        return ysResult;
    }

    /**
     * 修改设备名称
     *
     * @param host
     * @param accessToken  授权过程获取的access_token
     * @param deviceSerial 设备序列号
     * @param deviceName   设备名称，长度不大于50字节，不能包含特殊字符
     * @return
     * @throws IOException
     */
    public YSResult updateDeviceName(String host, String accessToken, String deviceSerial, String deviceName) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("deviceSerial", deviceSerial);
        params.put("deviceName", deviceName);
        final String url = host + "/api/lapp/device/name/update";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult>() {
        });
        return ysResult;
    }

    /**
     * 根据设备序列号查询设备能力集
     * @param host
     * @param accessToken 访问令牌
     * @param deviceSerial 设备序列号
     * @return Map<K,V> K-能力名 V-是否具备该能力
     */
    public YSResult<Map<String,String>> deviceCapacity(String host, String accessToken, String deviceSerial) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("deviceSerial", deviceSerial);
        final String url = host + "/api/lapp/device/capacity";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult<Map<String,String>> ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult<Map<String,String>>>() {
        });
        return ysResult;
    }
}
