package com.ultima.Senorial.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Meal {
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("imageType")
	private String imageType;
	@JsonProperty("readyInMinutes")
	private Integer readyInMinutes;
	@JsonProperty("servings")
	private Integer servings;
	@JsonProperty("sourceUrl")
	private String link;
	private List<String> imageUrls;



	private String cleanTitle;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	public Integer getReadyInMinutes() {
		return readyInMinutes;
	}
	public void setReadyInMinutes(Integer readyInMinutes) {
		this.readyInMinutes = readyInMinutes;
	}
	public Integer getServings() {
		return servings;
	}
	public void setServings(Integer servings) {
		this.servings = servings;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCleanTitle() {
		return cleanTitle;
	}
	public void setCleanTitle(String cleanTitle) {
		this.cleanTitle = cleanTitle;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	@Override
	public String toString() {
		return "Meal [id=" + id + ", title=" + title  + ", imageUrls=" + imageUrls
				+ ", readyInMinutes=" + readyInMinutes + ", servings=" + servings + ", link=" + link + ", cleanTitle="
				+ cleanTitle + ", imageType=" + imageType + "]";
	}
}
