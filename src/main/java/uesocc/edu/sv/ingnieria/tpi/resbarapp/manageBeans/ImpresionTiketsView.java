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

    public void imprimirCB(int idOrden) {

        byte[] cutP = new byte[]{0x1d, 'V', 1};

        nueva = ManejadorOrdenes.Obtener(idOrden);
        List<DetalleOrden> det = new ArrayList<DetalleOrden>();
        det = nueva.detalle;

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
        impresionC += "Cliente:" + nueva.cliente + "\t\tFecha: " + nueva.fecha + "\n";
        impresionC += "# de ticket:" + nueva.idOrden + "\t\t\tMesa:" + nueva.mesa + "\n";
        //impresionC += "Hora:12:45\n";
        impresionC += "------------------------------------------------\n";
        impresionC += " Concepto:\t\t\t Cantidad\n";

        for (DetalleOrden detalleOrden : cocina) {
            impresionC += detalleOrden.producto.nombre + "\t " + detalleOrden.cantidad + "\n";
        }

//        impresionC += " Coca-cola:\t\t\t2\n";
//        impresionC += " Boquitas :\t\t\t2\n";
        impresionC += "------------------------------------------------\n";
        impresionC += "Comentarios:" + nueva.comentario + "\n\n\n";
//        impresionC += "Total: $ " + nueva.total + "\n";
        impresionC += "------------------------------------------------\n\n\n\n\n";

        printerService.printString("POS-80C", impresionC);
        printerService.printBytes("POS-80C", cutP);

        impresionB += "------------------------------------------------\n";
        impresionB += "Cliente:" + nueva.cliente + "\t\tFecha: " + nueva.fecha + "\n";
        impresionB += "# de ticket:" + nueva.idOrden + "\t\t\tMesa:" + nueva.mesa + "\n";

//        Calendar calendar = Calendar.getInstance(nueva.fecha.setTime());
//        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss").format(nueva.fecha);
        //impresionB += "Hora:12:45\n";
        impresionB += "------------------------------------------------\n";
        impresionB += " Concepto:\t\t\t Cantidad\n";
        for (DetalleOrden detalleOrden : cocina) {
            impresionC += "*" + detalleOrden.producto.nombre + "\t " + detalleOrden.cantidad + "\n";
        }
        //impresionB += " Coca-cola:\t\t\t2\n";
        //impresionB += " Boquitas :\t\t\t2\n";
        impresionB += "------------------------------------------------\n";
        impresionB += "Comentarios:" + nueva.comentario + " \n\n\n";
//        impresionB += "Total: $ " + nueva.total + "\n";
        impresionB += "------------------------------------------------\n\n\n\n\n";

        printerService.printString("POS-80C", impresionB);
        printerService.printBytes("POS-80C", cutP);
    }

    public void imprimirP(int idOrden) {
        List<Parametro> datos = ManejadorParametros.Obtener();
        List<DetalleOrden> det = new ArrayList<DetalleOrden>();
        det = nueva.detalle;

        impresionP += "------------------------------------------------\n";
        for (Parametro dato : datos) {
            impresionP += dato.valor + "\n";
        }

//        impresionP += "Nombre de la empresa\n";
//        impresionP += "0210-031195-103-5\n";
//        impresionP += "Dirección de la empresa\n";
//        impresionP += "2015-1352 ; \t75119785\n";
//        impresionP += "correoelectronico@gmail.com\n";
        impresionP += "------------------------------------------------\n";
        impresionP += " Concepto:\t Cantidad\t Precio\n";
        for (DetalleOrden detalleOrden : det) {
            impresionP += detalleOrden.producto.nombre + "\t " + detalleOrden.cantidad + "\t" + detalleOrden.producto.precio + "\n";
        }
//        impresionP += " Coca-cola:\t\t2\t$1.50\n";
//        impresionP += " Boquitas: \t\t2\t$4.50\n";
        impresionP += "------------------------------------------------\n";
//        impresionP += "total $:6.50\n";
        impresionP += "Total: $" + nueva.total;
        impresionP += "------------------------------------------------\n\n\n\n\n";

        byte[] cutP = new byte[]{0x1d, 'V', 1};
        printerService.printString("POS-80C", impresionP);
        printerService.printBytes("POS-80C", cutP);
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
