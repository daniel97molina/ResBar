/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorParametros;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;
import uesocc.edu.sv.ingenieria.dsi.resbarapp.impresora.PrinterService;

/**
 *
 * @author irvin
 */
@ManagedBean
@Named(value = "impresionTiketsView")
@ViewScoped
public class ImpresionTiketsView {

    PrinterService printerService = new PrinterService();
    Orden o = new Orden();
    String impresionC = "\t\tCocina\n";
    String impresionB = "\t\tBebida\n";
    String impresionP = "\t\tTicket de venta\n";

    Orden nueva;

    public void imprimirCB(int idOrden, List<DetalleOrden> det) {

        byte[] cutP = new byte[]{0x1d, 'V', 1};
        nueva = ManejadorOrdenes.Obtener(idOrden);
//        List<DetalleOrden> det = new ArrayList<>();
//        det = nueva.detalle;

        List<DetalleOrden> bebida = new ArrayList<>();
        List<DetalleOrden> cocina = new ArrayList<>();
        for (DetalleOrden detalleOrden : det) {
            if (detalleOrden.producto.area == 'B') {
                bebida.add(detalleOrden);
            } else {
                cocina.add(detalleOrden);
            }
        }
        impresionC += "------------------------------------------------\n";
        impresionC += "Cliente: " + nueva.cliente + "\t\tMesero: " + nueva.mesero + "\n";
        impresionC += "# de ticket: " + nueva.idOrden + "\t\t\tMesa: " + nueva.mesa + "\n";
        impresionC += "Fecha y hora: " + nueva.fecha + "\n";
        //impresionC += "Hora:12:45\n";
        impresionC += "------------------------------------------------\n";
        impresionC += " Concepto:\t\t\t Cantidad\n";
        impresionC += "------------------------------------------------\n";

        for (DetalleOrden detalleOrden : cocina) {
            if (detalleOrden.producto.nombre.length() >= 12) {
                impresionC += detalleOrden.producto.nombre + "\t\t " + detalleOrden.cantidad + "\n";

            } else {
                impresionC += detalleOrden.producto.nombre + "\t\t\t " + detalleOrden.cantidad + "\n";
            }
        }

//        impresionC += " Coca-cola:\t\t\t2\n";
//        impresionC += " Boquitas :\t\t\t2\n";
        impresionC += "------------------------------------------------\n";
        impresionC += "Comentarios: " + nueva.comentario + "\n\n";
//        impresionC += "Total: $ " + nueva.total + "\n";
        impresionC += "------------------------------------------------\n\n\n\n\n";

        printerService.printString("POS-80C", impresionC);
        printerService.printBytes("POS-80C", cutP);
        
        impresionB += "------------------------------------------------\n";
        impresionB += "Cliente: " + nueva.cliente + "\t\tMesero: " + nueva.mesero + "\n";
        impresionB += "# de ticket: " + nueva.idOrden + "\t\t\tMesa: " + nueva.mesa + "\n";
        impresionB += "Fecha y hora: " + nueva.fecha + "\n";

//        Calendar calendar = Calendar.getInstance(nueva.fecha.setTime());
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss").format(nueva.fecha);
        //impresionB += "Hora:12:45\n";
        impresionB += "------------------------------------------------\n";
        impresionB += " Concepto:\t\t\t Cantidad\n";
        impresionB += "------------------------------------------------\n";
        for (DetalleOrden detalleOrden : bebida) {
            if (detalleOrden.producto.nombre.length() >= 12) {
                impresionB += detalleOrden.producto.nombre + "\t\t " + detalleOrden.cantidad + "\n";

            } else {
                impresionB += detalleOrden.producto.nombre + "\t\t\t " + detalleOrden.cantidad + "\n";
            }
        }
        //impresionB += " Coca-cola:\t\t\t2\n";
        //impresionB += " Boquitas :\t\t\t2\n";
        impresionB += "------------------------------------------------\n";
        impresionB += "Comentarios: " + nueva.comentario + " \n\n";
//        impresionB += "Total: $ " + nueva.total + "\n";
        impresionB += "------------------------------------------------\n\n\n\n\n";

        printerService.printString("POS-80C", impresionB);
        printerService.printBytes("POS-80C", cutP);
        System.out.println(impresionC);
        System.out.println(impresionB);
    }

    public void imprimirP(Orden orden, double pago) {
        List<Parametro> datos = ManejadorParametros.Obtener();

        List<DetalleOrden> det = new ArrayList<>();
        det = orden.detalle;

        impresionP += "------------------------------------------------\n";
        for (Parametro dato : datos) {
            impresionP += dato.valor + "\n";
        }
        impresionP += "------------------------------------------------\n";
        impresionP += "Cliente: " + orden.cliente + "\n";
        impresionP += "Mesa: " + orden.mesa + "\n";
        impresionP += "Mesero: " + orden.mesero + "\n";
        impresionP += "Fecha y hora: " + orden.fecha + "\n";

//        impresionP += "Nombre de la empresa\n";
//        impresionP += "0210-031195-103-5\n";
//        impresionP += "Dirección de la empresa\n";
//        impresionP += "2015-1352 ; \t75119785\n";
//        impresionP += "correoelectronico@gmail.com\n";
        impresionP += "------------------------------------------------\n";
        impresionP += " Concepto:\t\t Cantidad\t Precio\n";
        for (DetalleOrden detalleOrden : det) {
            if (detalleOrden.producto.nombre.length() >= 12) {
                impresionP += detalleOrden.producto.nombre + "\t " + detalleOrden.cantidad + "\t\t" + detalleOrden.producto.precio + "\n";
            } else {
                impresionP += detalleOrden.producto.nombre + "\t\t " + detalleOrden.cantidad + "\t\t" + detalleOrden.producto.precio + "\n";
            }

        }
//        impresionP += " Coca-cola:\t\t2\t$1.50\n";
//        impresionP += " Boquitas: \t\t2\t$4.50\n";
        impresionP += "------------------------------------------------\n";
//        impresionP += "total $:6.50\n";
        impresionP += "Total: $\t" + orden.total + "\n";
        if (pago > 0) {
            impresionP += "Efectivo: $\t" + pago + "\n";
            impresionP += "Cambio: $\t" + (pago - Double.parseDouble(orden.total.toString())) + "\n";
        }
        impresionP += "------------------------------------------------\n\n\n\n\n";

        byte[] cutP = new byte[]{0x1d, 'V', 1};
        printerService.printString("POS-80C", impresionP);
        printerService.printBytes("POS-80C", cutP);
        System.out.println(impresionP);
    }

//    byte[] cutP = new byte[]{0x1d, 'V', 1};
//
//    printerService.printString (
//
//    "POS-80C", impresionP);
//    printerService.printBytes (
//    "POS-80C", cutP);
//        byte[] cutP = new byte[]{0x1d, 'V', 1};
////        printerService.printString("POS-80C", impresionC);
////        printerService.printBytes("POS-80C", cutP);
////        printerService.printString("POS-80C", impresionB);
////        printerService.printBytes("POS-80C", cutP);
//
//    printerService.printString (
//
//    "POS-80C", impresionP);
//    printerService.printBytes (
//
//"POS-80C", cutP);
//        byte[] cutP2 = new byte[]{0x1d, 'V', 1};
//        printerService.printString("POS-80C", impresionB);
//        printerService.printBytes("POS-80C", cutP2);
//        byte[] cutP3 = new byte[]{0x1d, 'V', 1};
//        printerService.printString("POS-80C", impresionP);
//        printerService.printBytes("POS-80C", cutP3);
}
