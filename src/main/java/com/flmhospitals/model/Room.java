package com.flmhospitals.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Rooms")
@Builder
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roomId;
	
	private long roomNumber;
	
	private String roomType;
	
	private long roomCapacity;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<Bed> beds;

	public Room(long roomNumber, String roomType, long roomCapacity, List<Bed> beds) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomCapacity = roomCapacity;
		this.beds = beds;
	}
	
	

}
