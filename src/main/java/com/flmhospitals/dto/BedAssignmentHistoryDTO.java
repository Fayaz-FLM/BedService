package com.flmhospitals.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BedAssignmentHistoryDTO {
	
	private long BedAssignemntHistoryId;
	
	private long bedId;
	
	private int bedNum;
	
//	private RoomDetailsResponseDTO roomDetailsResponseDTO;
	
	private String patientId;
	
	private LocalDateTime assignedAt;
	
	private LocalDateTime vacatedAt;

	public BedAssignmentHistoryDTO(long bedId, int bedNum, String patientId, LocalDateTime assignedAt) {
		super();
		this.bedId = bedId;
		this.bedNum = bedNum;
		this.patientId = patientId;
		this.assignedAt = assignedAt;
	}

	public BedAssignmentHistoryDTO(LocalDateTime vacatedAt,long bedId, int bedNum, String patientId) {
		super();
		this.bedId = bedId;
		this.bedNum = bedNum;
		this.patientId = patientId;
		this.vacatedAt = vacatedAt;
	}
	
	
	
}
