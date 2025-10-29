package com.flmhospitals.model;

import java.util.List;

import com.flmhospitals.dto.BedRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Bed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bedId;
	
	private long bedNumber;
	
	private boolean isOccupied;
	
	private long patientId;
	
	
	@OneToMany(mappedBy = "bed",cascade = CascadeType.ALL)
	private List<BedAssignmentHistory> bedAssignmentHistoryList;

	public Bed(long bedNumber, boolean isOccupied, long patientId,
			List<BedAssignmentHistory> bedAssignmentHistoryList) {
		super();
		this.bedNumber = bedNumber;
		this.isOccupied = isOccupied;
		this.patientId = patientId;
		this.bedAssignmentHistoryList = bedAssignmentHistoryList;
	}
	
	

}
