package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.NivelFormacion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NivelFormacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NivelFormacionRepository extends JpaRepository<NivelFormacion, Long> {

}
