package com.manh.doantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manh.doantotnghiep.bean.entity.ImageEntity;

public interface ImageDao extends JpaRepository<ImageEntity, Integer>{
    
    @Query(value = "SELECT * FROM images i WHERE i.type = :type AND i.parent_id = :parentId",  nativeQuery = true)
    List<ImageEntity> getListImageByTypeAndParentId(@Param("type") String type,@Param("parentId") Integer parentId);
    
    @Query(value = "DELETE * FROM images i WHERE i.type = :type AND i.parent_id = :parentId",  nativeQuery = true)
    List<ImageEntity> deleteImageByTypeAndParentId(@Param("type") String type,@Param("parentId") Integer parentId);

}
