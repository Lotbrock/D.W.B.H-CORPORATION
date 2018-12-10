package co.edu.sena.horarios.model.controller.dao.factory;

import co.edu.sena.horarios.model.controller.dao.InstructorDAO;
import co.edu.sena.horarios.model.controller.dao.VinculacionDAO;
import co.edu.sena.horarios.model.controller.dao.mysql.InstructorDAOImpl;
import co.edu.sena.horarios.model.controller.dao.mysql.VinculacionDAOImpl;
import co.edu.sena.horarios.model.entities.Instructor;
import co.edu.sena.horarios.model.entities.Vinculacion;

public class DAOFactory {
    public static InstructorDAO crearInstructorDAO(){
        return new InstructorDAOImpl(Instructor.class);
    }
    public static VinculacionDAO crearVinculacionDAO(){return new VinculacionDAOImpl(Vinculacion.class);
    }
}
