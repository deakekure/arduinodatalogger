/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author zakyalvan
 */
public class DataContainerEvent {
    private Date timestamp = new Date();
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * Simple echo handler, tampilin setiap data baru yang ditambahin ke data container.
     */
    public static final Handler ECHO_NEW_DATAS_HANDLER = new Handler() {
        @Override
        public void handle(final DataContainerEvent event) {
            // Echo di luar event dispatch thread (di belakang swing thread, biar ga ngeganggu).
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(event instanceof NewDatasEvent) {
                        Collection<SimpleData> datas = ((NewDatasEvent) event).getNewDatas();
                        for(SimpleData data : datas) {
                            Logger.getLogger("EchoNewDatasHandler").info(data.toString());
                        }
                    }
                }
            }).start();
        }
    };
    
    public interface Handler {
        public void handle(DataContainerEvent event);
    }
    
    public static class NewDatasEvent extends DataContainerEvent {
        private List<SimpleData> newDatas = new ArrayList<SimpleData>();
        
        public NewDatasEvent(List<SimpleData> newDatas) {
            this.newDatas.addAll(newDatas);
        }
        public Collection<SimpleData> getNewDatas() {
            return new ArrayList<SimpleData>(newDatas);
        }
    }
}