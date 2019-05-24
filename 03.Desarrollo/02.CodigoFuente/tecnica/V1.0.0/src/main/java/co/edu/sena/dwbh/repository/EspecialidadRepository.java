package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Especialidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    @Query(value = "select distinct especialidad from Especialidad especialidad left join fetch especialidad.instructors",
        countQuery = "select count(distinct especialidad) from Especialidad especialidad")
    Page<Especialidad> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct especialidad from Especialidad especialidad left join fetch especialidad.instructors")
    List<Especialidad> findAllWithEagerRelationships();

    @Query("select especialidad from Especialidad especialidad left join fetch especialidad.instructors where especialidad.id =:id")
    Optional<Especialidad> findOneWithEagerRelationships(@Param("id") Long id);

}
