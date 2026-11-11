package kr.mook.terms.common.entity;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 이용약관 정보 엔티티
 * 
 * @version v.1.0
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Terms {

    @Schema(name = "이용약관 아이디", description = "이용약관 아이디는 저장할 때 자동으로 할당된다.", example = "1")
    private Long termsId;

    @Schema(name = "이용약관 제목", description = "이용약관 제목은 관리자가 작성한다.", example = "사이트 이용에 관한 동의")
    private String title;

    @Schema(name = "이용약관 내용", description = "이용약관 내용은 관리자가 작성한다.", example = "사이트 이용 시 사용자의 동작, 전달된 데이터 등을 수집할 수 있습니다.")
    private String contents;

    @Schema(name = "이용약관 내용 유형", description = "일반 텍스트(TEXT), JSON, HTML, 마크다운(MARKDOWN) 형식 중 이용약관의 내용을 작성한 형식을 전달한다.", example = "TEXT")
    private String contentType;

    @Schema(name = "출력 순번", description = "이용약관 목록 조회 시 출력 순번을 결정한다. 출력 순번이 낮을수록 가장 먼저 출력된다.", example = "1")
    private int orderNo;

    @Schema(name = "사용 여부", description = "이용약관 정보를 사용하여 일반 사용자에게 공개할지 결정한다.", example = "Y")
    private String useYn;

    @Schema(name = "필수 여부", description = "회원가입 시 필수로 동의해야 하는지 결정한다.", example = "Y")
    private String requireYn;

    @Schema(name = "생성자 아이디", description = "이용약관 정보를 최초 생성한 회원 아이디를 저장한다.", example = "123")
    private Long createId;

    @Schema(name = "생성일시", description = "이용약관 정보가 최초로 저장된 날짜와 시간을 저장한다.", example = "2026-11-01 12:23:45")
    private LocalDateTime createDate;

    @Schema(name = "수정자 아이디", description = "이용약관 정보를 수정한 회원 아이디를 저장한다.", example = "123")
    private Long updateId;

    @Schema(name = "수정일시", description = "이용약관 정보가 수정된 날짜와 시간을 저장한다.", example = "2026-11-01 12:23:45")
    private LocalDateTime updateDate;
}
