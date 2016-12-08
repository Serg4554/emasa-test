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
    //parametros necesarios para crear un aviso
    String ubicacion;
    String estado;
    String observaciones;
    String ubicacionTecnica;
    String prioridad;
    String inicioReparacionDia;
    String inicioReparacionMes;
    String inicioReparacionAnio;
    String finReparacionDia;
    String finReparacionMes;
    String finReparacionAnio;
    String posicionGPS;
    String tipo;
    
    
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUbicacionTecnica() {
        return ubicacionTecnica;
    }

    public void setUbicacionTecnica(String ubicacionTecnica) {
        this.ubicacionTecnica = ubicacionTecnica;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getInicioReparacionDia() {
        return inicioReparacionDia;
    }

    public void setInicioReparacionDia(String inicioReparacionDia) {
        this.inicioReparacionDia = inicioReparacionDia;
    }

    public String getInicioReparacionMes() {
        return inicioReparacionMes;
    }

    public void setInicioReparacionMes(String inicioReparacionMes) {
        this.inicioReparacionMes = inicioReparacionMes;
    }

    public String getInicioReparacionAnio() {
        return inicioReparacionAnio;
    }

    public void setInicioReparacionAnio(String inicioReparacionAnio) {
        this.inicioReparacionAnio = inicioReparacionAnio;
    }

    public String getFinReparacionDia() {
        return finReparacionDia;
    }

    public void setFinReparacionDia(String finReparacionDia) {
        this.finReparacionDia = finReparacionDia;
    }

    public String getFinReparacionMes() {
        return finReparacionMes;
    }

    public void setFinReparacionMes(String finReparacionMes) {
        this.finReparacionMes = finReparacionMes;
    }

    public String getFinReparacionAnio() {
        return finReparacionAnio;
    }

    public void setFinReparacionAnio(String finReparacionAnio) {
        this.finReparacionAnio = finReparacionAnio;
    }

    public String getPosicionGPS() {
        return posicionGPS;
    }

    public void setPosicionGPS(String posicionGPS) {
        this.posicionGPS = posicionGPS;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void desplegarCreacion()
    {
        this.desplegarCreacion=!desplegarCreacion;
    }
}
