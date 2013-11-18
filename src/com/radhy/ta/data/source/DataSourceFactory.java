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
    T create() throws ApplicationException;
    boolean canHandle(Class<? extends DataSource> dataSourceType);
}
