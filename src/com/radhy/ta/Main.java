/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.radhy.ta.data.DataContainer;
import com.radhy.ta.data.OptimizedDataCollector;
import com.radhy.ta.data.source.DataSourceFactory;
import com.radhy.ta.data.source.DataSourceFactoryProvider;
import com.radhy.ta.data.source.SocketDataSource;
import com.radhy.ta.gui.MainWindow;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Ini main class yang harus dijalankan untuk menggunakan program ini.
 * 
 * @author zakyalvan
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");
    public static void main(String[] args) {
        try {
//            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
            UIManager.setLookAndFeel(WindowsLookAndFeel.class.getName());
        }
        catch (ClassNotFoundException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainWindow mainWindow = new MainWindow();

                    DataSourceFactory dataSourceFactory = DataSourceFactoryProvider.getFactory(SocketDataSource.class);
                    SocketDataSource socketDataSource = (SocketDataSource) dataSourceFactory.create();
                    OptimizedDataCollector dataCollector = new OptimizedDataCollector(socketDataSource);

                    DataContainer dataContainer = dataCollector.getDataContainer();

                    mainWindow.setDataCollector(dataCollector);
                    mainWindow.startCollecting();
                    mainWindow.setVisible(true);
                }
                catch(ApplicationException aex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada aplikasi dengan pesan kesalahan : \n" + aex.getMessage());
                    System.exit(0);
                }
                catch(Exception e) {
                    JOptionPane.showMessageDialog(null, "Kesalahan tidak terduga dengan pesan : \n" + e.getMessage());
                    System.exit(0);
                }
            }
        });
    }
}
