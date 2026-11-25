package kr.mook.terms.admin.search.service;

import java.util.Locale;

import kr.mook.terms.admin.search.dto.TermsSearchResponseDto;

/**
 * 관리자용 이용약관 조회를 위한 서비스
 * 
 * @version v.1.0
 * @since 2026.11.12
 * @author Jeong, Inmook
 */
public interface TermsSearchService {

    /**
     * 이용약관 아이디를 통해 이용약관 테이블에 저장되어 있는지 점검
     * 
     * @param termsId 이용약관 아이디
     * @return 데이터가 있을 경우 true, 없을 경우 false 반환
     */
    public boolean exists(final long termsId);

    /**
     * 이용약관 아이디를 통해 이용약관 조회 테이블에 저장되어 있는지 점검
     * 
     * @param termsId 이용약관 아이디
     * @return 데이터가 있을 경우 true, 없을 경우 false 반환
     */
    public boolean existsUserTermsSearch(final long termsId);

    /**
     * 이용약관 제목이 중복되었는지 점검<br/>
     * 이용약관 아이디는 이용약관 정보 수정 시 제목 중복 여부를 점검할 때 사용되며,<br/>
     * 수정중인 이용약관 정보를 제외한 나머지 데이터와 제목이 중복되는지 확인하기 위해 필요
     * 
     * @param title 이용약관 제목
     * @param termsId 이용약관 아이디
     * @return 중복되는 데이터가 있을 경우 true, 없을 경우 false 반환
     */
    public boolean duplicateTitle(final String title, final long termsId);

    /**
     * 이용약관 출력순번이 현재 사용중인 이용약관 중 중복되는 데이터가 있는지 점검<br/>
     * 이용약관 아이디는 이용약관 정보 수정 시 출력 순번 중복 여부를 점검할 때 사용되며,<br/>
     * 수정중인 이용약관 정보를 제외한 나머지 데이터와 출력 순번이 중복되는지 확인하기 위해 필요
     * 
     * @param orderNo 이용약관 출력 순번
     * @param termsId 이용약관 아이디
     * @return 중복되는 데이터가 있을 경우 true, 없을 경우 false 반환
     */
    public boolean duplicateOrderNo(final int orderNo, final long termsId);

    
    public TermsSearchResponseDto detail(final long termsId, final Locale locale);
}
