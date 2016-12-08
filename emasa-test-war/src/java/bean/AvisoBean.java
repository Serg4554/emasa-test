/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import avisows.Aviso;
import avisows.AvisoWS_Service;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.WebServiceRef;
import usuariows.Usuario;
import usuariows.UsuarioWS_Service;

/**
 *
 * @author shiba
 */
@Named(value = "avisoBean")
@SessionScoped
public class AvisoBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Emasa-Soap-war/UsuarioWS.wsdl")
    private UsuarioWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Emasa-Soap-war/AvisoWS.wsdl")
    private AvisoWS_Service service;

    boolean desplegarCreacion;
    //parametros necesarios para crear un aviso
    private String ubicacion;
    private String estado;
    private String observaciones;
    private String ubicacionTecnica;
    private String prioridad;
    private String inicioReparacionDia;
    private String inicioReparacionMes;
    private String inicioReparacionAnio;
    private String finReparacionDia;
    private String finReparacionMes;
    private String finReparacionAnio;
    private String posicionGPS;
    private String tipo;
    private String Usuario;
    
    private String contador;
    private List<Aviso> listaAvisos;
    private Aviso avisoEditado;
    
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

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContador() {
        return contador;
    }

    public List<Aviso> getListaAvisos() {
        return listaAvisos;
    }

    public void setListaAvisos(List<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }
    
    public void desplegarCreacion()
    {
        this.desplegarCreacion=!desplegarCreacion;
    }
    
    public String creacionAviso()
    {
        Aviso nuevoAviso = new Aviso();
        //asignamos fecha
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        formatter.applyPattern("dd-MM-yyyy");
        Date fecha = new Date();
        try {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(fecha);
            nuevoAviso.setFechacreacion(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        } catch (DatatypeConfigurationException e) {

        }
        nuevoAviso.setEstado(estado);
        nuevoAviso.setObservaciones(observaciones);
        nuevoAviso.setTipo(tipo);
        nuevoAviso.setPosGPS(posicionGPS);
        nuevoAviso.setPrioridad(Integer.parseInt(prioridad));
        nuevoAviso.setUbicacion(ubicacion);
        nuevoAviso.setUbicacionTecnica(ubicacionTecnica);
        usuariows.Usuario user = find(Usuario);
        avisows.Usuario usuario = new avisows.Usuario();
        usuario.setEmail(user.getEmail());
        usuario.setOperador(user.isOperador());
        nuevoAviso.setUsuarioemail(usuario);
        
        if (avisoEditado==null)
        {    
            create(nuevoAviso);
        }
        else
        {
            nuevoAviso.setId(avisoEditado.getId());
            edit(nuevoAviso);
        }
        desplegarCreacion(); //ocultamos los controles de creación
        doListar();
        avisoEditado=null;
        ponerParametrosaNull();
        return "aviso";
    }
    
    public void contar()
    {
        contador = Integer.toString(count());
    }

    private void create(avisows.Aviso entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        avisows.AvisoWS port = service.getAvisoWSPort();
        port.create(entity);
    }

    private Usuario find(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        usuariows.UsuarioWS port = service_1.getUsuarioWSPort();
        return port.find(id);
    }

    private int count() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        avisows.AvisoWS port = service.getAvisoWSPort();
        return port.count();
    }
    
    @PostConstruct
    public void doListar()
    {
        listaAvisos = this.findAll();
    }
    
    public String doEditar(Aviso aviso)
    {
        avisoEditado=aviso;
        desplegarCreacion=true;
        Usuario = aviso.getUsuarioemail().getEmail();
        estado = aviso.getEstado();
        if (aviso.getFinReparacion()!=null)
        {
            finReparacionAnio = Integer.toString(aviso.getFechacreacion().getYear());
            finReparacionMes = Integer.toString(aviso.getFinReparacion().getMonth());
            finReparacionDia = Integer.toString(aviso.getFinReparacion().getDay());
        }
        if (aviso.getInicioReparacion()!=null)
        {
            inicioReparacionAnio = Integer.toString(aviso.getInicioReparacion().getYear());
            inicioReparacionMes = Integer.toString(aviso.getInicioReparacion().getMonth());
            inicioReparacionDia = Integer.toString(aviso.getInicioReparacion().getDay());
        }
        observaciones = aviso.getObservaciones();
        ubicacionTecnica = aviso.getUbicacionTecnica();
        if (aviso.getPrioridad()!=null)
        {
            prioridad = Integer.toString(aviso.getPrioridad());
        }
        posicionGPS = aviso.getPosGPS();
        tipo = aviso.getTipo();
        ubicacion = aviso.getUbicacion();
        
        
        return "aviso";
    }

    private java.util.List<avisows.Aviso> findAll() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        avisows.AvisoWS port = service.getAvisoWSPort();
        return port.findAll();
    }

    private void edit(avisows.Aviso entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        avisows.AvisoWS port = service.getAvisoWSPort();
        port.edit(entity);
    }

    private void ponerParametrosaNull() {
        avisoEditado=null;
        desplegarCreacion=false;
        estado = null;
        
        finReparacionAnio = null;
        finReparacionMes = null;
        finReparacionDia = null;

        inicioReparacionAnio = null;
        inicioReparacionMes = null;
        inicioReparacionDia = null;
        
        observaciones = null;
        ubicacionTecnica = null;
        prioridad = null;
        
        posicionGPS = null;
        tipo = null;
        ubicacion = null;
    }
}
