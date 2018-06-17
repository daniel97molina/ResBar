/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorProductos;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorAplicacion;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrdenPK;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

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
    private List<DetalleOrden> detallesAgregar = new ArrayList<>();

    public OrdenView() {
    }

    @PostConstruct
    protected void init() {
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().getOrDefault("id", "0"));
        if (id == 0) {
            creandoNuevo = true;
            orden = new Orden();
            orden.fecha = new Date();
            orden.detalle = new ArrayList<>();
        } else {
            try {
                orden = ManejadorOrdenes.Obtener(id);
                creandoNuevo = false;
            } catch (Exception e) {
                creandoNuevo = true;
                orden = new Orden();
                orden.fecha = new Date();
                orden.detalle = new ArrayList<>();
            }
        }
    }

    private void crearMensaje(String header, String message, boolean exito) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(exito ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR, header, message));

    }

    public void confirmar() {

        this.calcularTotalLocal();
        try {
            this.orden.detalle.forEach(d -> {
                d.orden = this.orden;
                d.detalleOrdenPK.idOrden = this.orden.idOrden;
            });
            if (creandoNuevo) {
                this.orden.idOrden = ManejadorOrdenes.ObtenerId();

                ManejadorOrdenes.Insertar(this.orden);
                crearMensaje("Exito", "Orden creada", true);
            } else {

                ManejadorOrdenes.Actualizar(this.orden);
                crearMensaje("Exito", "Orden actualizada", true);
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("Ordenes.jsf");
        } catch (ErrorAplicacion ea) {
            String[] v = ea.getMessage().split("\\$");
            if (v.length > 1) {
                crearMensaje("Error", v[1], false);
            } else {
                crearMensaje("Error", ea.getMessage(), false);
            }
        } catch (Exception e) {
            crearMensaje("Error", e.getMessage(), false);
        }
    }

    public void confirmarAgregarProductos() {
        this.detallesAgregar.forEach((d) -> {
            agregarDetalle(d.producto.idProducto, Integer.parseInt(d.cantidad.toString()), this.orden.detalle);
        });
        System.out.println("confirmar, productos:");
        this.orden.detalle.forEach(d -> {
            System.out.println("id:" + d.producto.idProducto + "nombre:" + d.producto.nombre);
        });
        this.calcularTotalLocal();
        this.detallesAgregar.clear();
    }

    public int obtenerCantidadAgregar(int idProducto) {
        for (DetalleOrden d : detallesAgregar) {
            if (d.producto.idProducto.equals(idProducto)) {
                return Integer.parseInt(d.cantidad.toString());
            }
        }
        return 0;
    }

    public void cancelarAgregarProductos() {
        this.detallesAgregar.clear();
    }

    public void reducirDetalle(int id, List<DetalleOrden> detalles) {
        System.out.println("id" + id);
        for (DetalleOrden d : detalles) {
            if (Objects.equals(d.producto.idProducto, id)) {
                if (d.cantidad.compareTo(BigDecimal.ONE) > 0) {
                    d.cantidad = d.cantidad.subtract(new BigDecimal(1));
                    System.out.println("reduciendo");
                    this.calcularTotalLocal();
                } else {
                    detalles.remove(d);
                }
                break;
            }
        }
        soutDetalle();
    }

    public void eliminarDetalle(int id, List<DetalleOrden> detalle) {
        System.out.println("id eliminar: " + id);
        for (int i = 0; i < detalle.size(); i++) {
            if (detalle.get(i).producto.idProducto.equals(id)) {
                detalle.remove(i);
                break;
            }
        }
        calcularTotalLocal();
    }

    public void agregarDetalle(int id, int cantidad, List<DetalleOrden> detalles) {
        System.out.println("id" + id);
        boolean encontrado = false;
        for (DetalleOrden d : detalles) {
            if (Objects.equals(d.producto.idProducto, id)) {
                encontrado = true;
                d.cantidad = d.cantidad.add(new BigDecimal(cantidad));
                System.out.println("sumando");
            }
        }
        if (!encontrado) {
            DetalleOrden det = new DetalleOrden();
            //det.orden = this.orden;
            det.producto = ManejadorProductos.Obtener(id);
            det.cantidad = new BigDecimal(cantidad);

            DetalleOrdenPK dopk = new DetalleOrdenPK();

            dopk.idProducto = det.producto.idProducto;
            det.detalleOrdenPK = dopk;
            detalles.add(det);
            System.out.println("creando detalle");
        }
        soutDetalle();
        this.calcularTotalLocal();
    }

    private void calcularTotalLocal() {
        BigDecimal dec = new BigDecimal(BigInteger.ZERO);
        for (DetalleOrden d : this.orden.detalle) {
            dec = d.cantidad.add(dec);
        }
        this.orden.setTotal(dec);
    }

    private void soutDetalle() {
        System.out.println("Cantidades *********");
        this.orden.detalle.forEach((d) -> {
            System.out.println(d.cantidad);
        });
        System.out.println("TOTAL " + this.orden.total);
    }

    public boolean isCreandoNuevo() {
        return creandoNuevo;
    }

    public void setCreandoNuevo(boolean creandoNuevo) {
        this.creandoNuevo = creandoNuevo;
    }

    public List<DetalleOrden> getDetallesAgregar() {
        return detallesAgregar;
    }

    public void setDetallesAgregar(List<DetalleOrden> detallesAgregar) {
        this.detallesAgregar = detallesAgregar;
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
