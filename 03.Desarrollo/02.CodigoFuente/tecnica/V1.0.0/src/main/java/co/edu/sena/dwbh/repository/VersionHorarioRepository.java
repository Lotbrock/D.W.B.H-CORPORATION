package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.VersionHorario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VersionHorario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VersionHorarioRepository extends JpaRepository<VersionHorario, Long> {

}
