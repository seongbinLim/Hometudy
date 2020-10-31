package com.hometudy.service;

import java.util.Optional;

import com.hometudy.dao.UserDao;
import com.hometudy.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userdao;
    //c
    public User join(User user){
        return userdao.save(user);
    }
    //r
    //이메일이나 아이디 있는지 조회
    public String checkDuplicate(String uid,String email){
        User checkEmail = userdao.findUserByEmail(email);
        User checkUid = userdao.findUserByUid(uid);
        
        if(checkEmail== null){ // email이 중복 O
            if(checkUid == null) return "Uid&Email";
            else return "email";
        } 
        else{//email 중복  X
            if(checkUid == null) return "uid";
            else return "null";
        }
    }
    //이메일로 유저 조회
    public User findByEmail(String email){
        return userdao.findUserByEmail(email);
    }
    //아이디로 유저 조회
    public User findByUid(String uid){
        return userdao.findUserByUid(uid);
    }

    //u
    public User update(User user){
        return userdao.save(user);
    }
    
    public Optional<User> findByUidAndPassword(String uid,String password){
        return userdao.findUserByUidAndPassword(uid,password);

    }
    //d
    public void delete(String uid){
        userdao.deleteByUid(uid);
    }
}
