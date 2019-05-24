package co.edu.sena.dwbh.repository;

import co.edu.sena.dwbh.domain.Instructor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Instructor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
