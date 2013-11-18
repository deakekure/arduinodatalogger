/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.helper;

import com.radhy.ta.data.DataCollector;
import com.radhy.ta.data.SocketDataCollector;
import com.radhy.ta.data.helper.SocketDataSourceFactoryPanel.ConnectListener;
import com.radhy.ta.gui.MainWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author zakyalvan
 */
public class SocketDataCollectorFactory implements DataCollectorFactory {
    private static final Logger LOGGER = Logger.getLogger("SocketDataCollectorFactory");
    
    private MainWindow mainWindow;
    private DataCollector dataCollector;
    
    public SocketDataCollectorFactory(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    @Override
    public DataCollector create(Map<String, Object> factoryParams) {
        LOGGER.info("Create socket data collector instance");
        
        final JDialog parameterDialog = new JDialog(mainWindow);
        parameterDialog.setTitle("Parameter Koneksi");
        SocketDataSourceFactoryPanel parameterPanel = new SocketDataSourceFactoryPanel();
        parameterPanel.setConnectListener(new ConnectListener() {
            @Override
            protected void onConnect(String host, int port) {
                dataCollector = new SocketDataCollector(host, port);
                parameterDialog.setVisible(false);
            }
        });
        parameterDialog.getContentPane().add(parameterPanel);
        parameterDialog.pack();
        parameterDialog.setModal(true);
        parameterDialog.setResizable(false);
        parameterDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LOGGER.info("Close the application");
                System.exit(0);
            }
        });
        parameterDialog.setVisible(true);
        return dataCollector;
    }
}
