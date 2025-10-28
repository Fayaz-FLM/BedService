package com.flmhospitals.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedRequestDTO {
	
	private long bedNumber;
	
	private long roomNumber;
	
	private boolean isOccupied;
	
	private List<BedAssignmentHistoryDTO> bedAssignmentHistoryDTOs;
	 
}
