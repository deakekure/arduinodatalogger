/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radhy.ta.data;

import com.radhy.ta.ApplicationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zakyalvan
 */
public class SerialDataCollector implements RemoteDataCollector {
    private static final Logger LOGGER = Logger.getLogger("SocketDataCollector");
    
    private DataContainer dataContainer;
    
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

    @Override
    public void connect(Map<String, Object> connectParam) throws Exception {
        
    }
}
