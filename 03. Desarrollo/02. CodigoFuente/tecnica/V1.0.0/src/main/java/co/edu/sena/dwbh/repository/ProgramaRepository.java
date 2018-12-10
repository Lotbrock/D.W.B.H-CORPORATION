package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Programa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Programa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {

}
