/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import java.text.DecimalFormat;
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
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

/**
 *
 * @author irvin
 */
@ManagedBean
@Named(value = "ordenesActivasView")
@ViewScoped
public class OrdenesActivasView implements Serializable {

    private LazyDataModel<Orden> modelo;
    private Orden registro;
    private double valorSpinner;
    private double cambio;
    private double subTotalDetalle;
    private List<Orden> listaOrdenes;
    private List<DetalleOrden> listaDetalles;
    private Orden orden;
    private String dato;

    /**
     * Creates a new instance of OrdenesActivasview
     */
    public OrdenesActivasView() {
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
                        salida = ManejadorOrdenes.ObtenerActivas();
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

    public void calcularCambio() {
        this.setCambio(this.valorSpinner - Double.parseDouble(this.getRegistro().getTotal().toString()));
    }

    public void mostrarDetalles(int idOrden) {
        this.setOrden(ManejadorOrdenes.Obtener(idOrden));
        this.setListaDetalles(this.orden.detalle);
    }

    //Aun no usada
    public void buscarOrdenes(final String dato) {
        //this.setListaOrdenes(ManejadorOrdenes.BuscarActivas(dato));
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
                        this.setRowCount(ManejadorOrdenes.BuscarActivas(dato).size());
                        salida = ManejadorOrdenes.BuscarActivas(dato);
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

    //Getter y Setter
    public double getCambio() {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(cambio));
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public double getValorSpinner() {
        return valorSpinner;
    }

    public void setValorSpinner(double valorSpinner) {
        this.valorSpinner = valorSpinner;
    }

    public List<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<Orden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
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

    public List<DetalleOrden> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleOrden> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    //No usado
    public double getSubTotalDetalle() {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(subTotalDetalle));
    }

    public void setSubTotalDetalle(double subTotalDetalle) {
        this.subTotalDetalle = subTotalDetalle;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void inhabilitar(Orden orden){
        orden.estado = false;
        ManejadorOrdenes.Actualizar(orden);
        ImpresionTiketsView impTicket = new ImpresionTiketsView();
        impTicket.imprimirP(this.registro,this.valorSpinner);
    }
    
}
