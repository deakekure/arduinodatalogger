/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author zakyalvan
 */
public class SerialDataSource implements InteractiveDataSource<Object> {
    @Override
    public OutputStream getOutputStream() throws ApplicationException {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void connect() throws ApplicationException {
        
    }

    @Override
    public InputStream getInputStream() throws ApplicationException {
        return null;
    }

    @Override
    public Object getNative() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}