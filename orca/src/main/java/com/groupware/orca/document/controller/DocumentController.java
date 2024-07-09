package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.DocumentService;
import com.groupware.orca.document.vo.*;
import com.groupware.orca.user.vo.UserVo;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("orca/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    // 결재 작성 화면
    @GetMapping("write")
    public String getTemplateList(){
        return "document/write";
    }
    // 결재 작성 카테고리 가져오기
    @GetMapping("categorie/list")
    @ResponseBody
    public List<TemplateVo> getCategory() {
        List<TemplateVo> templateVoList = service.getCategory();
        return templateVoList;
    }
    // 결재 작성 결재양식 제목 가져오기
    @GetMapping("template/list")
    @ResponseBody
    public List<TemplateVo> getTemplateByCategoryNo(@RequestParam int categoryNo) {
        List<TemplateVo> templateVoList= service.getTemplateByCategoryNo(categoryNo);
        return service.getTemplateByCategoryNo(categoryNo);
    }
    // 결재 작성 템플릿 내용 가져오기
    @GetMapping("template/content")
    @ResponseBody
    public TemplateVo getTemplateContent(@RequestParam("templateNo") int templateNo){
        TemplateVo template = service.getTemplateContent(templateNo);
        return template;
    }
    // 결재 작성 결재선 가져오기
    @GetMapping("template/apprline")
    @ResponseBody
    public ApprovalLineVo getTemplateApprLine(@RequestParam("templateNo") int templateNo){
        ApprovalLineVo apprline = service.getTemplateApprLine(templateNo);
        return apprline;
    }

    // 결재 작성 조직도(참조인) 가져오기 - ApprovalLineController ("orca/apprline/organization/list")

    // 결재 작성
    @PostMapping("write")
    public String writeDocument(
            DocumentVo vo,
            HttpSession httpSession,
            String[] seq,
            String[] approverNo,
            String[] deptCode,
            String[] positionCode,
            String[] approverClassificationNo,
            String[] referencerNo,
            @RequestParam("fileList") MultipartFile[] fileList,
            HttpServletRequest req) throws IOException {

        // 결재자 등록
        List<ApproverVo> approverVoList = new ArrayList<>();
        for (int i = 0; i < seq.length; ++i) {
            ApproverVo avo = new ApproverVo();
            avo.setSeq(Integer.parseInt(seq[i]));
            avo.setApproverNo(Integer.parseInt(approverNo[i]));
            avo.setDeptCode(Integer.parseInt(deptCode[i]));
            avo.setPositionCode(Integer.parseInt(positionCode[i]));
            avo.setApproverClassificationNo(Integer.parseInt(approverClassificationNo[i]));
            approverVoList.add(avo);
        }
        vo.setApproverVoList(approverVoList);

        // 참조자 등록
        List<ReferencerVo> referencerVoList = new ArrayList<>();
        if (referencerNo != null) {
            for (int i = 0; i < referencerNo.length; ++i) {
                ReferencerVo rvo = new ReferencerVo();
                rvo.setReferrerNo(Integer.parseInt(referencerNo[i]));
                referencerVoList.add(rvo);
            }
            vo.setReferencerVoList(referencerVoList);
        }

        // 파일 등록
        List<DocFileVo> fileVoList = new ArrayList<>();
        String title = vo.getTitle();

        if (fileList != null) {
            for (MultipartFile file : fileList) {
                // 파일을 서버에 저장하기
                String originFileName = file.getOriginalFilename(); // 원본 파일 이름을 가져옴
                InputStream is = file.getInputStream(); // 파일의 입력 스트림을 가져옴
                ServletContext context = req.getServletContext();
                String path = context.getRealPath("/static/upload/document/");

                java.io.File dir = new java.io.File(path); // 파일 저장 경로의 디렉토리 객체 생성
                if (!dir.exists()) {
                    dir.mkdirs(); // 디렉토리가 존재하지 않으면 생성
                }

                String random = UUID.randomUUID().toString(); // 고유한 파일 이름 생성을 위한 랜덤 문자열 생성
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                System.out.println("ext = " + ext);
                String changeName = title + System.currentTimeMillis() + "_" + random + ext;
                System.out.println("파일changeName = " + changeName);

                try (FileOutputStream fos = new FileOutputStream(path + changeName)) {
                    byte[] buf = new byte[1024]; // 파일을 읽고 쓰기 위한 버퍼 생성
                    int size;
                    while ((size = is.read(buf)) != -1) { // 입력 스트림에서 데이터를 읽어 버퍼에 저장
                        fos.write(buf, 0, size); // 버퍼에 있는 데이터를 출력 스트림에 씀
                    }
                }

                // DB에 파일 정보 저장
                DocFileVo fileVo = new DocFileVo();
                fileVo.setOriginName(originFileName);
                fileVo.setChangeName(changeName);

                fileVoList.add(fileVo);
            }
            vo.setFileVoList(fileVoList);
        }

        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(Integer.parseInt(loginUserNo));
        int result = service.writeDocument(vo);
        return "redirect:/orca/document/list";
    }


    // 올린결재
    // 내가 작성한 결재 문서 목록 조회
    @GetMapping("list")
    public String getDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }
    // (임시저장) 내가 작성한 결재 문서 목록 조회
    @GetMapping("temp")
    public String getTempDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getTempDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }
    // (종결) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @GetMapping("close")
    public String getCloseDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getCloseDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }
    // (반려) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @GetMapping("retrun")
    public String getRetrunDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getRetrunDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }
    // (결재취소) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @GetMapping("cencel")
    public String getCancelDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getCancelDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }
    // (삭제함) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    public String getDeleteDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getDeleteDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }
    // 받은 결재
    @GetMapping("/received")
    public String getSendDocumentList(Model model, HttpSession httpSession) {
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getSendDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }
    // (공람) - 종결된 결재 중 참조인에 해당하는 사람에게 보임
    @GetMapping("/public")
    public String getPublicDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getPublicDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        return "document/list";
    }

    // 검색(기안자/제목/내용/카테고리)


    // 결재 상세보기 - 기안자 no 추가 (params)
    @GetMapping("detail")
    public String getDocumentByNo(Model model, HttpSession httpSession, int docNo){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        DocumentVo document = service.getDocumentByNo(docNo);
        model.addAttribute("document", document);
        return "document/detail";
    }

    // 기안서 수정 (임시저장 상태일 경우만) // 제목, 내용, 상태(기안)만 수정가능
    @PostMapping("edit")
    public String editDocument(DocumentVo vo, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(Integer.parseInt(loginUserNo));
        int result = service.editDocument(vo);
        return "redirect:/orca/document/list";
    }

    // 기안서 상태 수정 (임시저장 상태일 경우 - 기안으로 )
    @PostMapping("updateStatus")
    public String updateStatusDocument(DocumentVo vo, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(Integer.parseInt(loginUserNo));
        int result = service.updateStatusDocument(vo);
        return "redirect:/orca/document/list";
    }

    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    @PutMapping("delete")
    public String deleteDocumentByNo(int docNo, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        int result = service.deleteDocumentByNo(docNo, loginUserNo);
        return "redirect:/orca/document/list";
    }
}