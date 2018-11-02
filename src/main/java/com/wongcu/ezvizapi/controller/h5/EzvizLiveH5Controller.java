package com.wongcu.ezvizapi.controller.h5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wongcu
 * @version 2018/11/2 15:42
 * @since 2018/11/2
 */
@Controller
@RequestMapping("/ezviz/live")
public class EzvizLiveH5Controller {

    @GetMapping("/index")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("rtmpAddr", "rtmp://rtmp.open.ys7.com/openlive/XXX");
        modelMap.addAttribute("hlsAddr", "http://hls.open.ys7.com/openlive/XXX.m3u8");
        return "ezviz-live";
    }
}
