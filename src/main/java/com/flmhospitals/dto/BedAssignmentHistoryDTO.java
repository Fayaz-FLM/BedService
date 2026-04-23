package com.flmhospitals.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BedAssignmentHistoryDTO {
	
	private long BedAssignmentHistoryId;
	
	private long bedNumber;
	
	private long patientId;
	
	private LocalDateTime assignedAt;
	
	private LocalDateTime vacatedAt;

	public BedAssignmentHistoryDTO(int bedNum, long patientId, LocalDateTime assignedAt) {

		this.bedNumber = bedNum;
		
		this.patientId = patientId;
		
		this.assignedAt = assignedAt;
	}

	public BedAssignmentHistoryDTO(LocalDateTime vacatedAt, int bedNum, long patientId) {

		this.bedNumber = bedNum;
		
		this.patientId = patientId;
		
		this.vacatedAt = vacatedAt;
	}
	
	
	
}
