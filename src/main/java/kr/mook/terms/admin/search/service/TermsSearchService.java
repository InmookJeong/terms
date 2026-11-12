package kr.mook.terms.admin.search.service;

/**
 * 관리자용 이용약관 조회를 위한 서비스
 * 
 * @version v.1.0
 * @since 2026.11.12
 * @author Jeong, Inmook
 */
public interface TermsSearchService {

    /**
     * 이용약관 제목이 중복되었는지 점검
     * 
     * @param title 이용약관 제목
     * @return 중복되는 데이터가 있을 경우 true, 없을 경우 false 반환
     */
    public boolean duplicateTitle(final String title);

    /**
     * 이용약관 출력순번이 현재 사용중인 이용약관 중 중복되는 데이터가 있는지 점검
     * 
     * @param orderNo 이용약관 출력 순번
     * @return 중복되는 데이터가 있을 경우 true, 없을 경우 false 반환
     */
    public boolean duplicateOrderNo(final int orderNo);

}
