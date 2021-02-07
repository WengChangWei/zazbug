package com.zazbug.blog.pojo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "df_book")
public class Book implements Serializable{

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "admin_id")
	private Integer admin_id;
	@Column(name = "text")
	private String text;
	@Column(name = "cover")
	private String cover;
	@Column(name = "addTime")
	private Date addTime;
	@Column(name = "updateTime")
	private Date updateTime;

}
