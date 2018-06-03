/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;
/**
 *
 * @author danm
 */
@ManagedBean
@Named(value = "ordenView")
@ViewScoped
public class OrdenView implements Serializable{
    
    private int id;
    private Orden orden = ManejadorOrdenes.Obtener(1);
    
    public OrdenView() {
    }
    
    @PostConstruct
    public void init(){
       if(id==0){
           this.setId(100);
       }else{
           this.setId(200);
       }
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
