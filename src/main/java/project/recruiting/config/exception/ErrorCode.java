package project.recruiting.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    ALREADY_APPLIED(400, "이미 지원하신 채용공고가 존재합니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    RECRUIT_NOT_FOUND(404, "존재하지 않는 채용공고 입니다."),
    COMPANY_NOT_FOUND(404, "존재하지 않는 기업입니다.");

    private final int status;
    private final String message;
}
