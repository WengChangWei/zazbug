package com.zazbug.blog.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "df_category")
public class Category {
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "isShow")
	private String isShow;

	@Column(name = "sort")
	private Integer sort;

	@Column(name = "parentId")
	private int parentId;

	private Category parentCate;

	private List<Category> children;

}
