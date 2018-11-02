/**
 *
 */
package com.wongcu.ezvizapi.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wongcu
 * @version 2018/9/25 18:26
 * @since 2018/9/25
 */
@Data
public class Token implements Serializable {

    private String accessToken;
    /**
     * 过期时间 绝对时间 毫秒
     */
    private long expireTime;
}
