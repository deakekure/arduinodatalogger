/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.gui;

import com.radhy.ta.data.DataContainerEvent;
import com.radhy.ta.data.SimpleData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zakyalvan
 */
public abstract class DataChartPanelUpdater implements DataContainerEvent.Handler {
    protected DataChartPanel chartPanel;
    
    public DataChartPanelUpdater(DataChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }
    
    @Override
    public void handle(DataContainerEvent event) {
        if(event instanceof DataContainerEvent.NewDatasEvent) {
            final List<SimpleData> datas = new ArrayList<SimpleData>(((DataContainerEvent.NewDatasEvent) event).getNewDatas());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doAddDatas(datas);
                }
            }).start();
        }
    }
    
    protected abstract void doAddDatas(List<SimpleData> datas);
}