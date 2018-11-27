package co.edu.sena.horarios.model.controller.dao.mysql;

import co.edu.sena.horarios.model.controller.dao.VinculacionDAO;
import co.edu.sena.horarios.model.controller.dao.factory.DAOFactory;
import co.edu.sena.horarios.model.entities.Vinculacion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VinculacionDAOImplTest {
    private VinculacionDAO vinculacionDAO;

    @Before
    public void setUp() throws Exception {
        vinculacionDAO = DAOFactory.crearVinculacionDAO();
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void test1Insert() {
        Vinculacion vinculacion = new Vinculacion("contrato indefinido", 180, "inactivo");
        vinculacionDAO.insert(vinculacion);
        assertEquals(vinculacion, (Vinculacion) vinculacionDAO.find("contrato indefinido"));
    }

    @Test
    public void test2Update() {
        Vinculacion vinculacion = (Vinculacion) vinculacionDAO.find("contrato indefinido");
        vinculacion.setEstado("activo");
        vinculacion.setHoras(150);
        vinculacionDAO.update(vinculacion);
        assertEquals(vinculacion, (Vinculacion) vinculacionDAO.find("contrato indefinido"));

    }

    @Test
    public void test3FindAll() {
        List<Vinculacion> list = (List<Vinculacion>) vinculacionDAO.findAll();
        assertFalse(list.isEmpty());
    }

    @Test
    public void test4Find() {
        Vinculacion vinculacion = (Vinculacion) vinculacionDAO.find("contrato indefinido");
        assertNotNull(vinculacion);
    }

    @Test
    public void test5Remove() {
       vinculacionDAO.remove((Vinculacion) new Vinculacion("contrato indefinido",150,"activo"));
       assertNull((Vinculacion) vinculacionDAO.find("contrato indefinido"));
    }
}