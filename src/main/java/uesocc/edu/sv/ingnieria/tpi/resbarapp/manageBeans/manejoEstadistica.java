/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorCategorias;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorProductos;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

/**
 *
 * @author gochez
 */
@Named(value = "manejoEstadistica")
@ViewScoped
public class manejoEstadistica implements Serializable {

    private List<Orden> VentasDiarias;
    private Orden RegistroOrdenes;
    private Date dia;
//       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//     private Date dia;     
//
//    public manejoEstadistica() throws ParseException {
//        this.dia = sdf.parse("2018-05-20");
//    }
    public manejoEstadistica(){
    }
    //@PostConstruct
    public void ventasD() {
        
        this.VentasDiarias = ManejadorOrdenes.ObtenerVentas(dia);
    }
    
    public void ObtenerObjeto(SelectEvent se) {

        this.RegistroOrdenes = (Orden)(se.getObject());
    }

    public List<Orden> getVentasDiarias() {
        return VentasDiarias;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
                this.VentasDiarias = ManejadorOrdenes.ObtenerVentas(dia);

    }


    public void setVentasDiarias(List<Orden> VentasDiarias) {
        this.VentasDiarias = VentasDiarias;
    }

    public Orden getRegistroOrdenes() {
        return RegistroOrdenes;
    }

    public void setRegistroOrdenes(Orden RegistroOrdenes) {
        this.RegistroOrdenes = RegistroOrdenes;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }


}
