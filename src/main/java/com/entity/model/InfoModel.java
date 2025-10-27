package com.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 通知
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class InfoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 通知名称
     */
    private String InfoName;


    /**
     * 通知图片
     */
    private String InfoPhoto;


    /**
     * 通知类型
     */
    private Integer InfoTypes;


    /**
     * 通知发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 通知详情
     */
    private String InfoContent;


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
	 * 获取：通知名称
	 */
    public String getInfoName() {
        return InfoName;
    }


    /**
	 * 设置：通知名称
	 */
    public void setInfoName(String InfoName) {
        this.InfoName = InfoName;
    }
    /**
	 * 获取：通知图片
	 */
    public String getInfoPhoto() {
        return InfoPhoto;
    }


    /**
	 * 设置：通知图片
	 */
    public void setInfoPhoto(String InfoPhoto) {
        this.InfoPhoto = InfoPhoto;
    }
    /**
	 * 获取：通知类型
	 */
    public Integer getInfoTypes() {
        return InfoTypes;
    }


    /**
	 * 设置：通知类型
	 */
    public void setInfoTypes(Integer InfoTypes) {
        this.InfoTypes = InfoTypes;
    }
    /**
	 * 获取：通知发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：通知发布时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：通知详情
	 */
    public String getInfoContent() {
        return InfoContent;
    }


    /**
	 * 设置：通知详情
	 */
    public void setInfoContent(String InfoContent) {
        this.InfoContent = InfoContent;
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
