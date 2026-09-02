package com.bus.reservation.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

public class BookingRequestDTO {
    private Long busId;
    private String fromLocation;
    private String toLocation;
    private LocalDate travelDate;
    private List<PassengerDTO> passengers;
	public BookingRequestDTO(Long busId, String fromLocation, String toLocation, LocalDate travelDate,
			List<PassengerDTO> passengers) {
		super();
		this.busId = busId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.travelDate = travelDate;
		this.passengers = passengers;
	}
	public Long getBusId() {
		return busId;
	}
	public void setBusId(Long busId) {
		this.busId = busId;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public LocalDate getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	public List<PassengerDTO> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerDTO> passengers) {
		this.passengers = passengers;
	}
	@Override
	public String toString() {
		return "BookingRequestDTO [busId=" + busId + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation
				+ ", travelDate=" + travelDate + ", passengers=" + passengers + "]";
	}
    
}
