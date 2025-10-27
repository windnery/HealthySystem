package com.entity.model;

import com.entity.HealthyKnowledgeEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 健康知识
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HealthyKnowledgeModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 健康知识名称
     */
    private String HealthyKnowledgeName;


    /**
     * 健康知识图片
     */
    private String HealthyKnowledgePhoto;


    /**
     * 健康知识类型
     */
    private Integer HealthyKnowledgeTypes;


    /**
     * 健康知识发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 健康知识详情
     */
    private String HealthyKnowledgeContent;


    /**
     * 创建时间 show1 show2 nameShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：健康知识名称
	 */
    public String getHealthyKnowledgeName() {
        return HealthyKnowledgeName;
    }


    /**
	 * 设置：健康知识名称
	 */
    public void setHealthyKnowledgeName(String HealthyKnowledgeName) {
        this.HealthyKnowledgeName = HealthyKnowledgeName;
    }
    /**
	 * 获取：健康知识图片
	 */
    public String getHealthyKnowledgePhoto() {
        return HealthyKnowledgePhoto;
    }


    /**
	 * 设置：健康知识图片
	 */
    public void setHealthyKnowledgePhoto(String HealthyKnowledgePhoto) {
        this.HealthyKnowledgePhoto = HealthyKnowledgePhoto;
    }
    /**
	 * 获取：健康知识类型
	 */
    public Integer getHealthyKnowledgeTypes() {
        return HealthyKnowledgeTypes;
    }


    /**
	 * 设置：健康知识类型
	 */
    public void setHealthyKnowledgeTypes(Integer HealthyKnowledgeTypes) {
        this.HealthyKnowledgeTypes = HealthyKnowledgeTypes;
    }
    /**
	 * 获取：健康知识发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：健康知识发布时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：健康知识详情
	 */
    public String getHealthyKnowledgeContent() {
        return HealthyKnowledgeContent;
    }


    /**
	 * 设置：健康知识详情
	 */
    public void setHealthyKnowledgeContent(String HealthyKnowledgeContent) {
        this.HealthyKnowledgeContent = HealthyKnowledgeContent;
    }
    /**
	 * 获取：创建时间 show1 show2 nameShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 nameShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
