package kr.mook.terms.admin.search.persistence;

import org.apache.ibatis.annotations.Mapper;

/**
 * 관리자용 이용약관 정보 조회를 위한 영속성 구현
 * 
 * @version v.1.0
 * @since 2026.11.12
 * @author Jeong, Inmook
 */
@Mapper
public interface TermsSearchMapper {

    /**
     * 저장된 이용약관 정보 중 동일한 제목을 가지고 있는 이용약관의 건수 조회
     * @param title
     * @return
     */
    public int countByTitle(final String title);

    /**
     * 사용중인 이용약관 정보 중 동일한 출력 순번을 가지고 있는 이용약관의 건수 조회
     * @param orderNo
     * @return
     */
    public int countByOrderNo(final int orderNo);

}
