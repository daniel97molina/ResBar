/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import java.util.Map;
import org.primefaces.model.SortOrder;
import java.util.ArrayList;

import javax.inject.Named;
//import javax.faces.view.ViewScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.LazyDataModel;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

/**
 *
 * @author irvin
 */
@ManagedBean
@Named(value = "ordenesActivasView")
@ViewScoped
public class OrdenesActivasView implements Serializable{

    private LazyDataModel<Orden> modelo;
    private Orden registro;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    /**
     * Creates a new instance of OrdenesActivasview
     */
    public OrdenesActivasView() {
    }

    public LazyDataModel<Orden> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Orden> modelo) {
        this.modelo = modelo;
    }

    public Orden getRegistro() {
        return registro;
    }

    public void setRegistro(Orden registro) {
        this.registro = registro;
    }

    @PostConstruct
    private void init() {
        try {
            this.modelo = new LazyDataModel<Orden>() {
                @Override
                public Object getRowKey(Orden object) {
                    if (object != null) {
                        return object.getIdOrden();
                    }
                    return null;
                }

                @Override
                public Orden getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (Orden reg : (List<Orden>) getWrappedData()) {
                                if (reg.getIdOrden().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                    return null;
                }

                @Override
                public List<Orden> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<Orden> salida = new ArrayList();
                    try {
                        this.setRowCount(ManejadorOrdenes.ObtenerActivas().size());        
                        salida= ManejadorOrdenes.ObtenerActivas();
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                    return salida;
                }

            };
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }


}
