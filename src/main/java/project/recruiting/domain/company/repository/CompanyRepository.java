package project.recruiting.domain.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.recruiting.domain.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
