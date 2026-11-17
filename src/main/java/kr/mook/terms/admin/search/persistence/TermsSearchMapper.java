package kr.mook.terms.admin.search.persistence;

import java.util.HashMap;

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
     * 이용약관 아이디가 이용약관 테이블에 저장되어 있는지 확인하기 위한 건수 조회
     * 
     * @param termsId
     * @return
     */
    public int countByTermsId(final long termsId);

    /**
     * 이용약관 아이디가 이용약관 조회 테이블에 저장되어 있는지 확인하기 위한 건수 조회
     * 
     * @param termsId
     * @return
     */
    public int countTermsSearchByTermsId(final long termsId);

    /**
     * 저장된 이용약관 정보 중 동일한 제목을 가지고 있는 이용약관의 건수 조회
     * 
     * @param termsMap {"title":"{title}", "termsId":"{termsId}"}
     * @return
     */
    public int countByTitle(final HashMap<String, Object> termsMap);

    /**
     * 사용중인 이용약관 정보 중 동일한 출력 순번을 가지고 있는 이용약관의 건수 조회
     * 
     * @param termsMap {"orderNo":"{orderNo}", "termsId":"{termsId}"}
     * @return
     */
    public int countByOrderNo(final HashMap<String, Object> termsMap);

}
