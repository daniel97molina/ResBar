/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorProductos;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrdenPK;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
 *
 * @author danm
 */
@Named(value = "ordenView")
@ViewScoped
public class OrdenView implements Serializable {

    private int id;
    private Orden orden;
    private boolean creandoNuevo = true;

    public OrdenView() {
    }

    @PostConstruct
    protected void init() {
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().getOrDefault("id", "0"));
        if (id == 0) {
            creandoNuevo = true;
            orden = new Orden();
            orden.fecha = new Date();
        } else {
            try {
                orden = ManejadorOrdenes.Obtener(id);
                creandoNuevo = false;
            } catch (Exception e) {
                orden = new Orden();
                creandoNuevo = true;
            }
        }
    }

    public void confirmar() {
        
        //this.calcularTotalLocal();
        try {
            System.out.println("CREANDO" + creandoNuevo);
            if (creandoNuevo) {
                this.orden.idOrden = ManejadorOrdenes.ObtenerId();
                ManejadorOrdenes.Insertar(this.orden);
            } else {
                ManejadorOrdenes.Actualizar(this.orden);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("Ordenes.jsf");
        } catch (Exception e) {
            //TODO mostrar mensaje de error growl
        }
    }

    public void reducirDetalle(int id) {
        System.out.println("id" + id);
        for (DetalleOrden d : this.orden.detalle) {
            if (Objects.equals(d.producto.idProducto, id)) {
                if (d.cantidad.compareTo(BigDecimal.ONE) > 0) {
                    d.cantidad = d.cantidad.subtract(new BigDecimal(1));
                    System.out.println("reduciendo");
                    this.calcularTotalLocal();
                }
            }
        }
        soutDetalle();   
    }
    
    public void eliminarDetalle(int id){
        for(int i =0 ; i<this.orden.detalle.size(); i++){
            if(this.orden.detalle.get(i).producto.idProducto.equals(id)){
                this.orden.detalle.remove(i);
                break;
            }
        }
    }
    
    public void agregarDetalle(int id, int cantidad) {
        System.out.println("id" + id);
        boolean encontrado = false;
        for (DetalleOrden d : this.orden.detalle) {
            if (Objects.equals(d.producto.idProducto, id)) {
                encontrado = true;
                d.cantidad = d.cantidad.add(new BigDecimal(cantidad));
                System.out.println("sumando");
            }
        }
        if (!encontrado) {
            DetalleOrden det = new DetalleOrden();
            det.orden = this.orden;
            det.producto = ManejadorProductos.Obtener(id);
            det.cantidad = new BigDecimal(cantidad);
            this.orden.detalle.add(det);
            System.out.println("creando detalle");
        }
        soutDetalle();
        this.calcularTotalLocal();
    }
    
    private void calcularTotalLocal(){
        BigDecimal dec = new BigDecimal(BigInteger.ZERO);
        for(DetalleOrden d: this.orden.detalle){
            dec = d.cantidad.add(dec);
        }
        this.orden.setTotal(dec);
    }

    private void soutDetalle() {
        System.out.println("Cantidades *********");
        for (DetalleOrden d : this.orden.detalle) {
            System.out.println(d.cantidad);
        }
        System.out.println("TOTAL "+this.orden.total);
    }

    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

}
