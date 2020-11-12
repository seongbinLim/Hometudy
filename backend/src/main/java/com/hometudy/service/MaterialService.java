package com.hometudy.service;

import java.util.List;
import java.util.Optional;

import com.hometudy.dao.MaterialDao;
import com.hometudy.dto.Material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    @Autowired
    MaterialDao materialDao;
    //C
    public Material create(Material material){
        return materialDao.save(material);
    }

    //R
    public Material findByMaterialno(int materialno){
        return materialDao.findMaterialByMaterialno(materialno);
    }
    public List<Material> findByRoomno(int roomno){
        return materialDao.findMaterialByRoomno(roomno);
    }    
    public List<Material> findByFilename(String filename){
        return materialDao.findMaterialByFilename(filename);
    }    
    public Material findBySrc(String src){
        return materialDao.findMaterialBySrc(src);
    }
    public List<Material> findByUid(String uid){
        return materialDao.findMaterialByUid(uid);
    }
    //U
    public Material update(Material material, int materialno){
        Material find = materialDao.findMaterialByMaterialno(materialno);
        if(find!=null){
            find.setRoomno(material.getRoomno());
            find.setFilename(material.getFilename());
            find.setSrc(material.getSrc());
            find.setUid(material.getUid());
            return materialDao.save(find);
            
        }
        else return null;
    }


    //D
    public void delete(int materialno){
        materialDao.deleteByMaterialno(materialno);
    }
    public void deleteByRoomno(int roomno){
        List<Material> list = materialDao.findMaterialByRoomno(roomno);

        for(Material next: list){
            materialDao.deleteById(next.getMaterialno());
        } 
    }
    public void deleteByUid(String uid){
        List<Material> list = materialDao.findMaterialByUid(uid);
        for(Material next: list){
            materialDao.deleteById(next.getMaterialno());
        }
    }
}
