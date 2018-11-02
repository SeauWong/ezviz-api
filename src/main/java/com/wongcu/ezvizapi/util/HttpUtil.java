package com.wongcu.ezvizapi.util;

import com.wongcu.ezvizapi.common.HttpClientResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * http请求工具类
 *
 * @author wongcu
 */
@Slf4j
public class HttpUtil {

    // 连接池总大小
    private static final int MAX_TOTAL = 100;
    // 每个domain的连接最大限制，MAX_PER_ROUTE<=MAX_TOTAL才有意义
    private static final int MAX_PER_ROUTE = MAX_TOTAL;
    // 可用空闲连接过期时间,重用空闲连接时会先检查是否空闲时间超过这个时间，如果超过，释放socket重新建
    private static final int VALIDATE_AFTER_INACTIVITY = 15 * 1000;
    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 10 * 1000;
    // socket读取数据超时时间
    private static final int SOCKET_TIMEOUT = 10 * 1000;
    // 从连接池当中获取链接的超时时间
    private static final int CONNECTION_REQUEST_TIMEOUT = 7000;

    /**
     * http连接池管理器
     */
    private static final PoolingHttpClientConnectionManager HTTP_CLIENT_CONNECTION_MANAGER;
    /**
     * 请求配置
     */
    private static final RequestConfig REQUEST_CONFIG;

    static {
        // step1 配置连接池管理器
        HTTP_CLIENT_CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        HTTP_CLIENT_CONNECTION_MANAGER.setMaxTotal(MAX_TOTAL);
        // 设置每个domain的最大连接数限制
        HTTP_CLIENT_CONNECTION_MANAGER.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        // 设置连接空闲时间
        HTTP_CLIENT_CONNECTION_MANAGER.setValidateAfterInactivity(VALIDATE_AFTER_INACTIVITY);

        // step2 请求配置
        REQUEST_CONFIG = RequestConfig.custom()
                .setConnectTimeout(CONNECTION_TIMEOUT)// 设置连接超时
                .setSocketTimeout(SOCKET_TIMEOUT)// 设置读取超时
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)// 设置从连接池获取连接实例的超时
                .build();
    }

    /**
     * 发送 POST 请求（HTTP），表单提交
     *
     * @param apiUrl  API接口URL
     * @param headers header
     * @param params  参数map
     * @return
     */
    public static HttpClientResult postWithForm(String apiUrl, Map<String, Object> headers, Map<String, Object> params) throws IOException {
        if (headers == null) {
            headers = Collections.emptyMap();
        }
        if (params == null) {
            params = Collections.emptyMap();
        }

        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setConfig(REQUEST_CONFIG);

        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue() == null ? "" : String.valueOf(entry.getValue()));
        }

        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue() == null ? "" : String.valueOf(entry.getValue()));
            pairList.add(pair);
        }

        httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));

        log.debug("POST:{} headers:参数:{}, params:{}", apiUrl, headers, params);
        return doHttpExecute(httpPost);
    }

    /**
     * 执行http调用
     *
     * @param httpUriRequest
     * @return
     * @throws IOException
     */
    private static HttpClientResult doHttpExecute(HttpUriRequest httpUriRequest) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            HttpClientResult result = new HttpClientResult(response);
            return result;
        }
    }

}
