package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.ResultadoAprendizaje;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResultadoAprendizaje entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultadoAprendizajeRepository extends JpaRepository<ResultadoAprendizaje, Long> {

}
