package com.flmhospitals.bulider;

import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.model.Bed;

public class BedBuilder {

	public static Bed buildBedFromBedDto(BedRequestDTO bedDto) {
		
		return Bed.builder()
				  .bedNumber(bedDto.getBedNumber())
				  .isOccupied(bedDto.isOccupied())
				  .
				  .build();
	}

}
