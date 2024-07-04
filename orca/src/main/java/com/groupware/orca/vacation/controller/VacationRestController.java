package com.groupware.orca.vacation.controller;

import com.groupware.orca.document.service.DocumentService;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.user.vo.UserVo;
import com.groupware.orca.vacation.service.VacationService;
import com.groupware.orca.vacation.vo.VacationRefVo;
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
@RequestMapping("orca/re/vacation")
public class VacationRestController {

    private final VacationService service;
    private final DocumentService documentService;

    // 휴가 코드 불러오기
    @GetMapping
    public ResponseEntity<List<VacationRefVo>> loadVacationCode(){
        List<VacationRefVo> VRVo = service.loadVacationCode();
        return ResponseEntity.ok(VRVo);

    }

    // 휴가 신청
    @PostMapping
    @Transactional
    public void enrollVacation(VacationVo vo, HttpSession httpSession){
        String empNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setEmpNo(empNo);

        // 결재 문서 생성
        DocumentVo documentVo = new DocumentVo();
        documentVo.setWriterNo(Integer.parseInt(empNo));
        documentVo.setTitle(documentVo.getTitle());
        documentVo.setCategoryNo(documentVo.getCategoryNo());
        documentVo.setTemplateNo(documentVo.getTemplateNo());

        String content = String.format("휴가 신청\n시작 날짜: %s\n종료 날짜: %s\n사유: %s",
                vo.getStartDate(), vo.getExpiryDate(), vo.getReason());
        documentVo.setContent(content);

        int docNo = documentService.writeDocument(documentVo);

        vo.setDocNo(docNo);

        service.enrollVacation(vo);

    }

}
