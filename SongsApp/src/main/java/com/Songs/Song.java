package com.Songs;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
@Entity
@Table(name="Song")
public class Song{
	@Id
	private int Id;
	private String name;
	private int std;
	public Song() {}
	public Song(int Id, String name, int std) {
		this.Id=Id;
		this.name=name;
		this.std=std;
	}
	public int getId() {return Id;}
	public void setId(int Id) {this.Id=Id;}
	public String getName() {return name;}
	public void setName(String name) {this.name=name;}
	public int getStd(){return std;}
	public void setStd(int std) {this.std=std;}
	
}
