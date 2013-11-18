/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import java.security.Timestamp;

/**
 * Interface untuk data.
 * 
 * @author zakyalvan
 */
public interface Data<I> {
    I getId();
    Object get(String key);
    <T> T get(String key, Class<T> type);
    Timestamp getTimestamp();
}
