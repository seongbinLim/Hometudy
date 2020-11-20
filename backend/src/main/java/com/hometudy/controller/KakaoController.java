package com.hometudy.controller;

import com.hometudy.api.KakaoPay;
import com.hometudy.api.KakaoPayApprovalVO;
import com.hometudy.dto.User;
import com.hometudy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@Controller
public class KakaoController {
    @Autowired
    private KakaoPay kakaopay;
    @Autowired
    private UserService userService;
    
    @PostMapping("/kakaoPay/{uid}")
    public String kakaoPay(@PathVariable String uid) {
        log.info("kakaoPay post............................................");
        
        return "redirect:" + kakaopay.kakaoPayReady(uid);
 
    }
    
    @GetMapping("/kakaoPaySuccess")
    public Model kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        log.info(model.toString());
        
        KakaoPayApprovalVO info = kakaopay.kakaoPayInfo(pg_token);
        model.addAttribute("info", info);
        String uid = info.getPartner_user_id();
        int money = info.getAmount().getTotal();
        User user = userService.findByUid(uid);
        user.setMoney(money+user.getMoney());
        userService.update(user);
        return model;
    }

    @GetMapping("/kakaoPayCancel")
    public ResponseEntity kakaoPayCancel() {
        log.info("kakaoPay Fail............................................");
        return new ResponseEntity(HttpStatus.BAD_GATEWAY);
    } 
}
