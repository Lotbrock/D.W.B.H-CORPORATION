package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Fase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Fase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FaseRepository extends JpaRepository<Fase, Long> {

}
