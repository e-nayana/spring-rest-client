package com.huston.spring.restclient.rest;

import com.huston.spring.restclient.SYSTEM;
import com.huston.spring.restclient.authentication.model.BaseAuthenticationCommunicationEntity;

public class SystemAwareCommunicationEntity implements BaseAuthenticationCommunicationEntity {

    SYSTEM system;

    public SYSTEM getSystem() {
        return system;
    }

    public void setSystem(SYSTEM system) {
        this.system = system;
    }

}
