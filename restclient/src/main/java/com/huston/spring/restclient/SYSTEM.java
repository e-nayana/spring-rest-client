package com.huston.spring.restclient;

public enum SYSTEM {

    VMS("vms"),

    COLLECTION_CENTRE("collection_centre"),

    BUY_NOW("buy_now"),

    HRM("hrm"),

    SALTSIDE("salt_side"),

    INVOICEMANGER("invoice_manager");

    private final String configCode;

    SYSTEM(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigCode() {
        return configCode;
    }


}
