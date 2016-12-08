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
import operacionws.Operacion;
import operacionws.OperacionWS;
import usuariows.Usuario;
import usuariows.UsuarioWS_Service;

/**
 *
 * @author nacho
 */
@Named(value = "operacionBean")
@SessionScoped
public class OperacionBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Emasa-Soap-war/AvisoWS.wsdl")
    private AvisoWS_Service service_2;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Emasa-Soap-war/UsuarioWS.wsdl")
    private UsuarioWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Emasa-Soap-war/OperacionWS.wsdl")
    private operacionws.OperacionWS_Service service;

    /**
     * Creates a new instance of OperacionBean
     */
    private int id;
    private String diafecha;
    private String mesfecha;
    private String añofecha;
    private String descripcion;
    private String usuario;
    private List<Operacion> listaOperaciones;
    private List<Operacion> listaOperacionesRecibidas;

    public List<Operacion> getListaOperacionesRecibidas() {
        return listaOperacionesRecibidas;
    }

    public void setListaOperacionesRecibidas(List<Operacion> listaOperacionesRecibidas) {
        this.listaOperacionesRecibidas = listaOperacionesRecibidas;
    }

    public List<Operacion> getListaOperaciones() {
        return listaOperaciones;
    }

    public void setListaOperaciones(List<Operacion> listaOperaciones) {
        this.listaOperaciones = listaOperaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDiafecha() {
        return diafecha;
    }

    public void setDiafecha(String diafecha) {
        this.diafecha = diafecha;
    }

    public String getMesfecha() {
        return mesfecha;
    }

    public void setMesfecha(String mesfecha) {
        this.mesfecha = mesfecha;
    }

    public String getAñofecha() {
        return añofecha;
    }

    public void setAñofecha(String añofecha) {
        this.añofecha = añofecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    @PostConstruct
    public void init(){
        String aux = "serg@gmail.com";
        listaOperaciones = this.findAll();
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    public OperacionBean() {
    }
    
    public String cargar(){
        return "testOperaciones";
    }
    
    public int getCount(){
        return this.count();
    }
    
    public String doCreate(){
        Operacion op = new Operacion();
        
        try {
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                formatter.applyPattern("dd-MM-yyyy");
                Date fecha = formatter.parse(diafecha);
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(fecha);
                op.setFecha(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            } 
        catch (DatatypeConfigurationException | ParseException e) {
            }
        op.setDescripcion(descripcion);
        operacionws.Usuario usu = new operacionws.Usuario();
        usu.setEmail(usuario);
        usu.setOperador(true);
        op.setUsuarioemail(usu);
        Aviso aviso = this.findAvisoPorUsuario(usuario).get(0);
        operacionws.Aviso av = new operacionws.Aviso();
        av.setEstado(aviso.getEstado());
        av.setFechacreacion(aviso.getFechacreacion());
        av.setFinReparacion(aviso.getFinReparacion());
        av.setId(aviso.getId());
        av.setInicioReparacion(aviso.getInicioReparacion());
        av.setObservaciones(aviso.getObservaciones());
        av.setPosGPS(aviso.getPosGPS());
        av.setPrioridad(aviso.getPrioridad());
        av.setTipo(aviso.getTipo());
        av.setUbicacion(aviso.getUbicacion());
        av.setUbicacionTecnica(aviso.getUbicacionTecnica());
        av.setUsuarioemail(usu);
        op.setAvisoId(av);
        create(op);
        listaOperaciones = this.findAll();
        return "testOperaciones";
    }

    public String doEdit(){
        Operacion op = this.find(id);
        if(diafecha.length() > 0){
            try {
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                formatter.applyPattern("dd-MM-yyyy");
                Date fecha = formatter.parse(diafecha);
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(fecha);
                op.setFecha(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
            } 
        catch (DatatypeConfigurationException | ParseException e) {
            }
        }
        if(descripcion.length() > 0){
            op.setDescripcion(descripcion);
        }
        if(usuario.length() > 0){
            operacionws.Usuario usu = new operacionws.Usuario();
            usu.setEmail(usuario);
            usu.setOperador(true);
            op.setUsuarioemail(usu);
        }
        this.edit(op);
        listaOperaciones = this.findAll();
        return "testOperaciones";
    }
    
    public String doRemove(){
        Operacion op = this.find(id);
        this.remove(op);
        listaOperaciones = this.findAll();
        return "testOperaciones";
    }
    
    public String doGetList(){
        Aviso aviso = this.findAvisoPorUsuario(usuario).get(0);
        listaOperacionesRecibidas = this.findListaOperaciones(aviso.getId());
        
        return "testOperaciones";
    }
    
    private int count() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        return port.count();
    }

    private void create(operacionws.Operacion entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        port.create(entity);
    }

    private void edit(operacionws.Operacion entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        port.edit(entity);
    }

    private operacionws.Operacion find(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        return port.find(id);
    }

    private java.util.List<operacionws.Operacion> findAll() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        return port.findAll();
    }

    private java.util.List<operacionws.Operacion> findListaOperaciones(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        return port.findListaOperaciones(id);
    }

    private java.util.List<operacionws.Operacion> findRange(java.util.List<java.lang.Integer> range) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        return port.findRange(range);
    }

    private void remove(operacionws.Operacion entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        port.remove(entity);
    }

    private Usuario find_1(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        usuariows.UsuarioWS port = service_1.getUsuarioWSPort();
        return port.find(id);
    }

    private java.util.List<avisows.Aviso> findAvisoPorUsuario(java.lang.String s) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        avisows.AvisoWS port = service_2.getAvisoWSPort();
        return port.findAvisoPorUsuario(s);
    }

    private java.util.List<operacionws.Operacion> findAll_1() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        operacionws.OperacionWS port = service.getOperacionWSPort();
        return port.findAll();
    }
    
}
