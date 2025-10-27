package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 通知
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("Info")
public class InfoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 通知详情
     */

    @TableField(value = "Info_content")
    private String InfoContent;


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
