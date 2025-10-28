package com.flmhospitals.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseDto {

	private long roomId;
	
	private long roomNumber;

	private String roomType;

	private long roomCapacity;

	private List<BedDetailsResponseDTO> beds;
}
