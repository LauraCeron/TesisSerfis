/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import JPA.CONTROLLER.clsAsignacionJpaController;
import JPA.CONTROLLER.clsMateriaJpaController;
import JPA.CONTROLLER.clsUsuarioJpaController;
import MODEL.clsAsignacion;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lau Cerón
 */
public class BeanAsignacion {

    private EntityManagerFactory emf;
    private clsAsignacionJpaController asignacionControl;
    private clsMateriaJpaController materiaControl;
    private clsUsuarioJpaController usuarioControl;

    public BeanAsignacion() {
        emf = Persistence.createEntityManagerFactory("PROYECTOSERFISPU");

        asignacionControl = new clsAsignacionJpaController(emf);
    }

    public void guargar(clsAsignacion asignacion) throws Exception {
        asignacionControl.create(asignacion);

    }

   

    
}
