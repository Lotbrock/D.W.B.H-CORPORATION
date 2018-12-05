package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.DisponibilidadHoraria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DisponibilidadHoraria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisponibilidadHorariaRepository extends JpaRepository<DisponibilidadHoraria, Long> {

}
