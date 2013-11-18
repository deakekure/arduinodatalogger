/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;
import java.io.OutputStream;

/**
 * Data source yang memungkinkan user mengirim perintah atau command.
 * 
 * @author zakyalvan
 */
public interface InteractiveDataSource<T> extends DataSource<T> {
    OutputStream getOutputStream() throws ApplicationException;
}
