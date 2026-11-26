package kr.mook.terms.admin.search.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.mook.terms.common.entity.Terms;

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

    /**
     * 이용약관 아이디를 이용하여 이용약관 상세 정보 조회
     * 
     * @param termsId 조회 대상 이용약관 아이디
     * @return
     */
    public Terms get(final long termsId);

    /**
     * 이용약관 조회 조건을 이용하여 이용약관 목록 조회
     * 
     * @param terms 조회 조건을 전달하기 위한 객체
     * @return
     */
    public List<Terms> list(final Terms terms);

}
