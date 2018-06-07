/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private boolean creando=true;

    public OrdenView() {
    }

    @PostConstruct
    protected void init() {
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().getOrDefault("id", "0"));
        if (id == 0) {
            creando = true;
            orden = new Orden();
            orden.fecha = new Date();
        } else {
            try {
                orden = ManejadorOrdenes.Obtener(id);
                creando = true;
            }catch (Exception e){
                orden = new Orden();
                creando = false;
            }
            }
        }
    

    public void reducirDetalle(int id){
        System.out.println("id"+id);
        for (DetalleOrden d : this.orden.detalle) {
            if (Objects.equals(d.producto.idProducto, id)) {
                //TODO si es mayor a uno
                d.cantidad = d.cantidad.subtract(new BigDecimal(1));
                System.out.println("reduciendo");
            }
        }
        soutDetalle();
    }
    
    private void soutDetalle(){
        System.out.println("Cantidades *********");
        for(DetalleOrden d : this.orden.detalle){
            System.out.println(d.cantidad);
        }
    }

    public void agregarDetalle(int id, int cantidad){
        System.out.println("id"+id);
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
