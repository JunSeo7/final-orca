package com.groupware.orca.sick.controller;

import com.groupware.orca.document.service.DocumentService;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.sick.service.SickService;
import com.groupware.orca.sick.vo.SickVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/sick")
public class SickRestController {

    private final SickService service;
    private final DocumentService documentService;

    // 병가 신청
    @PostMapping
    @Transactional
    public void enrollSick(SickVo vo, HttpSession httpSession){
        int empNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setEmpNo(empNo);

        // 결재 문서 생성
        DocumentVo documentVo = new DocumentVo();
        documentVo.setWriterNo(empNo);
        documentVo.setTitle(documentVo.getTitle());
        documentVo.setCategoryNo(documentVo.getCategoryNo());
        documentVo.setTemplateNo(documentVo.getTemplateNo());

        String content = String.format("기타 신청\n시작 날짜: %s\n종료 날짜: %s\n사유: %s",
                vo.getStartDate(), vo.getExpiryDate(), vo.getReason());
        documentVo.setContent(content);

        int docNo = documentService.writeDocument(documentVo);

        vo.setDocNo(docNo);

        service.enrollSick(vo);
    }

}
