package com.groupware.orca.etc.controller;

import com.groupware.orca.document.service.DocumentService;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.etc.service.EtcService;
import com.groupware.orca.etc.vo.EtcRefVo;
import com.groupware.orca.etc.vo.EtcVo;
import com.groupware.orca.user.vo.UserVo;
import com.groupware.orca.vacation.vo.VacationVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/etc")
public class etcRestController {

    private final EtcService service;
    private final DocumentService documentService;

    // 기타 코드 불러오기
    @GetMapping
    public ResponseEntity<List<EtcRefVo>> loadEtcCode(){
        List<EtcRefVo> eVo = service.loadEtcCode();
        return ResponseEntity.ok(eVo);

    }

    // 기타 신청
    @PostMapping
    @Transactional
    public void enrollEtc(EtcVo vo, HttpSession httpSession){
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

        service.enrollEtc(vo);

    }

}
