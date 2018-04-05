/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author zaldivar
 */
@ManagedBean
public class ChartView implements Serializable{

    /**
     * Creates a new instance of ChartView
     */
    private LineChartModel model;
    
    public ChartView() {
    }
    @PostConstruct
    public void init(){
        createModels();           
    }

    public LineChartModel getModel() {
        return model;
    }
    
    private void createModels(){
        Axis yAxis;
        model=initModel();
        model.setTitle("Ventas dede el 2010");
        model.setLegendPosition("e");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Años"));
        yAxis=model.getAxis(AxisType.Y);
        yAxis.setLabel("Monto en dolares");
        yAxis.setMin(0);
        yAxis.setMax(10000);
    }
    
    private LineChartModel initModel(){
        LineChartModel modelo = new LineChartModel();
        ChartSeries ventas = new ChartSeries();
        ventas.setLabel("Ventas");
        ventas.set("2010", 2000);
        ventas.set("2011", 4000);
        ventas.set("2012", 3000);
        ventas.set("2013", 1000);
        ventas.set("2014", 7000);
        ventas.set("2015", 2000);
        ventas.set("2016", 4000);
        ventas.set("2017", 5000);
        ventas.set("2018", 1000);
        
        modelo.addSeries(ventas);
        
        return modelo;
    }
    
    
}
