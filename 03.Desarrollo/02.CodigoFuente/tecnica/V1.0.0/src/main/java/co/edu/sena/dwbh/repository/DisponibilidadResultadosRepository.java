package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.DisponibilidadResultados;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DisponibilidadResultados entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisponibilidadResultadosRepository extends JpaRepository<DisponibilidadResultados, Long> {

}
