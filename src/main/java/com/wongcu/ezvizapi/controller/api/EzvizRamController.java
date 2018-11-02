package com.wongcu.ezvizapi.controller.api;

import com.wongcu.ezvizapi.clients.EzvizRamClient;
import com.wongcu.ezvizapi.common.YSPageResult;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.common.ram.Policy;
import com.wongcu.ezvizapi.common.ram.RamDetail;
import com.wongcu.ezvizapi.common.ram.Statement;
import com.wongcu.ezvizapi.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author wongcu
 * @version 2018/11/2 13:49
 * @since 2018/11/2
 */
@RestController
@RequestMapping("/ezviz/ram")
@Slf4j
public class EzvizRamController {

    @Value("${ys.host}")
    private String ysHost;

    @Autowired
    private EzvizRamClient ramClient;

    @PostMapping("/create")
    public YSResult<String> createAccount(String accessToken, String accountName, String password) throws IOException {
        return ramClient.createAccount(ysHost, accessToken, accountName, MD5Utils.MD5Encode(password, null).toLowerCase());
    }

    @GetMapping("/account")
    public YSResult<RamDetail> getAccount(String accessToken, String accountId, String accountName) throws IOException {
        return ramClient.getAccount(ysHost, accessToken, accountId, accountName);
    }

    @GetMapping("/list_account")
    public YSPageResult<List<RamDetail>> ramAccountList(String accessToken, Integer pageStart, Integer pageSize) throws IOException {
        return ramClient.ramAccountList(ysHost, accessToken, pageStart, pageSize);
    }

    @GetMapping("/ram_token")
    public YSResult<String> getRamToken(String accessToken, String accountId) throws IOException {
        return ramClient.getRamToken(ysHost, accessToken, accountId);
    }

    @PutMapping("/set_ram_police")
    public YSResult setRamPolicy(String accessToken, String accountId, @RequestBody Policy policy) throws IOException {
        return ramClient.setRamPolicy(ysHost, accessToken, accountId, policy);
    }

    @PutMapping("/add_ram_statement")
    public YSResult addRamStatement(String accessToken, String accountId, @RequestBody Statement statement) throws IOException {
        return ramClient.addRamStatement(ysHost, accessToken, accountId, statement);
    }

}
