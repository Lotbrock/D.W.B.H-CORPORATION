package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Actividad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Actividad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    @Query(value = "select distinct actividad from Actividad actividad left join fetch actividad.planeacions",
        countQuery = "select count(distinct actividad) from Actividad actividad")
    Page<Actividad> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct actividad from Actividad actividad left join fetch actividad.planeacions")
    List<Actividad> findAllWithEagerRelationships();

    @Query("select actividad from Actividad actividad left join fetch actividad.planeacions where actividad.id =:id")
    Optional<Actividad> findOneWithEagerRelationships(@Param("id") Long id);

}
