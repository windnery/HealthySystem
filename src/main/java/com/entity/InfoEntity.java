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
 * 通知
 *
 * @author 
 * @email
 */
@TableName("Info")
public class InfoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public InfoEntity() {

	}

	public InfoEntity(T t) {
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
     * 通知名称
     */
    @TableField(value = "Info_name")

    private String InfoName;


    /**
     * 通知图片
     */
    @TableField(value = "Info_photo")

    private String InfoPhoto;


    /**
     * 通知类型
     */
    @TableField(value = "Info_types")

    private Integer InfoTypes;


    /**
     * 通知发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 通知详情
     */
    @TableField(value = "Info_content")

    private String InfoContent;


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
	 * 设置：通知名称
	 */
    public String getInfoName() {
        return InfoName;
    }
    /**
	 * 获取：通知名称
	 */

    public void setInfoName(String InfoName) {
        this.InfoName = InfoName;
    }
    /**
	 * 设置：通知图片
	 */
    public String getInfoPhoto() {
        return InfoPhoto;
    }
    /**
	 * 获取：通知图片
	 */

    public void setInfoPhoto(String InfoPhoto) {
        this.InfoPhoto = InfoPhoto;
    }
    /**
	 * 设置：通知类型
	 */
    public Integer getInfoTypes() {
        return InfoTypes;
    }
    /**
	 * 获取：通知类型
	 */

    public void setInfoTypes(Integer InfoTypes) {
        this.InfoTypes = InfoTypes;
    }
    /**
	 * 设置：通知发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：通知发布时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：通知详情
	 */
    public String getInfoContent() {
        return InfoContent;
    }
    /**
	 * 获取：通知详情
	 */

    public void setInfoContent(String InfoContent) {
        this.InfoContent = InfoContent;
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
        return "Info{" +
            "id=" + id +
            ", InfoName=" + InfoName +
            ", InfoPhoto=" + InfoPhoto +
            ", InfoTypes=" + InfoTypes +
            ", insertTime=" + insertTime +
            ", InfoContent=" + InfoContent +
            ", createTime=" + createTime +
        "}";
    }
}
