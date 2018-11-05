package com.wongcu.ezvizapi.controller.h5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wongcu
 * @version 2018/11/2 15:42
 * @since 2018/11/2
 */
@Controller
@RequestMapping("/ezviz/h5/live")
public class EzvizLiveH5Controller {

    @GetMapping("/index")
    public String index(){
        return "ezviz-index";
    }


    @GetMapping("/show")
    public String show(@RequestParam("rtmpAddr") String rtmpAddr,
                       @RequestParam("hlsAddr") String hlsAddr,
                       ModelMap modelMap) {
        modelMap.addAttribute("rtmpAddr", rtmpAddr);
        modelMap.addAttribute("hlsAddr", hlsAddr);
        return "ezviz-live";
    }
}
