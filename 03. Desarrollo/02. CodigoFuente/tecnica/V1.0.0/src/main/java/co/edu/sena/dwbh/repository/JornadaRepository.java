package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Jornada;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Jornada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Long> {

}
