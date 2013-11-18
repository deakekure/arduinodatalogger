/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.source;

import java.util.ServiceLoader;
import java.util.logging.Logger;

/**
 *
 * @author zakyalvan
 */
public class DataSourceFactoryProvider {
    private static final Logger LOGGER = Logger.getLogger(DataSourceFactoryProvider.class.getSimpleName());
    
    /**
     *
     * @param dataSourceType
     * @return
     */
    public static DataSourceFactory getFactory(Class<? extends DataSource> dataSourceType) {
        LOGGER.info("Get factory of data source with type : " + dataSourceType.getSimpleName());
        
        ServiceLoader<DataSourceFactory> serviceLoader = ServiceLoader.load(DataSourceFactory.class);
        
        for(DataSourceFactory dataSourceFactory : serviceLoader) {
            if(dataSourceFactory.canHandle(dataSourceType)) {
                return dataSourceFactory;
            }
        }
        return null;
    }
}
