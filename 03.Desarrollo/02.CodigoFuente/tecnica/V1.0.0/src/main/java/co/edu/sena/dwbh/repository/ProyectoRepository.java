package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Proyecto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Proyecto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

}
