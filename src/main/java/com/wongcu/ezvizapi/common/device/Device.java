package com.wongcu.ezvizapi.common.device;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wongcu
 * @version 2018/11/2 14:55
 * @since 2018/11/2
 */
@Data
public class Device implements Serializable {
    private static final long serialVersionUID = 2915307603618609014L;

    /**
     * 设备序列号
     */
    private String deviceSerial;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 在线状态：0-不在线，1-在线
     */
    private Integer status;

    /**
     * 具有防护能力的设备布撤防状态：0-睡眠，8-在家，16-外出，普通IPC布撤防状态：0-撤防，1-布防
     */
    private Integer defence;

    /**
     * 设备版本号
     */
    private String deviceVersion;
}
