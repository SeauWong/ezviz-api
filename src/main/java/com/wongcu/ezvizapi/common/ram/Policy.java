package com.wongcu.ezvizapi.common.ram;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wongcu
 * @version 2018/11/2 12:26
 * @since 2018/11/2
 */
@Data
public class Policy implements Serializable {
    private static final long serialVersionUID = 8669330217979859532L;

    @JSONField(name = "Statement")
    private List<Statement> statement;
}
