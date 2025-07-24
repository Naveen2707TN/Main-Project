package com.spring.backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.backend.Entity.messageEntity;

import jakarta.transaction.Transactional;

@Repository
public interface messageRepo extends JpaRepository<messageEntity, Long>{ 
    
    @Query(value = "select * from message where (sedid=:sid and recid=:rid) or (sedid=:rid and recid=:sid) order by senddate", nativeQuery = true)
    List<messageEntity> listMessage(Long sid, Long rid);

    @Modifying
    @Transactional
    @Query(value = "Delete from message where (sedid =:sid and recid =:rid) and msg =:msg", nativeQuery = true)
    void deleteMessage(Long sid, Long rid, String msg);
}
