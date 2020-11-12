package com.hometudy.dao;

import java.util.List;

import com.hometudy.dto.Material;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDao  extends JpaRepository<Material, Integer>{
    
    // int materialno;
    // int roomno;
    // String filename;
    // String src;
    // String uid;
    //c,u
    Material save(Material material);


    //r
    Material findMaterialByMaterialno(int materialno);
    List<Material> findMaterialByRoomno(int roomno);
    List<Material> findMaterialByFilename(String filename);
    Material findMaterialBySrc(String src);
    List<Material> findMaterialByUid(String uid);
    

    //d
    Material deleteByMaterialno(int materialno);

}
