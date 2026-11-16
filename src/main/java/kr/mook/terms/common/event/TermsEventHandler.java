package kr.mook.terms.common.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import kr.mook.terms.common.entity.Terms;
import lombok.extern.log4j.Log4j2;

/**
 * 이용약관 관리(저장/수정/삭제) 결과를 외부로 전송하기 위한 이벤트 핸들러
 * 
 * @version v.1.0
 * @since 2026.11.16
 * @author Jeong, Inmook
 */
@Log4j2
@Component
public class TermsEventHandler {

    /**
     * 이용약관 관리(저장/수정/삭제) 결과 외부 전송
     * 
     * @param event
     */
    @EventListener
    public void termsEventPublisher(TermsEvent event) {
        Terms terms = event.getTerms();
        String status = event.getStatus();  // I : 등록(저장), U : 수정, D : 삭제
        log.info("##### 이용약관 이벤트 퍼블리셔 실행. TERMS : {}, STATUS : {}", terms, status);
    }

    
}
