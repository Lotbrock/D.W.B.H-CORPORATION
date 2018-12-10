package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.ResultadosVistos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResultadosVistos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultadosVistosRepository extends JpaRepository<ResultadosVistos, Long> {

}
