package kr.mook.terms.admin.management.service;

import java.util.Locale;

import kr.mook.terms.admin.management.dto.TermsManagementRequestDto;
import kr.mook.terms.admin.management.dto.TermsManagementResponseDto;

/**
 * 이용약관 관리(저장/수정/삭제/상태변경 등)를 위한 서비스
 * 
 * @version v.1.0
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
public interface TermsManagementService {

    /**
     * 이용약관 정보 저장을 위한 서비스
     * 
     * @param termsManagementRequestDto 관리자가 작성한 이용약관 정보
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 이용약관 정보 저장 결과 상태 코드 및 메시지, 이용약관 아이디 반환<br/>
     * {<br/>
     *      &emsp;"statusCode" : "200",<br/>
     *      &emsp;"message" : "이용약관을 저장하였습니다.",<br/>
     *      &emsp;"termsId" : 1<br/>
     * }<br/>
     */
    public TermsManagementResponseDto saveTerms(final TermsManagementRequestDto termsManagementRequestDto, final Locale locale);

    /**
     * 이용약관 정보 수정을 위한 서비스
     * 
     * @param termsId 수정할 이용약관 정보 아이디
     * @param termsManagementRequestDto 관리자가 작성한 이용약관 정보
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 이용약관 정보 수정 결과 상태 코드 및 메시지, 이용약관 아이디 반환<br/>
     * {<br/>
     *      &emsp;"statusCode" : "200",<br/>
     *      &emsp;"message" : "이용약관을 수정하였습니다.",<br/>
     *      &emsp;"termsId" : 1<br/>
     * }<br/>
     */
    public TermsManagementResponseDto updateTerms(final long termsId, final TermsManagementRequestDto termsManagementRequestDto, final Locale locale);
}
