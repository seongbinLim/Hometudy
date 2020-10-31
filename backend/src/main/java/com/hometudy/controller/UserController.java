package com.hometudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.spring5.SpringTemplateEngine;

import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;

import com.hometudy.dto.BasicResponse;
import com.hometudy.dto.SignupRequest;
import com.hometudy.dto.User;
import com.hometudy.dto.UserInfoRequest;
import com.hometudy.service.UserService;

@ApiResponses(value = {
    @ApiResponse(
        code = 401,
        message = "Unauthorized",
        response = BasicResponse.class
    ),
    @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
    @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
    @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class)
})


@CrossOrigin(origins = { "*" })
@RestController
public class UserController {
	@Autowired
	UserService userSerive;
    @Autowired public JavaMailSender javaMailSender;
    @Autowired public SpringTemplateEngine templateEngine;

    @GetMapping("/user/login")
    @ApiOperation(value = "로그인")
    public Object login(
        @RequestParam(required = true)final String uid,
        @RequestParam(required = true)final String password,
        HttpSession session
    ) {

        Optional<User> userOpt = userSerive.findByUidAndPassword(uid,password);
        ResponseEntity response = null;
        System
            .out
            .println("in here");

        if (userOpt.isPresent()) {

            // 로그인 세션 저장
            session.setAttribute("user", userOpt);
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "로그아웃")
    public Object logout(HttpSession session) {

        ResponseEntity response = null;
        session.invalidate();

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;

    }

    @PostMapping("/user/join")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody SignupRequest request)throws IOException, MessagingException {

        ResponseEntity response = null;

        System
            .out
            .println("Join in");

        String email = request.getEmail();
        String uid = request.getUid();
        String password = request.getPassword();
        String name = request.getName();
        String phone = request.getPhone();
		int money = request.getMoney();
		
		String duplicateResult = userSerive.checkDuplicate(uid,email);
        if (duplicateResult.equals("null")) {

            User user = new User(
                uid,
                password,
                email,
                name,
                phone,
			    money
            );

            User insertResult = userSerive.join(user);

            System
                .out
                .println(insertResult);

            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.data = "success";

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
                final BasicResponse result = new BasicResponse();
                result.status = false;
                result.data = "Uid&Email";
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    // 유저 정보 반환
    @PostMapping("/user/userinfo")
    @ApiOperation(value = "유저 정보 반환하기")
    public Object userinfo(@Valid @RequestBody UserInfoRequest request)throws IOException, MessagingException {
        String uid = request.getUid();
        User user = userSerive.findByUid(uid);

        if (user == null) 
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        else 
            return new ResponseEntity<User>(user, HttpStatus.OK);

        }
    
    //탈퇴하기
    @DeleteMapping("/user/delete")
    @ApiOperation(value = " 탈퇴하기")
    public Object delete(HttpSession session)throws IOException, MessagingException {

        User user = (User)session.getAttribute("user");
        String uid = user.getUid();
        userSerive.delete(uid);
        session.invalidate();

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    //데이터 수정
    @PutMapping("/user/update")
    @ApiOperation(value = "수정하기")
    public Object update(@Valid @RequestBody SignupRequest request)throws IOException, MessagingException {

        String email = request.getEmail();
        String uid = request.getUid();
        String password = request.getPassword();
        String name = request.getName();
        String phone = request.getPhone();
        int money = request.getMoney();

        User user = new User(
            uid,
            password,
            email,
            name,
            phone,
            money
        );
        userSerive.update(user);

        // 화면에 표시할 유저 정보.
        User userData = userSerive.findByEmail(email);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

        // //내 소개 수정
        // @PutMapping("/account/update/description")
        // @ApiOperation(value = "소개 수정하기")
        // public Object desUpdate(@Valid @RequestBody DesRequest request)throws IOException, MessagingException {

        //     String uid = request.getUid();
        //     String description = request.getDescription();

        //     User user = userDao.findUserByUid(uid);
        //     user.setDescription(description);
        //     userDao.save(user);

        //     final BasicResponse result = new BasicResponse();
        //     result.status = true;
        //     result.data = "success";

        //     return new ResponseEntity<>(result, HttpStatus.OK);
        // }

    @GetMapping("/user/email_auth")
    @ApiOperation(value = "이메일인증")
    public Object sendMail(@RequestParam(required = true)final String email)throws MessagingException, IOException {
        String sendTo = email; //= email;//보내려는 이메일 주소 작성
        String mailTitle = "인증이다";
        int randomNum = (int)(Math.random() * (9999 - 1000)) + 1000;
        String mailContent = "인증번호 : " + Integer.toString(randomNum);
        System
            .out
            .println("메일 인증 시도");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mailTitle);
        helper.setTo(sendTo);
        helper.setText(mailContent, true);
        helper.setFrom("tt");

        javaMailSender.send(message);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = Integer.toString(randomNum);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }		
	/*
	 * @PostMapping("/login") public ResponseEntity<String> login(@RequestBody User
	 * user) { String userId = user.getUid(); String userPassword =
	 * user.getPassword();
	 * 
	 * return ""; }
	 */
	

}
