package com.flmhospitals.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BedDetailsResponseDTO {
	
	private long bedId;
	
	private long bedNum;
	
	private long roomNumber;
	
	private boolean isOccupied;
	

}
