package com.hometudy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.hometudy.dto.User;

public interface UserDao extends JpaRepository<User, String> {
    
    
    //중복확인
    Optional<User> findUserByEmailOrUid(String email,String uid);


    // 조회
    User findUserByEmail(String email);

    // uid로 유저 정보 조회
    User findUserByUid(String uid);

    // 로그인
    Optional<User> findUserByUidAndPassword(String uid, String password);

    //c, u
    User save(User user);

    //d
    Optional<User> deleteByUid(String userid);    
    


}
