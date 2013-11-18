/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;
import java.io.OutputStream;

/**
 * Saya bingung milih namanya, intinya ini adalah kontrak untuk kelas object datasource
 * yang dapat dikirimi perintah (lewat OutputStream).
 * 
 * @author zakyalvan
 */
public interface InteractiveDataSource<T> extends DataSource<T> {
    OutputStream getOutputStream() throws ApplicationException;
}
