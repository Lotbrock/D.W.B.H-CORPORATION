package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Modalidad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Modalidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModalidadRepository extends JpaRepository<Modalidad, Long> {

}
