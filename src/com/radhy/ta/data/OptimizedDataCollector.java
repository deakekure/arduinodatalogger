/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import com.radhy.ta.data.source.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author zakyalvan
 */
public class OptimizedDataCollector extends SwingWorker<Void, Integer> {
    private static final Logger LOGGER = Logger.getLogger(OptimizedDataCollector.class.getSimpleName()); 
    
    private DataSource dataSource;
    private DataContainer dataContainer;
    
    private boolean started = false;
    
    public OptimizedDataCollector(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dataContainer = DataContainer.getInstance();
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        collect();
        return null;
    }
    
    public DataContainer getDataContainer() {
        return dataContainer;
    }
    
    public boolean isStarted() {
        return started;
    }
    
    public void collect() {
        if(!dataSource.isConnected()) {
            dataSource.connect();
        }
        
        InputStreamReader inputStreamReader = new InputStreamReader(dataSource.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            String line;
            while(!(line = bufferedReader.readLine()).equalsIgnoreCase("STOP")) {
                SimpleData data = SimpleData.DEFAULT_PARSER.parseString(line);
                dataContainer.add(data);
            }
        }
        catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Kesalahan input/output", ex);
        }
    }
    
    /**
     * Start swing worker. Dengan anggapan awal bahwa method ini akan selalu 
     * dipanggil di awt event dispatch thread, jadi ga perlu sinkronisasi pada saat
     * execute dipanggil.
     */
    public void start() {
        // Sesuai dokumentasi, sebuah swing-worker yang sudah dieksekusi tidak bisa dieksekusi lagi.
        if(!started) {
            this.execute();
            started = true;
        }
    }
    public void pause() {
        
    }
    public void resume() {
        
    }
    public void stop() {
        
    }
}
