package com.normal.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "events")
@NoArgsConstructor @AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String pinCode;
    private boolean galleryPublished = false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public void setGalleryPublished(boolean galleryPublished) {
		this.galleryPublished = galleryPublished;
	}
	public boolean isGalleryPublished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
