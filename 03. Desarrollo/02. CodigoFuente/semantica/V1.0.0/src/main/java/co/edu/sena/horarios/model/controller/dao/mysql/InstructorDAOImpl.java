package co.edu.sena.horarios.model.controller.dao.mysql;

import co.edu.sena.horarios.model.controller.dao.InstructorDAO;
import co.edu.sena.horarios.model.entities.Instructor;

public class InstructorDAOImpl extends AbstractDao<Instructor> implements InstructorDAO {
    public InstructorDAOImpl(Class<Instructor> entityClass) {
        super(entityClass);
    }
}
