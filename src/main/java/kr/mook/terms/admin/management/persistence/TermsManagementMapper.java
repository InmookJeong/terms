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
     * 사용자용 이용약관 검색 테이블에 사용중인 이용약관 정보 저장
     * 
     * @param terms 사용여부가 Y인 저장된 이용약관 정보
     * @return 저장된 이용약관 개수
     */
    public int saveInTermsSearch(Terms terms);
    
}
