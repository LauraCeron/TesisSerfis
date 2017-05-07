
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import JPA.CONTROLLER.clsAreaJpaController;
import JPA.CONTROLLER.clsMateriaJpaController;
import MODEL.clsArea;
import MODEL.clsMateria;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lau Cerón
 */
public class BeanMateria {

    private EntityManagerFactory emf;
    private clsMateriaJpaController materiaControl;
    private clsAreaJpaController areaControl;

    public BeanMateria() {
        emf = Persistence.createEntityManagerFactory("PROYECTOSERFISPU");

        materiaControl = new clsMateriaJpaController(emf);
    }

    public void guargar(clsMateria materia) throws Exception {
        materiaControl.create(materia);

    }

    public List<clsMateria> listarMateria() {
        return materiaControl.findclsMateriaEntities();
    }
}
