 package com.bus.reservation.entity;

import java.util.List;

import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
 @Table(name="bookings")
 public class Booking{
	 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name="users")
	 private User user;
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name="buses")
	 private Bus bus;
	 @OneToMany(mappedBy="booking", cascade=cascadeType.All fetch=FetchType.EAGER)
	 private List<Passenger>passengers;
	 
	 
 }