/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data.source;

import com.radhy.ta.ApplicationException;
import com.radhy.ta.data.SocketDataCollector;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author zakyalvan
 */
public class SocketDataSourceFactory implements DataSourceFactory<SocketDataSource> {
    private static final Logger LOGGER = Logger.getLogger(SocketDataSourceFactory.class.getSimpleName());
    private SocketDataSource dataSource;
    
    @Override
    public SocketDataSource create() throws ApplicationException {
        final JDialog parameterDialog = new JDialog();
        
        parameterDialog.setTitle("Parameter Koneksi");
        SocketDataSourceFactoryPanel parameterPanel = new SocketDataSourceFactoryPanel();
        parameterPanel.setConnectListener(new SocketDataSourceFactoryPanel.ConnectListener() {
            @Override
            protected void onConnect(String host, int port) {
                dataSource = new SocketDataSource(host, port);
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
        return dataSource;
    }

    @Override
    public boolean canHandle(Class<? extends DataSource> dataSourceType) {
        if(SocketDataSource.class.isAssignableFrom(dataSourceType)) {
            return true;
        }
        return false;
    }
}
