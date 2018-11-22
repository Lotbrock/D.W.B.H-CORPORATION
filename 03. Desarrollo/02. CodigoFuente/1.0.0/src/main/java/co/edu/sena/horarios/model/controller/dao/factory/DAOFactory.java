package co.edu.sena.horarios.model.controller.dao.factory;

import co.edu.sena.horarios.model.controller.dao.InstructorDAO;
import co.edu.sena.horarios.model.controller.dao.mysql.InstructorDAOImpl;
import co.edu.sena.horarios.model.entities.Instructor;

public class DAOFactory {
    public static InstructorDAO crearInstructorDAO(){
        return new InstructorDAOImpl(Instructor.class);
    }
}
