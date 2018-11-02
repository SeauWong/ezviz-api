package com.wongcu.ezvizapi.common;

import lombok.Data;

/**
 * @author wongcu
 * @version 2018/11/2 13:59
 * @since 2018/11/2
 */
@Data
public class YSPageResult<T> {

    private Page page;

    private String code;

    private String msg;

    private T data;

    @Data
    public static class Page {

        /**
         * 总记录数
         */
        private Integer total;

        /**
         * 分页起始页
         */
        private Integer page;

        /**
         * 分页大小
         */
        private Integer size;
    }
}
