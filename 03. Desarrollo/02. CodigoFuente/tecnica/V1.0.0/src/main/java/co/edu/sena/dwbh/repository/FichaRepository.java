package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Ficha;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ficha entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

}
