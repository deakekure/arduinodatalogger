/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;

/**
 *
 * @author zakyalvan
 */
public class SerialDataSourceFactory implements DataSourceFactory<DataSource> {
    @Override
    public DataSource create() throws ApplicationException {
        return null;
    }

    @Override
    public boolean canHandle(Class<? extends DataSource> dataSourceType) {
        if(dataSourceType.isAssignableFrom(SerialDataSource.class)) {
            return true;
        }
        return false;
    }
}
