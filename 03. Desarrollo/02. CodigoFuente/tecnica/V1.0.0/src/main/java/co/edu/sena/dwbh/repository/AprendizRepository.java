package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Aprendiz;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Aprendiz entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AprendizRepository extends JpaRepository<Aprendiz, Long> {

}
