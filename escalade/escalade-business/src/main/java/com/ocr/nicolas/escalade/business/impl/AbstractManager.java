package com.ocr.nicolas.escalade.business.impl;

import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;
import javax.inject.Named;


public abstract class AbstractManager {

    @Inject
    @Named("txManager")
    private PlatformTransactionManager platformTransactionManager;

    protected PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }
}
