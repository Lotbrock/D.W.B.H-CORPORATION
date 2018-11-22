package co.edu.sena.horarios.model.controller.dao.mysql;

import co.edu.sena.horarios.model.controller.dao.InstructorDAO;
import co.edu.sena.horarios.model.controller.dao.factory.DAOFactory;
import co.edu.sena.horarios.model.entities.DisponibilidadHoraria;
import co.edu.sena.horarios.model.entities.Especialidad;
import co.edu.sena.horarios.model.entities.Instructor;
import co.edu.sena.horarios.model.entities.InstructorPK;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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


    @Test
    public void test1Insert() {
        Instructor instructor = new Instructor(new InstructorPK("CC","1010091473"));
        instructor.setEspecialidadCollection(new ArrayList<>());
        instructor.getEspecialidadCollection().add(new Especialidad("Desarrollo de sistemas", "Activo","ADSI"));
    instructor.setDisponibilidadHorariaCollection(new ArrayList<>());
    instructor.getDisponibilidadHorariaCollection().add(new DisponibilidadHoraria());
instructorDAO.insert(instructor);
assertEquals(instructor, instructorDAO.find(new InstructorPK("CC", "1010091473")));
    }
}