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

    /**
     * 이용약관 정보 삭제를 위한 서비스
     * 
     * @param termsId 삭제할 이용약관 정보 아이디
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 이용약관 정보 삭제 결과 상태 코드 및 메시지<br/>
     * {<br/>
     *      &emsp;"statusCode" : "200",<br/>
     *      &emsp;"message" : "이용약관을 삭제하였습니다.",<br/>
     *      &emsp;"termsId" : 0<br/>
     * }<br/>
     */
    public TermsManagementResponseDto deleteTerms(final long termsId, final Locale locale);

    /**
     * 이용약관 상태 변경을 위한 서비스
     * 
     * @param termsId 상태 변경을 할 이용약관 정보 아이디
     * @param field 변경할 상태 항목 : USE_YN / REQUIRE_YN
     * @param value 변경할 상태값 : Y / N
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 이용약관 상태 변경 결과 상태 코드 및 메시지<br/>
     * {<br/>
     *      &emsp;"statusCode" : "200",<br/>
     *      &emsp;"message" : "이용약관의 {사용여부/필수여부} 상태를 변경하였습니다.",<br/>
     *      &emsp;"termsId" : 1<br/>
     * }<br/>
     */
    public TermsManagementResponseDto changeStatus(final long termsId, final String field, final String value, final Locale locale);
}
