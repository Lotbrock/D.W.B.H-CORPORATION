package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Planeacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Planeacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlaneacionRepository extends JpaRepository<Planeacion, Long> {

    @Query(value = "select distinct planeacion from Planeacion planeacion left join fetch planeacion.trimestres",
        countQuery = "select count(distinct planeacion) from Planeacion planeacion")
    Page<Planeacion> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct planeacion from Planeacion planeacion left join fetch planeacion.trimestres")
    List<Planeacion> findAllWithEagerRelationships();

    @Query("select planeacion from Planeacion planeacion left join fetch planeacion.trimestres where planeacion.id =:id")
    Optional<Planeacion> findOneWithEagerRelationships(@Param("id") Long id);

}
