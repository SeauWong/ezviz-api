package com.wongcu.ezvizapi.clients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wongcu.ezvizapi.common.HttpClientResult;
import com.wongcu.ezvizapi.common.YSPageResult;
import com.wongcu.ezvizapi.common.YSResult;
import com.wongcu.ezvizapi.common.ram.Policy;
import com.wongcu.ezvizapi.common.ram.RamDetail;
import com.wongcu.ezvizapi.common.ram.Statement;
import com.wongcu.ezvizapi.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 萤石ram接口
 */
@Component
@Slf4j
public class EzvizRamClient {

    /**
     * 创建子账户
     *
     * @param accessToken 授权过程获取的access_token
     * @param accountName 子账户名, 4-40位英文字母或_字符
     * @param password    子账户密码，LowerCase(MD5(AppKey#密码明文))
     * @return
     */
    public YSResult<String> createAccount(String host, String accessToken, String accountName, String password) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("accountName", accountName);
        params.put("password", password);
        final String url = host + "/api/lapp/ram/account/create";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult<String> ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult<String>>() {
        });
        return ysResult;
    }

    /**
     * 获取单个子账户信息
     *
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param accountId   子账户Id 非必填，与accountName二选一
     * @param accountName 子账户Name 非必填，与accountId二选一
     * @return
     */
    public YSResult<RamDetail> getAccount(String host, String accessToken, String accountId, String accountName) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("accountId", accountId);
        params.put("accountName", accountName);
        final String url = host + "/api/lapp/ram/account/get";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult<RamDetail> ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult<RamDetail>>() {
        });
        return ysResult;
    }

    /**
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param pageStart   分页起始页，从0开始
     * @param pageSize    分页大小，默认为10，最大为50
     * @return
     */
    public YSPageResult<List<RamDetail>> ramAccountList(String host, String accessToken, Integer pageStart, Integer pageSize) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("pageStart", pageStart);
        params.put("pageSize", pageSize);
        final String url = host + "/api/lapp/ram/account/list";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSPageResult<>();
        }
        YSPageResult<List<RamDetail>> ysResult = JSON.parseObject(result.getData(), new TypeReference<YSPageResult<List<RamDetail>>>() {
        });
        return ysResult;
    }

    /**
     * 获取B模式子账户accessToken
     *
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param accountId   子账户Id
     * @return
     */
    public YSResult<String> getRamToken(String host, String accessToken, String accountId) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("accountId", accountId);
        final String url = host + "/api/lapp/ram/token/get";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult<String> ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult<String>>() {
        });
        return ysResult;
    }

    /**
     * 设置子账户的授权策略
     *
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param accountId   子账户Id
     * @param policy      授权策略
     * @return
     */
    public YSResult setRamPolicy(String host, String accessToken, String accountId, Policy policy) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("accountId", accountId);
        String policyString = JSON.toJSONString(policy);
        params.put("policy", policyString);
        final String url = host + "/api/lapp/ram/policy/set";
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
     * 增加子账户权限
     *
     * @param host
     * @param accessToken 授权过程获取的access_token
     * @param accountId   子账户Id
     * @param statement   授权语句
     * @return
     */
    public YSResult addRamStatement(String host, String accessToken, String accountId, Statement statement) throws IOException {
        Map<String, Object> params = new HashMap<>(2);
        params.put("accessToken", accessToken);
        params.put("accountId", accountId);
        String statementString = JSON.toJSONString(statement);
        params.put("statement", statementString);
        final String url = host + "/api/lapp/ram/statement/add";
        HttpClientResult result = HttpUtil.postWithForm(url, null, params);
        if (!result.isSuccess()) {
            log.error("{}调用发生异常,result:{}", url, result);
            return new YSResult<>();
        }
        YSResult ysResult = JSON.parseObject(result.getData(), new TypeReference<YSResult>() {
        });
        return ysResult;
    }

}
