package project.recruiting.domain.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.recruiting.config.exception.BusinessException;
import project.recruiting.domain.company.entity.Company;
import project.recruiting.domain.company.repository.CompanyRepository;

import static project.recruiting.config.exception.ErrorCode.*;

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
                .orElseThrow(() -> new BusinessException(COMPANY_NOT_FOUND));
    }
}
