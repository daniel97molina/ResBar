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
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

/**
 *
 * @author gochez
 */
@Named(value = "manejoEstadistica")
@ViewScoped
public class manejoEstadistica implements Serializable {

    private List<Orden> historico;
    private Date date1;
    private Date fecha1;
    private Date fecha2;
    String fechaSeleccionada;

    @PostConstruct
    private void init() {
        Date fechaInicial = new Date();
        Date fechaInicial1, fechaInicial2;
        
        fechaInicial1 = fechaInicial;
        fechaInicial1.setHours(0);
        fechaInicial1.setMinutes(0);
        fechaInicial1.setSeconds(0);
        System.out.println("FECHA1=" + fechaInicial1);
        fechaInicial2 = fechaInicial;
        fechaInicial2.setHours(23);
        fechaInicial2.setMinutes(59);
        fechaInicial2.setSeconds(59);
        System.out.println("fecha2=" + fechaInicial2);
        historico = ManejadorOrdenes.ObtenerVentas(fechaInicial1, fechaInicial2);
        PrimeFaces.current().ajax().update("tblVentas");
        System.out.println(historico);
    }

    public void onDateSelect(SelectEvent event) throws ParseException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", dateFormat.format(event.getObject())));
        fechaSeleccionada = dateFormat.format(event.getObject());
        fecha1 = dateFormat.parse(fechaSeleccionada);
        fecha1.setHours(0);
        fecha1.setMinutes(0);
        fecha1.setSeconds(0);
        System.out.println("FECHA1=" + fecha1);
        fecha2 = dateFormat.parse(fechaSeleccionada);
        fecha2.setHours(23);
        fecha2.setMinutes(59);
        fecha2.setSeconds(59);
        System.out.println("fecha2=" + fecha2);
        historico = ManejadorOrdenes.ObtenerVentas(fecha1, fecha2);
        System.out.println(historico);
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public List<Orden> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Orden> historico) {
        this.historico = historico;
    }

}
