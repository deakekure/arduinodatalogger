/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * Ini adalah kelas dari object pekerja yang dibelakang layar melakukan 
 * koleksi data secara real time. Sebenarnya object dari kelas ini mendelegasikan
 * koleksi data kepada object dari type DataCollector.
 * 
 * @author zakyalvan
 */
public class DataCollectingWorker extends SwingWorker<Void, Integer> {
    private static final Logger LOGGER = Logger.getLogger("DataCollectingWorker");
    
    private DataCollector dataCollector;
    
    public DataCollectingWorker(DataCollector dataCollector) {
        this.dataCollector = dataCollector;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        LOGGER.info("Do collecting data in bacground of swing thread.");
        
        if(dataCollector instanceof RemoteDataCollector) {
            ((RemoteDataCollector) dataCollector).connect(new HashMap<String, Object>());
        }
        
        dataCollector.collect();
        
        return null;
    }
}
