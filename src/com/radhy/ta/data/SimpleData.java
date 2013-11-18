/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Kelas yang menyatakan instance dari data.
 * 
 * @author zakyalvan
 */
public class SimpleData implements Serializable {
    private final Double x;
    private final Double y;
    private final Double z;
    
    private Date timestamp;
    
    public SimpleData(Double x, Double y, Double z) {
        this(x, y, z, new Date());
    }
    public SimpleData(Double x, Double y, Double z, Date timestamp) {
        this.x = x;
        this.y = y;
        this.z = z;
        
        this.timestamp = timestamp;
    }
    
    public Double getX() {
        return x;
    }
    public Double getY() {
        return y;
    }
    public Double getZ() {
        return z;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "Data : {x : " + x + ", y : " + y + ", z : " + z + " timestamp : " + timestamp + "}";
    }
    
    /**
     * Kontrak untuk parser data.
     */
    public interface Parser {
        SimpleData parseString(String data);
        SimpleData parseString(String data, String delimiter);
    }
    
    public static final Parser DEFAULT_PARSER = new Parser() {
        public static final String DEFAULT_DATA_DELIMITER = " ";
        
        /**
         * Parsing string data dengan default delimiter.
         */
        @Override
        public SimpleData parseString(String data) {
            return parseString(data, DEFAULT_DATA_DELIMITER);
        }
        @Override
        public SimpleData parseString(String data, String delimiter) {
            String[] splitedData = data.split(" ");
            Double x = Double.parseDouble(splitedData[0]);
            Double y = Double.parseDouble(splitedData[1]);
            Double z = Double.parseDouble(splitedData[2]);
            
            return new SimpleData(x, y, z);
        }
    };
}
