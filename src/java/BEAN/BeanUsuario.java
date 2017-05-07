/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;


import JPA.CONTROLLER.clsUsuarioJpaController;
import MODEL.clsUsuario;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lau Cerón
 */
public class BeanUsuario 
{
     private EntityManagerFactory emf;
    private clsUsuarioJpaController usuarioControl;

    public BeanUsuario() {
        emf = Persistence.createEntityManagerFactory("PROYECTOSERFISPU");
        usuarioControl = new clsUsuarioJpaController(emf);
    }
    public List<clsUsuario> listarUsuario() {
        return usuarioControl.findclsUsuarioEntities();
    }
    
}
