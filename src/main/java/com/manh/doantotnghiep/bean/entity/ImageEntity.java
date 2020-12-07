package com.manh.doantotnghiep.bean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
public class ImageEntity extends CommonEntity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "type")
    private String type;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public String getType() {
        return this.type;
    }
}
