package kr.mook.terms.admin.management.service;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.mook.terms.admin.management.dto.TermsManagementRequestDto;
import kr.mook.terms.admin.management.dto.TermsManagementResponseDto;
import kr.mook.terms.admin.management.persistence.TermsManagementMapper;
import kr.mook.terms.admin.management.util.TermsRequestValueChecker;
import kr.mook.terms.admin.management.util.TermsResponseMaker;
import kr.mook.terms.admin.search.service.TermsSearchService;
import kr.mook.terms.common.entity.Terms;
import kr.mook.terms.common.event.TermsEvent;
import kr.mook.terms.common.util.TermsConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 이용약관 관리(저장/수정/삭제/상태변경 등)를 위한 서비스 구현
 * 
 * @version v.1.0
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TermsManagementServiceImpl implements TermsManagementService {

    private final MessageSource messageSource;
    private final TermsManagementMapper termsManagementMapper;

    private final TermsSearchService termsSearchService;

    private final ApplicationEventPublisher eventPublisher;

    private final String _TERMS_ERROR_MESSAGE_PREFIX = "ERROR-TERMS-";
    private final String _SAVE_PUBLISH_STATUS = "I";
    private final String _UPDATE_PUBLISH_STATUS = "U";
    private final String _DELETE_PUBLISH_STATUS = "D";

    /**
     * 이용약관 정보 저장 서비스 구현
     */
    @Transactional
    @Override
    public TermsManagementResponseDto saveTerms(final TermsManagementRequestDto termsManagementRequestDto, final Locale locale) {
        log.info("##### 관리자가 작성한 이용약관 정보 : {}", termsManagementRequestDto);

        // 저장될 이용약관 정보의 값이 적절한지 검증(빈 값, 잘못된 형식의 값, 이미 저장된 값 등)
        String errorStatusCode = this._getErrorStatusCode(termsManagementRequestDto, 0);
        if(!"".equals(errorStatusCode))
            return this._getErrorResponse(errorStatusCode, locale);
        
        // 데이터베이스에 저장하기 위해 Terms Entity로 변환
        Terms terms = TermsConverter.toTerms(termsManagementRequestDto);
        terms.setCreateId(1L);
        terms.setCreateDate(LocalDateTime.now());

        // 이용약관 저장
        this.termsManagementMapper.save(terms);
        log.info("##### 이용약관 정보 저장 성공. TERMS_ID : {}", terms.getTermsId());

        // 사용자 조회용 이용약관 테이블에도 데이터 저장
        this._saveUserTermsSearchHandler(terms);

        // 이용약관 저장 성공 결과 외부 전송
        this._termsEventPublisher(terms, this._SAVE_PUBLISH_STATUS);
        
        return this._getSuccessResponse(terms.getTermsId(), terms.getTitle(), "TERMS-SAVE", locale);
    }

    /**
     * 이용약관 정보 수정 서비스 구현
     */
    @Transactional
    @Override
    public TermsManagementResponseDto updateTerms(final long termsId, final TermsManagementRequestDto termsManagementRequestDto, final Locale locale) {
        log.info("##### 관리자가 수정한 termsId : {}, 이용약관 정보 : {}", termsId, termsManagementRequestDto);

        // 저장된 이용약관 정보가 없는 경우 오류 메시지 출력
        if(!this.termsSearchService.exists(termsId))
            return this._getErrorResponse("NOT-EXISTS", locale);

        // 저장될 이용약관 정보의 값이 적절한지 검증(빈 값, 잘못된 형식의 값, 이미 저장된 값 등)
        String errorStatusCode = this._getErrorStatusCode(termsManagementRequestDto, termsId);
        if(!"".equals(errorStatusCode))
            return this._getErrorResponse(errorStatusCode, locale);

        // 데이터베이스에 저장하기 위해 Terms Entity로 변환
        Terms terms = TermsConverter.toTerms(termsManagementRequestDto);
        terms.setTermsId(termsId);
        terms.setUpdateId(1L);
        terms.setUpdateDate(LocalDateTime.now());

        // 이용약관 수정
        this.termsManagementMapper.update(terms);
        
        // 사용자 조회용 이용약관 테이블에도 데이터 저장/수정/삭제
        this._updateUserTermsSearchHandler(terms);

        // 이용약관 수정 성공 결과 외부 전송
        this._termsEventPublisher(terms, this._UPDATE_PUBLISH_STATUS);

        return this._getSuccessResponse(terms.getTermsId(), terms.getTitle(), "TERMS-UPDATE", locale);
    }

    /**
     * 이용약관 정보 삭제 서비스 구현
     */
    @Transactional 
    @Override
    public TermsManagementResponseDto deleteTerms(long termsId, Locale locale) {
        log.info("##### 관리자가 삭제한 termsId : {}", termsId);
        if(!this.termsSearchService.exists(termsId))
            return this._getErrorResponse("NOT-EXISTS", locale);

        // 이용약관 삭제
        this.termsManagementMapper.delete(termsId);
        
        // 사용자 조회용 이용약관 테이블에도 데이터 삭제
        if(this.termsSearchService.existsUserTermsSearch(termsId))
            this.termsManagementMapper.deleteUserTermsSearch(termsId);

        // 이용약관 삭제 성공 결과 외부 전송
        Terms terms = Terms.builder().termsId(termsId).build();
        this._termsEventPublisher(terms, this._DELETE_PUBLISH_STATUS);

        return this._getSuccessResponse(termsId, "", "TERMS-DELETE", locale);
    }

    /**
     * 사용자 조회용 이용약관 테이블에도 데이터 저장
     * 
     * @param terms
     */
    private void _saveUserTermsSearchHandler(final Terms terms) {
        // useYn이 Y인 경우에는 TERMS_SEARCH 테이블에도 저장
        if("Y".equals(terms.getUseYn()))
            this.termsManagementMapper.saveUserTermsSearch(terms);
    }

    /**
     * 사용자 조회용 이용약관 테이블에도 데이터 수정
     * 
     * @param terms
     */
    private void _updateUserTermsSearchHandler(final Terms terms) {
        boolean hasTerms = this.termsSearchService.existsUserTermsSearch(terms.getTermsId());
        boolean useTerms = "Y".equalsIgnoreCase(terms.getUseYn());

        // TERMS_SEARCH 테이블에 이미 저장되어 있고, 사용여부가 Y인 경우 TERMS_SEARCH에 데이터 수정
        if(hasTerms && useTerms)
            this.termsManagementMapper.updateUserTermsSearch(terms);
        
        // TERMS_SEARCH 테이블에 이미 저장되어 있고, 사용여부가 N인 경우 TERMS_SEARCH에 데이터 삭제
        if(hasTerms && !useTerms)
            this.termsManagementMapper.deleteUserTermsSearch(terms.getTermsId());
        
        // TERMS_SEARCH 테이블에 저장되어 있지 않고, 사용여부가 Y인 경우 TERMS_SEARCH에 데이터 저장
        if(!hasTerms && useTerms)
            this.termsManagementMapper.saveUserTermsSearch(terms);
    }

    /**
     * 관리자가 작성한 이용약관 정보에 빈 값(EMPTY), 잘못된 형식의 값(NOT-VALID), 이미 저장된 값(DUPLICATION)이 있는지 확인하여 오류 상태 코드 반환<br/>
     * 
     * @param termsManagementRequestDto 관리자가 작성한 이용약관 정보
     * @return 오류 상태코드, 빈 값이 반환될 경우 오류가 없음을 의미
     */
    private String _getErrorStatusCode(final TermsManagementRequestDto termsManagementRequestDto, long termsId) {

        String errorStatusCode = TermsRequestValueChecker.getErrorStatusCode(termsManagementRequestDto, termsId);
        if(!errorStatusCode.isBlank()) return errorStatusCode;

        if(this.termsSearchService.duplicateTitle(termsManagementRequestDto.getTitle(), termsId))
            return "DUPLICATE-TITLE";

        String useYn = termsManagementRequestDto.getUseYn();
        if("Y".equalsIgnoreCase(useYn) && this.termsSearchService.duplicateOrderNo(termsManagementRequestDto.getOrderNo(), termsId))
            return "DUPLICATE-ORDER-NO";

        return "";
    }

    /**
     * 오류 메시지를 포함한 결과 반환
     * 
     * @param statusCode 오류 상태 코드
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 오류 메시지를 포함한 결과 반환
     */
    private TermsManagementResponseDto _getErrorResponse(final String statusCode, final Locale locale) {
        String messageCode = this._TERMS_ERROR_MESSAGE_PREFIX + statusCode;
        String message = this.messageSource.getMessage(messageCode, null, locale);
        return TermsResponseMaker.getErrorResponse(message, locale);
    }

    /**
     * 저장/수정/삭제 성공에 대한 결과 반환
     * 
     * @param termsId 저장/수정 성공한 이용약관 아이디. 삭제의 경우 0이 전달됨
     * @param title 이용약관 제목
     * @param statusCode 저장/수정/삭제 상태 코드
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 저장/수정/삭제 성공에 대한 결과 반환
     */
    private TermsManagementResponseDto _getSuccessResponse(final long termsId, final String title, final String statusCode, final Locale locale) {
        String message = this.messageSource.getMessage(statusCode, new String[] {title}, locale);
        return TermsResponseMaker.getSuccessResponse(termsId, message, locale);
    }

    /**
     * 이용약관 관리(저장/수정/삭제) 결과 전송
     * 
     * @param terms
     * @param status
     */
    private void _termsEventPublisher(final Terms terms, final String status) {
        TermsEvent event = new TermsEvent(terms, status);
        eventPublisher.publishEvent(event);
    }
}
