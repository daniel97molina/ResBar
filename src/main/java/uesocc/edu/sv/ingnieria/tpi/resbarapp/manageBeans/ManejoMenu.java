/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorCategorias;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorProductos;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
 *
 * @author zaldivar
 */
@Named(value = "manejoMenu")
@ViewScoped
public class ManejoMenu implements Serializable {

    private Categoria registroCategoria;
    private Producto registroProducto;
    private List<Categoria> modeloCategoria;
    private List<Producto> modeloProducto;
    private boolean mostrar;

    public ManejoMenu() {
    }

    @PostConstruct
    public void init() {
        this.modeloCategoria = ManejadorCategorias.Obtener(false);
    }

    public void filtroCategoria(SelectEvent se) {
        this.mostrar = true;

        this.registroCategoria = (Categoria) (se.getObject());
        this.modeloProducto = ManejadorProductos.ObtenerxCategoria(this.registroCategoria.idCategoria);
    }

    public void guardarCategoria(ActionEvent ae) {
        if (this.registroCategoria != null) {
            this.mostrar = false;
            this.registroCategoria.idCategoria=ManejadorCategorias.ObtenerId();
            ManejadorCategorias.Insertar(this.registroCategoria);
        }
    }

    public void guardarProducto(ActionEvent ae) {
        if (this.registroProducto != null && this.registroCategoria != null) {
            this.registroProducto.categoria = this.registroCategoria;
            this.registroProducto.idProducto = ManejadorProductos.ObtenerID();
            ManejadorProductos.Insertar(this.registroProducto);
            this.modeloProducto = ManejadorProductos.ObtenerxCategoria(this.registroCategoria.getIdCategoria());
        }
    }

    public void eliminarProducto(ActionEvent ae) {
        if (this.registroProducto != null) {
            ManejadorProductos.Eliminar(this.registroProducto);
            this.modeloProducto = ManejadorProductos.ObtenerxCategoria(this.registroCategoria.getIdCategoria());

        }
    }

    public void eliminarCategoria(ActionEvent ae) {
        if (this.registroCategoria != null) {
            this.mostrar = false;
            
            ManejadorCategorias.Eliminar(this.registroCategoria);
        }
    }

    public void modificarCategoria(ActionEvent ae) {
        if (this.registroCategoria != null) {
            this.mostrar = false;

            ManejadorCategorias.Actualizar(this.registroCategoria);
        }
    }

    public void modificarProducto(ActionEvent ae) {
        if (this.registroProducto != null) {
            ManejadorProductos.Actualizar(this.registroProducto);
        }
    }

    public void nuevaCategoria(ActionEvent ae) {
        this.registroCategoria = new Categoria();
        this.mostrar = false;
    }

    public void nuevoProducto(ActionEvent ae) {
        this.registroProducto = new Producto();
        System.out.println("entre nuevo prod :" + this.registroProducto);
    }

    public Categoria getRegistroCategoria() {
        return registroCategoria;
    }

    public void setRegistroCategoria(Categoria registroCategoria) {
        this.registroCategoria = registroCategoria;
    }

    public Producto getRegistroProducto() {
        return registroProducto;
    }

    public void setRegistroProducto(Producto registroProducto) {
        this.registroProducto = registroProducto;
    }

    public List<Categoria> getModeloCategoria() {
        return modeloCategoria;
    }

    public void setModeloCategoria(List<Categoria> modeloCategoria) {
        this.modeloCategoria = modeloCategoria;
    }

    public List<Producto> getModeloProducto() {
        return modeloProducto;
    }

    public void setModeloProducto(List<Producto> modeloProducto) {
        this.modeloProducto = modeloProducto;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

}
