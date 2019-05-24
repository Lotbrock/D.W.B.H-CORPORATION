package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Vinculacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Vinculacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VinculacionRepository extends JpaRepository<Vinculacion, Long> {

    @Query(value = "select distinct vinculacion from Vinculacion vinculacion left join fetch vinculacion.instructors",
        countQuery = "select count(distinct vinculacion) from Vinculacion vinculacion")
    Page<Vinculacion> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct vinculacion from Vinculacion vinculacion left join fetch vinculacion.instructors")
    List<Vinculacion> findAllWithEagerRelationships();

    @Query("select vinculacion from Vinculacion vinculacion left join fetch vinculacion.instructors where vinculacion.id =:id")
    Optional<Vinculacion> findOneWithEagerRelationships(@Param("id") Long id);

}
