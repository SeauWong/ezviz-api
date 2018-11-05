package com.wongcu.ezvizapi.controller.api;

import com.wongcu.ezvizapi.clients.EzvizPtzClient;
import com.wongcu.ezvizapi.common.YSResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author huangsiyao
 * @version 2018/11/3 14:04
 * @since 2018/11/3
 */
@RestController
@RequestMapping("/ezviz/ptz")
@Slf4j
public class EzvizPtzAPIController {

    @Value("${ys.host}")
    private String ysHost;
    @Autowired
    private EzvizPtzClient ptzClient;

    @GetMapping("/start")
    public YSResult ptzStart(String accessToken, String deviceSerial, Integer channelNo, Integer direction, Integer speed) throws IOException {
        return  ptzClient.ptzStart(ysHost,accessToken,deviceSerial,channelNo,direction,speed);
    }

    @GetMapping("/stop")
    public YSResult ptzStop(String accessToken, String deviceSerial, Integer channelNo, Integer direction) throws IOException {
        return ptzClient.ptzStop(ysHost,accessToken,deviceSerial,channelNo,direction);
    }
}
