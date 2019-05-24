package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.EstadoFormacion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadoFormacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstadoFormacionRepository extends JpaRepository<EstadoFormacion, Long> {

}
