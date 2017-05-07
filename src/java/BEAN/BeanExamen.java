/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN;

import JPA.CONTROLLER.clsExamenJpaController;
import MODEL.clsExamen;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lau Cerón
 */
public class BeanExamen {
  private EntityManagerFactory emf;
  private clsExamenJpaController examenControl;
    public  BeanExamen()
    {
      emf= Persistence.createEntityManagerFactory("PROYECTOSERFISPU");
      
      examenControl = new clsExamenJpaController(emf);
    }
    public void guargar(clsExamen examen) throws Exception
    {
        examenControl.create(examen);
        
    }
}

