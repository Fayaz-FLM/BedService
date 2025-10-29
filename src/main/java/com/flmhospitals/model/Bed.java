package com.flmhospitals.model;

import java.util.List;

import com.flmhospitals.dto.BedRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
>>>>>>> 668d9f2e1e7c76aed38ebc045528488b4432fee0
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Beds")
@Builder
public class Bed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bedId;
	
	private long bedNumber;
	
	private boolean isOccupied;
	
	private long patientId;
	

	@ManyToOne
   @JoinColumn(name = "room_id")
	private Room room;

	
	@OneToMany(mappedBy = "bed",cascade = CascadeType.ALL)
	private List<BedAssignmentHistory> bedAssignmentHistoryList;

	public Bed(long bedNumber, boolean isOccupied, long patientId, Room room,
			List<BedAssignmentHistory> bedAssignmentHistoryList) {
		super();
		this.bedNumber = bedNumber;
		this.isOccupied = isOccupied;
		this.patientId = patientId;
		this.room = room;
		this.bedAssignmentHistoryList = bedAssignmentHistoryList;
	}


}
