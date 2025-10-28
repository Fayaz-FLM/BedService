package com.flmhospitals.bulider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.model.Bed;
import com.flmhospitals.model.BedAssignmentHistory;

public class BedDtoBuilder {

	public static Bed buildBedFromBedDto(Bed bed) {
		return Bed.builder()
			   .bedNumber(bed.getBedNumber())
			   .isOccupied(false)
			   .patientId(0)
			   .bedAssignmentHistoryList(buildBedAssigmentHistory(bed.getBedAssignmentHistoryList()))
			   .build();    
	}
	
	private static List<BedAssignmentHistory> buildBedAssigmentHistory(List<BedAssignmentHistory>
	bedAssignmentHistories){
		
		List<BedAssignmentHistory> bedRequestDTOs =new ArrayList<>();
		for(BedAssignmentHistory bed:bedAssignmentHistories) {
			BedRequestDTO bedRequestDTO =new BedRequestDTO();
			BeanUtils.copyProperties(bed, bedRequestDTO);
			bedRequestDTOs.add(bed);
			
		}
		return bedRequestDTOs;
	}
}
