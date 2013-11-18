/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import com.radhy.ta.data.DataContainerEvent.Handler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Data container.
 * 
 * @author zakyalvan
 */
public class DataContainer implements Iterable<SimpleData> {
    private static DataContainer instance;
    
    public static DataContainer getInstance() {
        if(instance == null) {
            instance = new DataContainer();
        }
        return instance;
    }
    
    private List<SimpleData> newDatas = new ArrayList<>();
    private List<SimpleData> datas = new ArrayList<>();
    
    private Set<DataContainerEvent.Handler> eventHandlers = new HashSet<>();
    
    private int maxNewDataQueueSize = 10;
    
    private DataContainer() {}

    public void add(SimpleData data) {
        datas.add(data);
        newDatas.add(data);
        if(newDatas.size() == maxNewDataQueueSize) {
            final List<SimpleData> newDatasToPublish = new ArrayList<>(newDatas);
            newDatas.clear();
                
            new Thread(new Runnable() {
                @Override
                public void run() {
                    notifyEventHandler(new DataContainerEvent.NewDatasEvent(newDatasToPublish));
                }
            }).start();
        }
    }
    public Collection<SimpleData> getDatas() {
        return new ArrayList<>(datas);
    }
    
    public void addEventHandler(Handler handler) {
        eventHandlers.add(handler);
    }
    
    protected void notifyEventHandler(DataContainerEvent event) {
        for(DataContainerEvent.Handler eventHandler : this.eventHandlers) {
            eventHandler.handle(event);
        }
    }
    
    @Override
    public Iterator<SimpleData> iterator() {
        return datas.iterator();
    }
}
