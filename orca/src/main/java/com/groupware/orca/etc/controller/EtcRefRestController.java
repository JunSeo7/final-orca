package com.groupware.orca.etc.controller;

import com.groupware.orca.etc.service.EtcRefService;
import com.groupware.orca.etc.vo.EtcRefVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/etcRef")
public class EtcRefRestController {

    private final EtcRefService service;

    // 기타 코드 등록
    @PostMapping("registrationEtcCode")
    public ResponseEntity<EtcRefVo> registrationEtcCode(EtcRefVo vo){
        int result = service.registrationEtcCode(vo);

        if(result != 1){
            throw new RuntimeException();
        }

        return ResponseEntity.ok(vo);

    }

    // 기타 코드 수정
    @PostMapping("editEtcCode")
    public void editEtcCode(EtcRefVo vo){
        service.editEtcCode(vo);
    }

    // 기타 코드 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteECode(String etcCode){
        int result = service.deleteECode(etcCode);

        if(result != 1){
            throw new RuntimeException();
        }

        return ResponseEntity.ok("delete eCode !");

    }

}
