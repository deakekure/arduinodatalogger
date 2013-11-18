/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import java.util.Map;

/**
 *
 * @author zakyalvan
 */
public interface RemoteDataCollector extends DataCollector {
    void connect(Map<String, Object> connectParam) throws Exception;
}
