package com.hometudy.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    @RequestMapping("/index2")
    public String index(HttpServletRequest request) {
        return "/index2";
    }
    @RequestMapping("/fileadd")
    public String fileadd(HttpServletRequest request) {
        return "/fileadd";
    }
    @RequestMapping(value="/fileupload", method= RequestMethod.POST)
    public String fileupload(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile) {
        System.out.println("file upload -----------------------------------------------------------");
        try {
            mFile.transferTo(new File("C:\\Users\\multicampus\\file\\"+mFile.getOriginalFilename()));
        } catch(IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return "/fileupload";
    }

    @RequestMapping(value="/image/{roomno}", method= RequestMethod.POST)
    public String fileupload(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile, @PathVariable int roomno) {
        String path = "C:\\Users\\multicampus\\file\\";
        try {
            mFile.transferTo(new File(path+mFile.getOriginalFilename()));

        } catch(IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return "/fileupload";
    }
}
