package kr.mook.terms.admin.management.persistence;

import org.apache.ibatis.annotations.Mapper;

import kr.mook.terms.common.entity.Terms;

/**
 * 이용약관 관리(저장/수정/삭제/상태변경 등)를 위한 영속성 구현
 * 
 * @version v.1.0
 * @since 2026.11.10
 * @author Jeong, Inmook
 */
@Mapper
public interface TermsManagementMapper {

    /**
     * 이용약관 정보 저장
     * 
     * @param terms 저장할 이용약관 정보
     * @return 저장된 이용약관 개수
     */
    public int save(Terms terms);

    /**
     * 
     * 이용약관 정보 수정
     * 
     * @param terms 수정할 이용약관 정보
     * @return 수정된 이용약관 개수
     */
    public int update(Terms terms);

    /**
     * 이용약관 정보 삭제
     * 
     * @param termsId 삭제할 이용약관 아이디
     * @return 삭제된 이용약관 개수
     */
    public int delete(long termsId);

    /**
     * 사용자용 이용약관 검색 테이블에 사용중인 이용약관 정보 저장
     * 
     * @param terms 사용여부가 Y인 저장된 이용약관 정보
     * @return 저장된 이용약관 개수
     */
    public int saveUserTermsSearch(Terms terms);

    /**
     * 사용자용 이용약관 검색 테이블에 사용중인 이용약관 정보 수정
     * 
     * @param terms 사용여부가 Y인 수정된 이용약관 정보
     * @return 수정된 이용약관 개수
     */
    public int updateUserTermsSearch(Terms terms);

    /**
     * 사용자용 이용약관 검색 테이블에 사용중인 이용약관 정보 삭제
     * 
     * @param termsId 사용여부가 N인 삭제 대상 이용약관 아이디
     * @return 삭제된 이용약관 개수
     */
    public int deleteUserTermsSearch(long termsId);
    
}
