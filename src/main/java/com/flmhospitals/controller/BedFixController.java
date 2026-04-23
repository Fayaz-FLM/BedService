package com.flmhospitals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flmhospitals.dao.BedRepository;
import com.flmhospitals.model.Bed;

@RestController
@RequestMapping("/bed/admin")
public class BedFixController {
	
	private final BedRepository bedRepository;

	public BedFixController(BedRepository bedRepository) {
		this.bedRepository = bedRepository;
	}

	/**
	 * Emergency fix endpoint to correct beds that were created with inverted isOccupied logic.
	 * This fixes beds that show as "occupied" but have no patient assigned.
	 */
	@PostMapping("/fix-inverted-beds")
	public ResponseEntity<String> fixInvertedBeds() {
		List<Bed> allBeds = bedRepository.findAll();
		int fixedCount = 0;
		
		for (Bed bed : allBeds) {
			// If bed is marked as occupied but has no patient (patientId = 0), it was created with inverted logic
			if (bed.isOccupied() && bed.getPatientId() == 0) {
				bed.setOccupied(false);
				bedRepository.save(bed);
				fixedCount++;
			}
		}
		
		return ResponseEntity.ok("Fixed " + fixedCount + " beds with inverted occupancy status");
	}
}
