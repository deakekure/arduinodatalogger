/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;

/**
 *
 * @author zakyalvan
 */
public interface DataSourceFactory<T extends DataSource> {
    /**
     * Create data source object.
     * 
     * @return
     * @throws ApplicationException 
     */
    T create() throws ApplicationException;
    
    /**
     * Apakah factory dapat mencreate data source berdasarkan type yang diberikan.
     * 
     * @param dataSourceType
     * @return 
     */
    boolean canHandle(Class<? extends DataSource> dataSourceType);
}
