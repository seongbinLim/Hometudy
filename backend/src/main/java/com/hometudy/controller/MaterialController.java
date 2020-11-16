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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.hometudy.dto.BasicResponse;
import com.hometudy.dto.Material;
import com.hometudy.dto.User;
import com.hometudy.service.MaterialService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import io.swagger.annotations.ApiOperation;

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
public class MaterialController {
    @Autowired
    MaterialService materialService;


    //C
    @PostMapping("/material/create")
    @ApiOperation(value="자료업로드")
    public Object create(
        @Valid @RequestBody Material request
    ){    
        
        ResponseEntity response = null;
        Material newmaterial = new Material();
        newmaterial.setUid(request.getUid());
        newmaterial.setFilename(request.getFilename());
        newmaterial.setSrc(request.getSrc());
        newmaterial.setRoomno(request.getRoomno());


        materialService.create(newmaterial);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        response = new ResponseEntity<>(result,HttpStatus.OK);

        return response;
    }


    //R
    //1. 방번호로 찾기
    @GetMapping("/material/findByRoomno")
    @ApiOperation(value="방번호로찾기")
    public Object findByRoomno(@RequestParam(required = true)final int no){
        List<Material> roomList = materialService.findByRoomno(no);
        
        if(roomList.isEmpty()) {
            return new ResponseEntity<List<Material>>(roomList, HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<List<Material>>(roomList, HttpStatus.OK);
        }


    }
    //2. 사용자로 찾기
    @GetMapping("/material/findByUid")
    @ApiOperation(value="유저로찾기")
    public Object findByUid(@RequestParam(required = true)final String uid){
        List<Material> List = materialService.findByUid(uid);
        
        if(List.isEmpty()) {
            return new ResponseEntity<List<Material>>(List, HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<List<Material>>(List, HttpStatus.OK);
        }


    }
    //3. 파일 번호로 찾기
    @GetMapping("/material/findByMaterialno")
    @ApiOperation(value="파일번호로찾기")
    public Object findByMaterailno(@RequestParam(required = true)final int no){
        Material material= materialService.findByMaterialno(no);
        
        if(material == null) {
            return new ResponseEntity<Material>(material, HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<Material>(material, HttpStatus.OK);
        }


    }
    //4. 파일이름으로 찾기
    @GetMapping("/material/findByFilename")
    @ApiOperation(value="파일이름으로찾기")
    public Object findByFilename(@RequestParam(required = true)final String filename){
        List<Material> material= materialService.findByFilename(filename);
        
        if(material == null) {
            return new ResponseEntity<List<Material>>(material, HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<List<Material>>(material, HttpStatus.OK);
        }


    }

    //5.파일경로로 찾기



    //u
    @PostMapping("/material/update")
    @ApiOperation(value = "파일업데이트하기")
    public Object update(@Valid @RequestBody Material material){
        int materialno =  material.getMaterialno();
        int roomno = material.getRoomno();
        String filename = material.getFilename();
        String src = material.getSrc();
        String uid = material.getUid();
        

        Material newMaterial = new Material(materialno, roomno, filename, src, uid);

        materialService.update(newMaterial,materialno);

            
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    //d
    @DeleteMapping("/material/deleteByUid")
    @ApiOperation(value = "uid로 삭제하기")
    public Object deleteByUid(@RequestParam(required = true)final String uid){
        materialService.deleteByUid(uid);
            
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/material/deleteByRoomno")
    @ApiOperation(value = "roomno로 삭제하기")
    public Object deleteByRoomno(@RequestParam(required = true)final int roomno){
        materialService.deleteByRoomno(roomno);
            
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @DeleteMapping("/material/deleteByMaterialno")
    @ApiOperation(value = "Materialno로 삭제하기")
    public Object deleteByMaterialno(@RequestParam(required = true)final int Materialno){
        materialService.delete(Materialno);
            
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
