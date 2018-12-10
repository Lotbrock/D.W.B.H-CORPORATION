package co.edu.sena.dwbhcorporation.repository;

import co.edu.sena.dwbhcorporation.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
