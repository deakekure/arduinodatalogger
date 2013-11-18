/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesChangeListener;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

/**
 * Adapter panel untuk jfreechart
 * 
 * @author zakyalvan
 */
public class DataChartPanel extends javax.swing.JPanel {
    private static final Logger LOGGER = Logger.getLogger("DataChartPanel");
    
    private static final int MAX_DATA_COUNT = 1500;
    
    private String chartTitle;
    private Color lineColor;
    
    private TimeSeries timeSeries;
    
    /**
     * Creates new form ChartPanle
     */
    public DataChartPanel(String chartTitle, Color lineColor) {
        this.chartTitle = chartTitle;
        this.lineColor = lineColor;
        
        initComponents();
        initChart();
    }

    private void initChart() {
        timeSeries = new TimeSeries("Data");
        timeSeries.addChangeListener(new SeriesChangeListener() {
            @Override
            public synchronized void seriesChanged(SeriesChangeEvent sce) {
                TimeSeries series = (TimeSeries) sce.getSource();
                
                series.removeChangeListener(this);
                int itemCount = series.getItemCount();
                if(itemCount > MAX_DATA_COUNT) {
                    int differentCount = itemCount - MAX_DATA_COUNT;
                    series.delete(0, differentCount -1, false);
                }
                series.addChangeListener(this);
            }
        });
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeries);
        
        DateAxis domain = new DateAxis("Time");
        domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 8));
        domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        domain.setAutoRange(true);
        domain.setLowerMargin(0.0);
        domain.setUpperMargin(0.0);
        domain.setTickLabelsVisible(true);
        
        NumberAxis range = new NumberAxis("Value");
        range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 8));
        range.setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, lineColor);
        //renderer.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        
        XYPlot plot = new XYPlot(dataset, domain, range, renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

        JFreeChart chart = new JFreeChart(chartTitle, new Font("SansSerif", Font.BOLD, 16), plot, true);
        chart.setBackgroundPaint(Color.WHITE);
        chart.removeLegend();
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createLineBorder(Color.WHITE)));
        chartPanel.setBackground(Color.white);
        
        this.add(chartPanel);
    }
    
    public void addData(Double data) {
        timeSeries.add(new Millisecond(), data);
    }
    public void addData(Double data, Date timestamp) {
        try {
            timeSeries.add(new Millisecond(timestamp), data);
        }
        catch(SeriesException e) {}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}