/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingnieria.tpi.resbarapp.manageBeans;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorCategorias;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;

/**
 *
 * @author zaldivar
 */
@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.isEmpty()) {
            int intValue;
            try {
                Categoria categoria = null;

                intValue = Integer.parseInt(value);
                if (intValue > 0) {
                    List<Categoria> collection;
                    collection = ManejadorCategorias.Obtener(true);
                    for (Categoria cat : collection) {
                        if (cat.idCategoria == intValue) {
                            categoria = cat;
                        }
                    }
                }
                return categoria;
            } catch (NumberFormatException ne) {
                throw new ConverterException();
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value!=null){
            return String.valueOf(((Categoria)value).idCategoria);
        }
        else{
            return null;
        }
    }

}
