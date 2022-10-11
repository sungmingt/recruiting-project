package project.recruiting.domain.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.recruiting.domain.company.entity.Company;
import project.recruiting.domain.company.repository.CompanyRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    /**
     * recruitService 호출용 -> Entity 반환
     */
    public Company findCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("해당 기업이 존재하지 않습니다."));
    }
}
