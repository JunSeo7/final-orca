package com.groupware.orca.document.vo;

import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.approvalLine.vo.ApproverVo;
import lombok.Data;

import java.util.List;

@Data
public class DocumentVo {
    // Category Information (카테고리 정보)
    private String categoryNo;         // 카테고리 번호
    private String categoryName;       // 카테고리 이름

    // Template Information (템플릿 정보)
    private int templateNo;            // 템플릿 번호
    private String templateTitle;      // 템플릿 제목
    private String templateContent;    // 템플릿 내용

    // Document Information (문서 정보)
    private int docNo;                 // 문서 번호
    private int writerNo;              // 기안자 번호
    private String writerName;         // 기안자 이름
    private int deptCode;              // 부서 코드
    private String deptName;           // 부서 이름
    private int positionCode;          // 직위 코드
    private String positionName;       // 직위 이름
    private String title;              // 문서 제목
    private String content;            // 문서 내용
    private String enrollDate;         // 작성 날짜
    private String creditDate;         // 기안 날짜
    private int status;                // 1: 임시저장 2: 기안(대기) 3: 결재 취소
    private String statusName;         // 상태 이름
    private String urgent;             // 긴급 여부 (Y/N)
    private String delYn;              // 삭제 여부 (Y/N)

    private int apprLineNo;            //결재선 번호
    private List<ApprovalLineVo> approvalLineVoList; //결재선 리스트
    private List<ApproverVo> approverVoList; //결재선(결재자) 리스트

    // File and Reference Information (파일 및 참조자 정보)

    private String file;                          //파일
    private List<DocFileVo> fileVoList;           // 파일 리스트
    private List<ReferencerVo> referencerVoList;  // 참조자 리스트
}