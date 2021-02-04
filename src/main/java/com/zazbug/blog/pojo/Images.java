package com.zazbug.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "df_images")
public class Images {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "url")
	private String url;
	@Column(name = "cateId1")
	private int cateId1;
	@Column(name = "cateId2")
	private int cateId2;
	@Column(name = "isShow")
	private String isShow;
	@JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "addTime")
	private Date addTime;

	private Category categoryByOne;

	private Category categoryByTwo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getCateId1() {
		return cateId1;
	}

	public void setCateId1(int cateId1) {
		this.cateId1 = cateId1;
	}

	public int getCateId2() {
		return cateId2;
	}

	public void setCateId2(int cateId2) {
		this.cateId2 = cateId2;
	}

	public Category getCategoryByOne() {
		return categoryByOne;
	}

	public void setCategoryByOne(Category categoryByOne) {
		this.categoryByOne = categoryByOne;
	}

	public Category getCategoryByTwo() {
		return categoryByTwo;
	}

	public void setCategoryByTwo(Category categoryByTwo) {
		this.categoryByTwo = categoryByTwo;
	}
}
