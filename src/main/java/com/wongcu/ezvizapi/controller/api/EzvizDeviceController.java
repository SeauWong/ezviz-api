package com.wongcu.ezvizapi.controller.api;

import com.wongcu.ezvizapi.clients.EzvizDeviceClient;
import com.wongcu.ezvizapi.common.YSPageResult;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.common.device.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author wongcu
 * @version 2018/11/2 14:59
 * @since 2018/11/2
 */
@RestController
@RequestMapping("/ezviz/devices")
public class EzvizDeviceController {

    @Value("${ys.host}")
    private String ysHost;

    @Autowired
    private EzvizDeviceClient deviceClient;

    @GetMapping("/list_devices")
    public YSPageResult<List<Device>> deviceList(String accessToken, Integer pageStart, Integer pageSize) throws IOException {
        return deviceClient.deviceList(ysHost, accessToken, pageStart, pageSize);
    }

    @PutMapping("/update_device_name")
    public YSResult updateDeviceName(String accessToken, String deviceSerial, String deviceName) throws IOException {
        return deviceClient.updateDeviceName(ysHost, accessToken, deviceSerial, deviceName);
    }
}
