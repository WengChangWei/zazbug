package com.zazbug.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "df_images")
public class Images {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "url")
	private String url;
	@Column(name = "cateId1")
	private Integer cateId1;
	@Column(name = "cateId2")
	private Integer cateId2;
	@Column(name = "isShow")
	private String isShow;
	@JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "addTime")
	private Date addTime;

	private Category categoryByOne;

	private Category categoryByTwo;

}
