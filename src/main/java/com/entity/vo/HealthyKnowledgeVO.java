package com.entity.vo;

import com.entity.HealthyKnowledgeEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 健康知识
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("HealthyKnowledge")
public class HealthyKnowledgeVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 健康知识名称
     */

    @TableField(value = "HealthyKnowledge_name")
    private String HealthyKnowledgeName;


    /**
     * 健康知识图片
     */

    @TableField(value = "HealthyKnowledge_photo")
    private String HealthyKnowledgePhoto;


    /**
     * 健康知识类型
     */

    @TableField(value = "HealthyKnowledge_types")
    private Integer HealthyKnowledgeTypes;


    /**
     * 健康知识发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 健康知识详情
     */

    @TableField(value = "HealthyKnowledge_content")
    private String HealthyKnowledgeContent;


    /**
     * 创建时间 show1 show2 nameShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：健康知识名称
	 */
    public String getHealthyKnowledgeName() {
        return HealthyKnowledgeName;
    }


    /**
	 * 获取：健康知识名称
	 */

    public void setHealthyKnowledgeName(String HealthyKnowledgeName) {
        this.HealthyKnowledgeName = HealthyKnowledgeName;
    }
    /**
	 * 设置：健康知识图片
	 */
    public String getHealthyKnowledgePhoto() {
        return HealthyKnowledgePhoto;
    }


    /**
	 * 获取：健康知识图片
	 */

    public void setHealthyKnowledgePhoto(String HealthyKnowledgePhoto) {
        this.HealthyKnowledgePhoto = HealthyKnowledgePhoto;
    }
    /**
	 * 设置：健康知识类型
	 */
    public Integer getHealthyKnowledgeTypes() {
        return HealthyKnowledgeTypes;
    }


    /**
	 * 获取：健康知识类型
	 */

    public void setHealthyKnowledgeTypes(Integer HealthyKnowledgeTypes) {
        this.HealthyKnowledgeTypes = HealthyKnowledgeTypes;
    }
    /**
	 * 设置：健康知识发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：健康知识发布时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：健康知识详情
	 */
    public String getHealthyKnowledgeContent() {
        return HealthyKnowledgeContent;
    }


    /**
	 * 获取：健康知识详情
	 */

    public void setHealthyKnowledgeContent(String HealthyKnowledgeContent) {
        this.HealthyKnowledgeContent = HealthyKnowledgeContent;
    }
    /**
	 * 设置：创建时间 show1 show2 nameShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show1 show2 nameShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
