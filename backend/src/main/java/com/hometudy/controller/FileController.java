package com.hometudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.hometudy.dto.Material;
import com.hometudy.dto.Room;
import com.hometudy.service.MaterialService;
import com.hometudy.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = { "*" })
@RestController
public class FileController {
    @Autowired
    RoomService roomService;
    @Autowired
    MaterialService materialService;

    @PostMapping(value = "/uploadImg/{roomno}")
    public ResponseEntity fileupload(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile,
            @PathVariable int roomno) {
        String path = "C:\\Users\\yagee\\";
        try {
            mFile.transferTo(new File(path + mFile.getOriginalFilename()));
            Room room = roomService.findByRoomno(roomno);
            room.setSrc(path + mFile.getOriginalFilename());
            roomService.update(room, roomno);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
 
    @PostMapping(value = "/upload/material/{roomno}/{uid}")
    public ResponseEntity fileupload(@RequestParam("filename") MultipartFile mFile,
                                    @PathVariable int roomno, @PathVariable String uid) {
        try {
            String path = "C:\\Users\\SeongbinLim\\file\\";
            String filename = mFile.getOriginalFilename();
            mFile.transferTo(new File(path + filename));
            
            Material material = new Material();
            material.setFilename(filename);
            material.setSrc(path);
            material.setRoomno(roomno);
            material.setUid(uid);
            materialService.create(material);

        } catch(IllegalStateException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/download/material/{materialno}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int materialno) throws FileNotFoundException {
        Material material = materialService.findByMaterialno(materialno);
        String filename = material.getFilename();
        String filepath = material.getSrc();
        File file = new File(filepath+filename);
		
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
                /*
                Path path = Paths.get("C:/Users/001/Pictures/anise-4623554_1280.png");
                String contentType = Files.probeContentType(path);
                
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, contentType);
                //headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
                Resource resource = new InputStreamResource(Files.newInputStream(path));
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
                */
   }
}
