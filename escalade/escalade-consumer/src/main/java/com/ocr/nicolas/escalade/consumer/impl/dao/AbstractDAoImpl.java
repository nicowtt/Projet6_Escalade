package com.ocr.nicolas.escalade.consumer.impl.dao;



import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;


public abstract class AbstractDAoImpl {

    @Inject
    @Named("dataSourceEscalade")
    private DataSource datasource;

    protected DataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }



}
