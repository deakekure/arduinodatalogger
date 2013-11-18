/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.helper;

import com.radhy.ta.data.DataCollector;
import java.util.Map;

/**
 *
 * @author zakyalvan
 */
public interface DataCollectorFactory {
    DataCollector create(Map<String, Object> factoryParams);
}
