package project.recruiting.domain.recruit.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import project.recruiting.domain.recruit.entity.Recruit;

import java.util.List;

import static project.recruiting.domain.company.entity.QCompany.*;
import static project.recruiting.domain.recruit.entity.QRecruit.*;

@RequiredArgsConstructor
public class RecruitQueryDslImpl implements RecruitQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Recruit> searchByContent(String content) {

        return queryFactory
                .selectFrom(recruit)
                .join(recruit.company, company)
                .where(companyNameCont(content)
                        .or(companyCityCont(content))
                        .or(positionCont(content))
                        .or(toolCont(content)))
                .fetch();
    }

    private BooleanExpression companyNameCont(String companyName) {
        return StringUtils.hasText(companyName) ? company.name.contains(companyName) : null;
    }

    private BooleanExpression companyCityCont(String companyCity) {
        return StringUtils.hasText(companyCity) ? company.city.contains(companyCity) : null;    }

    private BooleanExpression positionCont(String position) {
        return StringUtils.hasText(position) ? recruit.position.contains(position) : null;
    }

    private BooleanExpression toolCont(String tool) {
        return StringUtils.hasText(tool) ? recruit.tool.contains(tool) : null;
    }
}
