package com.wongcu.ezvizapi.common.ram;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Data
public class Statement implements Serializable {

    private static final long serialVersionUID = 6682798434940814012L;
    @JSONField(name = "Permission")
    private String permission;

    @JSONField(name = "Resource")
    private List<String> resource;
}
