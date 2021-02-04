package com.zazbug.blog.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "df_category")
public class Category {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "isShow")
	private String isShow;

	@Column(name = "sort")
	private int sort;

	@Column(name = "parentId")
	private int parentId;

	private Category parentCate;

	private List<Category> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public Category getParentCate() {
		return parentCate;
	}

	public void setParentCate(Category parentCate) {
		this.parentCate = parentCate;
	}
}
