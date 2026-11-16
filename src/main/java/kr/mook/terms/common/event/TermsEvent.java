package kr.mook.terms.common.event;

import kr.mook.terms.common.entity.Terms;

/**
 * 이용약관 관리 결과를 전달하기 위한 이벤트 객체
 * 
 * @version v.1.0
 * @since 2026.11.16
 * @author Jeong, Inmook
 */
public class TermsEvent {
    // 이용약관 관리 결과
    private final Terms terms;
    // 이용약관 관리 상태(I : 등록, U : 수정, D : 삭제)
    private final String status;

    public TermsEvent(Terms terms, String status) {
        this.terms = terms;
        this.status = status;
    }

    public Terms getTerms() {
        return terms;
    }

    public String getStatus() {
        return status;
    }
}
