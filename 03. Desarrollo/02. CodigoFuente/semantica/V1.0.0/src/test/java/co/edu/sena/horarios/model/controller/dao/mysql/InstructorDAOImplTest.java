package co.edu.sena.horarios.model.controller.dao.mysql;

import co.edu.sena.horarios.model.controller.dao.InstructorDAO;
import co.edu.sena.horarios.model.controller.dao.factory.DAOFactory;
import co.edu.sena.horarios.model.entities.*;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**TODO
 * Insertar datos para completar la tabla de Instructor
 * isertar datos en tipo vinculacion
 * insertar datos en especialidad
 * insertar datos en disponibilidad horararia
 */

public class InstructorDAOImplTest {
private InstructorDAO instructorDAO;
    @Before
    public void setUp() throws Exception {
        instructorDAO = DAOFactory.crearInstructorDAO();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Insertar datos en tipo_documento
     * Insertar datos en usuario
     * insertar datos en vinculacion
     */
    @Test
    public void test1Insert() {
        Instructor instructor = new Instructor(new InstructorPK("CC","1010091473"));
        instructor.setTipoVinculacion(new Vinculacion("contratista"));
instructorDAO.insert(instructor);
assertEquals(instructor, instructorDAO.find(new InstructorPK("CC", "1010091473")));
    }

    @Test
    public void test2Update() {
Instructor instructor = (Instructor) instructorDAO.find(new InstructorPK("CC","1010091473"));
instructor.setTipoVinculacion(new Vinculacion("planta"));
instructorDAO.update(instructor);
assertEquals(instructor,(Instructor) instructorDAO.find(new InstructorPK("CC","1010091473")));
    }

    @Test
    public void test3FindAll() {
        List<Instructor> instructors = (List<Instructor>) instructorDAO.findAll();
        assertFalse(instructors.isEmpty());
    }

    @Test
    public void test4Find() {
        Instructor instructorEncontrado =(Instructor) instructorDAO.find(new InstructorPK("CC", "1010091473"));
        assertNotNull(instructorEncontrado);
    }

    @Test
    public void test5Remove() {
        instructorDAO.remove((Instructor) instructorDAO.find(new InstructorPK("CC", "1010091473")));
        assertNull((Instructor)instructorDAO.find(new InstructorPK("CC", "1010091473")));
    }
}