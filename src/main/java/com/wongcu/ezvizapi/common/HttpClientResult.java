package com.wongcu.ezvizapi.common;

import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;

@Data
public class HttpClientResult implements Serializable {

    private static final long serialVersionUID = -8206843202914904822L;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回结果
     */
    private String data;

    /**
     * 失败原因
     */
    private String cause;

    public HttpClientResult() {
    }

    public HttpClientResult(CloseableHttpResponse response) throws IOException {
        this.code = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        this.data = EntityUtils.toString(entity, "UTF-8");
        if (code != 200) {
            this.cause = response.getStatusLine().getReasonPhrase();
        }
    }

    public Boolean isSuccess() {
        return 200 == code;
    }
}
