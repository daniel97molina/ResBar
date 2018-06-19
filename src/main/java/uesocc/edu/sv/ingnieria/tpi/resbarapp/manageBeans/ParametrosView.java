/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorParametros;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;

/**
 *
 * @author irvin
 */
@ManagedBean
@Named(value = "parametrosView")
@ViewScoped
public class ParametrosView {

    public ParametrosView() {
    }
    
    Parametro nombre = ManejadorParametros.Obtener(1);
    Parametro direccion = ManejadorParametros.Obtener(2);
    Parametro telefono = ManejadorParametros.Obtener(3);
    Parametro nit = ManejadorParametros.Obtener(4);
    Parametro nrc = ManejadorParametros.Obtener(5);
    Parametro giro = ManejadorParametros.Obtener(6);
    Parametro email = ManejadorParametros.Obtener(7);

    public Parametro getNombre() {
        return nombre;
    }

    public void setNombre(Parametro nombre) {
        this.nombre = nombre;
    }

    public Parametro getDireccion() {
        return direccion;
    }

    public void setDireccion(Parametro direccion) {
        this.direccion = direccion;
    }

    public Parametro getTelefono() {
        return telefono;
    }

    public void setTelefono(Parametro telefono) {
        this.telefono = telefono;
    }

    public Parametro getNit() {
        return nit;
    }

    public void setNit(Parametro nit) {
        this.nit = nit;
    }

    public Parametro getNrc() {
        return nrc;
    }

    public void setNrc(Parametro nrc) {
        this.nrc = nrc;
    }

    public Parametro getGiro() {
        return giro;
    }

    public void setGiro(Parametro giro) {
        this.giro = giro;
    }

    public Parametro getEmail() {
        return email;
    }

    public void setEmail(Parametro email) {
        this.email = email;
    }    
    
    
}
