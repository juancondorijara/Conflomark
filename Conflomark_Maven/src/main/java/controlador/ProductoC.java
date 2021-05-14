package controlador;

import dao.ProductoImpl;
import modelo.Producto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.enterprise.context.SessionScoped;

//Notación CDI
@Named(value = "productoC")
//@Dependent
@SessionScoped
public class ProductoC implements Serializable{

    private Producto producto;
    private ProductoImpl dao;
    private List<Producto> listadoProducto;
    
    public ProductoC() {
        producto = new Producto();
        dao = new ProductoImpl();
    }
    
    public void registrar() throws Exception{
        try {
            dao.registrar(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Error al Registrar"));
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }
    
    public void modificar() throws Exception {
        try {
            dao.modificar(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void eliminar(Producto producto) throws Exception{
        try {            
            dao.eliminar(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarC " + e.getMessage());
        }
    }
    
    public void limpiar(){
        producto = new Producto();
    }
    
    public void listar(){
        try {
            listadoProducto = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }
    
    
    
// Getter y Setter

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoImpl getDao() {
        return dao;
    }

    public void setDao(ProductoImpl dao) {
        this.dao = dao;
    }

    public List<Producto> getListadoProducto() {
        return listadoProducto;
    }

    public void setListadoProducto(List<Producto> listadoProducto) {
        this.listadoProducto = listadoProducto;
    }

}
