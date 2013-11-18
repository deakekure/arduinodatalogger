/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Logger;

/**
 * 
 * 
 * @author zakyalvan
 */
public class SocketDataSource implements InteractiveDataSource<Socket> {
    private static final Logger LOGGER = Logger.getLogger(SocketDataSource.class.getSimpleName());
    
    public static final int DEFAULT_CONNECT_TIMEOUT = 10000;
    
    private Socket socket;
    
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    
    private String host;
    private int port;
    
    public SocketDataSource(String host, int port) {
        socket = new Socket();
        
        this.host = host;
        this.port = port;
    }
    public SocketDataSource(String host, int port, int connectTimeout) {
        this(host, port);
        this.connectTimeout = connectTimeout;
    }
    
    @Override
    public OutputStream getOutputStream() throws ApplicationException {
        if(!isConnected()) {
            LOGGER.severe("Socket belum terkoneksi.");
            throw new ApplicationException("Socket belum terkoneksi.");
        }
        
        if(socket.isOutputShutdown()) {
            LOGGER.severe("Socket output stream shutdown.");
            throw new ApplicationException("Socket output stream shutdown.");
        }
        
        OutputStream outputStream;
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException ex) {
            LOGGER.severe("Kesalahan input/output, dengan pesan : " + ex.getMessage());
            throw new ApplicationException("Kesalahan input/output", ex);
        }
        return outputStream;
    }

    @Override
    public boolean isConnected() {
        return socket.isConnected();
    }

    public void connect(int connectTimeout) throws ApplicationException {
        this.connectTimeout = connectTimeout;
        connect();
    }
    @Override
    public void connect() throws ApplicationException {
        try {
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            socket.connect(socketAddress, connectTimeout);
        } catch (IOException ex) {
            throw new ApplicationException("Kesalahan input/output", ex);
        }
    }

    @Override
    public InputStream getInputStream() throws ApplicationException {
        if(!isConnected()) {
             LOGGER.severe("Socket belum terkoneksi.");
            throw new ApplicationException("Socket belum terkoneksi.");
        }
        
        if(socket.isInputShutdown()) {
             LOGGER.severe("Socket input stream shutdown.");
            throw new ApplicationException("Socket input stream shutdown.");
        }
        
        InputStream inputStream;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException ex) {
            LOGGER.severe("Kesalahan input/output, dengan pesan : " + ex.getMessage());
            throw new ApplicationException("Kesalahan input/output", ex);
        }
        return inputStream;
    }

    @Override
    public Socket getNative() {
        return socket;
    }
}
