/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author shiba
 */
@Named(value = "avisoBean")
@SessionScoped
public class AvisoBean implements Serializable {

    boolean desplegarCreacion;
    
    /**
     * Creates a new instance of AvisoBean
     */
    public AvisoBean() {
    }
    
    public String cargar()
    {
        return "aviso";
    }

    public boolean isDesplegarCreacion() {
        return desplegarCreacion;
    }

    public void setDesplegarCreacion(boolean desplegarCreacion) {
        this.desplegarCreacion = desplegarCreacion;
    }
    
    
}
