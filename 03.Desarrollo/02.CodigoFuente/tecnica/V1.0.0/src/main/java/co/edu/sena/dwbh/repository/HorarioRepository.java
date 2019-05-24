package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Horario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Horario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

}
