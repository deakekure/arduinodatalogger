/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import com.radhy.ta.ApplicationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author zakyalvan
 */
public class SocketDataCollector implements RemoteDataCollector {
    public static final String HOST_CONNECT_PARAM = "RealtimeSocketDataCollector.host";
    public static final String PORT_CONNECT_PARAM = "RealtimeSocketDataCollector.port";
    
    private static final Logger LOGGER = Logger.getLogger("SocketDataCollector");
    
    private DataContainer dataContainer;
    
    private String host;
    private int port;
    
    private Socket socket;
    
    public SocketDataCollector(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void connect(Map<String, Object> connectParam) throws ApplicationException {
        LOGGER.info("Connect to remote server host, first check for connection param for overriding the old value");
        if(connectParam.containsKey(HOST_CONNECT_PARAM)) {
            this.host = (String) connectParam.get(HOST_CONNECT_PARAM);
        }
        if(connectParam.containsKey(PORT_CONNECT_PARAM)) {
            this.port = (Integer) connectParam.get(PORT_CONNECT_PARAM);
        }
        
        LOGGER.info("Trying to connect to host : " + host + " and port : " + port);
        try {
            socket = new Socket(host, port);
            LOGGER.info("Connection established on (local) port : " + socket.getLocalPort());
        } 
        catch (UnknownHostException ex) {
            LOGGER.log(Level.SEVERE, "Host tidak diketahui", ex);
            throw new ApplicationException("Host tidak diketahui", ex);
        } 
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Terjadi kesalahan pada Input/Output", ex);
            throw new ApplicationException("Terjadi kesalahan pada Input/Output", ex);
        }
    }
    
    @Override
    public void setDataContainer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }
    
    @Override
    public void collect() throws ApplicationException {
        LOGGER.info("Start collect data.");
        
        if(dataContainer == null) {
            LOGGER.warning("Data container belum di berikan");
            throw new ApplicationException("Data continer belum diberikan");
        }
        
        if(!socket.isConnected()) {
            LOGGER.warning("Koneksi socket ke server data tidak terbangun");
            throw new ApplicationException("Koneksi socket ke server data tidak terbangun");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String line;
            while(!(line = bufferedReader.readLine()).equalsIgnoreCase("STOP")) {
                SimpleData data = SimpleData.DEFAULT_PARSER.parseString(line);
                dataContainer.add(data);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            throw new ApplicationException("Terjadi kesalahan input/output", ex);
        }
    }
}
