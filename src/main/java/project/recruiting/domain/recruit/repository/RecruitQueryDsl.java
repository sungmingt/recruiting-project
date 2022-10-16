package project.recruiting.domain.recruit.repository;

import project.recruiting.domain.recruit.entity.Recruit;

import java.util.List;

public interface RecruitQueryDsl {

    List<Recruit> searchByContent(String content);
}
