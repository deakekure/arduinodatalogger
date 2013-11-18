/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import com.radhy.ta.ApplicationException;

/**
 * Kontrak untuk kelas dari object yang bekerja untuk koleksi data.
 * 
 * @author zakyalvan
 */
public interface DataCollector {
    void setDataContainer(DataContainer dataContainer);
    void collect() throws ApplicationException;
}