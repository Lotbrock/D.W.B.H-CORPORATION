package co.edu.sena.horarios.model.controller.dao.mysql;

import co.edu.sena.horarios.model.controller.dao.VinculacionDAO;
import co.edu.sena.horarios.model.entities.Vinculacion;

public class VinculacionDAOImpl extends AbstractDao<Vinculacion> implements VinculacionDAO {
    public VinculacionDAOImpl(Class<Vinculacion> entityClass) {
        super(entityClass);
    }
}
