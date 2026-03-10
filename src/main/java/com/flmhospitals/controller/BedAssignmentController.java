package com.flmhospitals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flmhospitals.dto.BedAssignmentHistoryDTO;
import com.flmhospitals.dto.BedDetailsResponseDTO;
import com.flmhospitals.model.Bed;
import com.flmhospitals.service.BedAssignmentService;

@RestController
@RequestMapping("/bed")
public class BedAssignmentController {

	private final BedAssignmentService bedAssignmentService;

	public BedAssignmentController(BedAssignmentService bedAssignmentService) {
		this.bedAssignmentService = bedAssignmentService;
	}

	@PostMapping("/assign/{bedNumber}/{patientId}")
	public ResponseEntity<BedDetailsResponseDTO> assignBed(@PathVariable(name = "bedNumber") long bedNumber,
			@PathVariable(name = "patientId") String patientId) {

		Bed assignedBed = bedAssignmentService.bedAssigntment(bedNumber, patientId);
		BedDetailsResponseDTO response = BedDetailsResponseDTO.builder()
				.bedNumber(assignedBed.getBedNumber())
				.roomNumber(assignedBed.getRoom().getRoomNumber())
				.isOccupied(assignedBed.isOccupied())
				.build();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/vacate-bed/{roomNumber}/{bedNumber}")
	public ResponseEntity<BedDetailsResponseDTO> vacateBed(
	        @PathVariable long roomNumber,
	        @PathVariable long bedNumber) {
		
		Bed vacatedBed = bedAssignmentService.vacateBed(roomNumber, bedNumber);
		BedDetailsResponseDTO response = BedDetailsResponseDTO.builder()
				.bedNumber(vacatedBed.getBedNumber())
				.roomNumber(vacatedBed.getRoom().getRoomNumber())
				.isOccupied(vacatedBed.isOccupied())
				.build();
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/bed-history/{bedNumber}")
    public ResponseEntity<List<BedAssignmentHistoryDTO>> 
        getHistoryByBed(@PathVariable long bedNumber) {
		List<BedAssignmentHistoryDTO> historyByBedNumber = bedAssignmentService.getHistoryByBedNumber(bedNumber);
        
		return ResponseEntity.ok(historyByBedNumber);
    }
}
