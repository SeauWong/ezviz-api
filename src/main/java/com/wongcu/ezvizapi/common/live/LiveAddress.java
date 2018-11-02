package com.wongcu.ezvizapi.common.live;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wongcu
 * @version 2018/11/2 13:41
 * @since 2018/11/2
 */
@Data
public class LiveAddress implements Serializable {
    private static final long serialVersionUID = 868201121403069498L;

    private String deviceSerial;

    private Integer channelNo;

    private String deviceName;

    private String hls;

    private String hlsHd;

    private String rtmp;

    private String rtmpHd;

    private Integer status;

    private Integer exception;

    private String ret;

    private String desc;
}
