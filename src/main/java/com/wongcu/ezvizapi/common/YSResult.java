package com.wongcu.ezvizapi.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class YSResult<T> implements Serializable {
    private static final long serialVersionUID = 3055060842297103818L;

    private String code;

    private String msg;

    private T data;
}
