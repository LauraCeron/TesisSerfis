/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import JPA.CONTROLLER.clsAreaJpaController;
import MODEL.clsArea;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lau Cerón
 */
public class BeanArea {

    private EntityManagerFactory emf;
    private clsAreaJpaController areaControl;

    public BeanArea() {
        emf = Persistence.createEntityManagerFactory("PROYECTOSERFISPU");

        areaControl = new clsAreaJpaController(emf);
    }

    public void guargar(clsArea area) throws Exception {
        areaControl.create(area);

    }

    public List<clsArea> listarArea() {
        return areaControl.findclsAreaEntities();
    }
}
