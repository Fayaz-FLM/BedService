package com.flmhospitals.bulider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.flmhospitals.dto.BedAssignmentHistoryDTO;
import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.model.Bed;
import com.flmhospitals.model.BedAssignmentHistory;

public class BedBuilder {

	public static Bed buildBedFromDto(BedRequestDTO bedRequestDTO) {
		
		return Bed.builder()
				  .bedNumber(bedRequestDTO.getBedNumber())
				  .isOccupied(bedRequestDTO.isOccupied())
				  .patientId(0L)
				  .bedAssignmentHistoryList(buildBedAssignmentHistory(bedRequestDTO.getBedAssignmentHistoryDTOs()))
				  .build();
	}
	
	private static List<BedAssignmentHistory> buildBedAssignmentHistory(List<BedAssignmentHistoryDTO>
	list){
		
		List<BedAssignmentHistory> historylist =new ArrayList<>();
		
		for(BedAssignmentHistoryDTO dto:list) {
			BedAssignmentHistory history =new BedAssignmentHistory();
			BeanUtils.copyProperties(dto, history);
			historylist.add(history);
		}
		return historylist;
	}
}
