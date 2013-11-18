/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;
import java.io.InputStream;

/**
 * Kontrak interface untuk kelas object yang dapat dijadikan sumber data.
 * 
 * @author zakyalvan
 */
public interface DataSource<T> {
    boolean isConnected();
    void connect() throws ApplicationException;
    InputStream getInputStream() throws ApplicationException;
    T getNative();
}
