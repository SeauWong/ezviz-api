package com.wongcu.ezvizapi.controller.api;

import com.wongcu.ezvizapi.clients.EzvizLiveClient;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.common.live.LiveAddress;
import com.wongcu.ezvizapi.common.live.LiveOpenResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author wongcu
 * @version 2018/11/2 14:17
 * @since 2018/11/2
 */
@RestController
@RequestMapping("/ezviz/live")
@Slf4j
public class EzvizLiveController {

    @Value("${ys.host}")
    private String ysHost;

    @Autowired
    private EzvizLiveClient liveClient;

    @PostMapping("/open_live")
    public YSResult<List<LiveOpenResult>> openLive(String accessToken, String source) throws IOException {
        return liveClient.openLive(ysHost, accessToken, source);
    }

    @GetMapping("/live_address")
    public YSResult<List<LiveAddress>> getLiveAddress(String accessToken, String source) throws IOException {
        return liveClient.getLiveAddress(ysHost, accessToken, source);
    }
}
