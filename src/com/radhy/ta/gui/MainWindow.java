/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.gui;

import com.radhy.ta.ApplicationException;
import com.radhy.ta.data.DataCollectingWorker;
import com.radhy.ta.data.DataContainer;
import com.radhy.ta.data.OptimizedDataCollector;
import com.radhy.ta.data.SimpleData;
import java.awt.Color;
import java.util.List;
import java.util.logging.Logger;

/**
 * Main window yang ditampilkan pada saat aplikasi ini dijalankan,
 * 
 * @author zakyalvan
 */
public class MainWindow extends javax.swing.JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getSimpleName());
    
    private OptimizedDataCollector dataCollector;
    private DataContainer dataContainer;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.setTitle("Kaswandhi Triyoso");
        
        dataContainer = DataContainer.getInstance();
        //dataContainer.addEventHandler(DataContainerEvent.ECHO_NEW_DATAS_HANDLER);
        
        DataChartPanel xChartPanel = new DataChartPanel("Sumbu X", Color.RED);
        DataChartPanelUpdater xChartPanelUpdater = new DataChartPanelUpdater(xChartPanel) {
            @Override
            protected void doAddDatas(List<SimpleData> datas) {
                for(SimpleData data : datas) {
                    chartPanel.addData(data.getX(), data.getTimestamp());
                }
            }
        };
        dataContainer.addEventHandler(xChartPanelUpdater);
        getContentPane().add(xChartPanel);
        
        DataChartPanel yChartPanel = new DataChartPanel("Sumbu Y", Color.GREEN);
        DataChartPanelUpdater yChartPanelUpdater = new DataChartPanelUpdater(yChartPanel) {
            @Override
            protected void doAddDatas(List<SimpleData> datas) {
                for(SimpleData data : datas) {
                    chartPanel.addData(data.getY(), data.getTimestamp());
                }
            }
        };
        dataContainer.addEventHandler(yChartPanelUpdater);
        getContentPane().add(yChartPanel);
        
        DataChartPanel zChartPanel = new DataChartPanel("Sumbu Z", Color.BLUE);
        DataChartPanelUpdater zChartPanelUpdater = new DataChartPanelUpdater(zChartPanel) {
            @Override
            protected void doAddDatas(List<SimpleData> datas) {
                for(SimpleData data : datas) {
                    chartPanel.addData(data.getZ(), data.getTimestamp());
                }
            }
        };
        dataContainer.addEventHandler(zChartPanelUpdater);
        getContentPane().add(zChartPanel);
        
        pack();
    }
    
    public void setDataCollector(OptimizedDataCollector dataCollector) {
        this.dataCollector = dataCollector;
    }
    
    public void startCollecting() throws ApplicationException {
        if(dataCollector == null) {
            LOGGER.warning("Instance DataCollector blum diberikan");
            throw new ApplicationException("Instance DataCollector blum diberikan");
        }
        else {
            dataCollector.start();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(3, 1));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}