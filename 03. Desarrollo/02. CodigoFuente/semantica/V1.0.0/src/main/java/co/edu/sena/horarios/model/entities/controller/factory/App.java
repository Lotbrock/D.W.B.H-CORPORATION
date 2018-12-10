package co.edu.sena.horarios.model.entities.controller.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class App {

    public static void main(String[] args) {

        try{
            EntityManagerFactory mf = Persistence.createEntityManagerFactory("UPSemantica");
            EntityManager em = mf.createEntityManager();

        }
        catch (PersistenceException e){
            System.out.println(e.getMessage());
        }
    }
}
