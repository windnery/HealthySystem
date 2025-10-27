package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 健康知识
 *
 * @author 
 * @email
 */
@TableName("HealthyKnowledge")
public class HealthyKnowledgeEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HealthyKnowledgeEntity() {

	}

	public HealthyKnowledgeEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 健康知识详情
     */
    @TableField(value = "HealthyKnowledge_content")

    private String HealthyKnowledgeContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "HealthyKnowledge{" +
            "id=" + id +
            ", HealthyKnowledgeName=" + HealthyKnowledgeName +
            ", HealthyKnowledgePhoto=" + HealthyKnowledgePhoto +
            ", HealthyKnowledgeTypes=" + HealthyKnowledgeTypes +
            ", insertTime=" + insertTime +
            ", HealthyKnowledgeContent=" + HealthyKnowledgeContent +
            ", createTime=" + createTime +
        "}";
    }
}
