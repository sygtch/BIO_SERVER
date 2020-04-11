package com.guo.sky;

import java.io.Serializable;

/**
 * @author GuoTianchi
 * @version 1.0
 * @date 2020/4/8 13:41
 */
public class Request implements Serializable {

    private String clazzName;
    private String method;
    private Object[] params;

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
