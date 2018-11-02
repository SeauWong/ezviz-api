package com.wongcu.ezvizapi.common.live;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class LiveOpenResult implements Serializable {
    private static final long serialVersionUID = 7130483157092784495L;

    private String deviceSerial;

    private Integer channelNo;

    private String ret;

    private String desc;
}
