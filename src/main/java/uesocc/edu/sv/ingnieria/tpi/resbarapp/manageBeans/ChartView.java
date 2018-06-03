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
        model.setTitle("Ventas en el mes seleccionado");
        model.setLegendPosition("e");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Dias"));
        yAxis=model.getAxis(AxisType.Y);
        yAxis.setLabel("Monto en dolares");
        yAxis.setMin(0);
        yAxis.setMax(10000);
    }

    private LineChartModel initModel(){
        LineChartModel modelo = new LineChartModel();
        ChartSeries ventas = new ChartSeries();
        ventas.setLabel("Ventas");
        ventas.set("1", 2000);
        ventas.set("2", 4000);
        ventas.set("2", 3000);
        ventas.set("3", 1000);
        ventas.set("4", 7000);
        ventas.set("5", 2000);
        ventas.set("6", 4000);
        ventas.set("7", 5000);
        ventas.set("8", 3400);
        ventas.set("9", 5600);
        ventas.set("10", 7700);
        ventas.set("11", 4500);
        ventas.set("12", 3000);
        ventas.set("13", 2300);
        ventas.set("14", 3400);
        ventas.set("15", 5600);
        ventas.set("16", 4503);
        ventas.set("17", 4400);
        ventas.set("18", 5400);
        ventas.set("19", 2300);
        ventas.set("20", 4560);
        ventas.set("21", 3450);
        ventas.set("22", 3780);
        ventas.set("23", 2330);
        ventas.set("24", 3000);
        ventas.set("25", 5000);
        ventas.set("26", 7000);
        ventas.set("27", 8000);
        ventas.set("28", 9000);
        ventas.set("29", 1000);
        ventas.set("30", 1000);
        
        modelo.addSeries(ventas);
        
        return modelo;
    }
    
    
}
