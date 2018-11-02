package com.wongcu.ezvizapi.common.ram;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wongcu
 * @version 2018/11/2 12:21
 * @since 2018/11/2
 */
@Data
public class RamDetail implements Serializable {

    private static final long serialVersionUID = 2948948730317264839L;
    private String accountId;

    private String accountName;

    private String appKey;

    private Integer accountStatus;

    private Policy policy;
}
