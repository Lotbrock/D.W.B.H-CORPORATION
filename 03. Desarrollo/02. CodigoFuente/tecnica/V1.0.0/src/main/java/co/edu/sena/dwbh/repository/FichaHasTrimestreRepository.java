package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.FichaHasTrimestre;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FichaHasTrimestre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FichaHasTrimestreRepository extends JpaRepository<FichaHasTrimestre, Long> {

}
