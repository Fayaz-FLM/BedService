package com.flmhospitals.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flmhospitals.builder.BedBuilder;
import com.flmhospitals.builder.BedResponseDtoBuilder;
import com.flmhospitals.dao.BedRepository;
import com.flmhospitals.dao.RoomRepository;
import com.flmhospitals.dto.BedDetailsResponseDTO;
import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.dto.RoomResponseDto;
import com.flmhospitals.exception.BedNotFoundException;
import com.flmhospitals.exception.BedUnavailableException;
import com.flmhospitals.exception.RoomNotFoundException;
import com.flmhospitals.model.Bed;
import com.flmhospitals.model.Room;
import com.flmhospitals.service.BedService;

@Service
public class BedServiceImpl implements BedService {

	private final BedRepository bedRepository;
	private final RoomRepository roomRepository;

	public BedServiceImpl(BedRepository bedRepository, RoomRepository roomRepository) {

		this.bedRepository = bedRepository;
		this.roomRepository = roomRepository;
	}

	@Override
	public ResponseEntity<String> addBedInRoom(BedRequestDTO bedRequestDTO) {

		Room existingRoom = roomRepository.findByRoomNumber(bedRequestDTO.getRoomNumber());
		if (existingRoom == null) {
			throw new RoomNotFoundException("Room Number " + bedRequestDTO.getRoomNumber() + " doesn't exist");
		}
		
		List<Bed> bedInExistingRoom = existingRoom.getBeds();
		for (Bed bed : bedInExistingRoom) {
			if (bedRequestDTO.getBedNumber() == bed.getBedNumber()) {
				throw new IllegalArgumentException("Bed Number " + bedRequestDTO.getBedNumber() + " already exists in Room Number " + bedRequestDTO.getRoomNumber());
			}
		}
		
		if (bedInExistingRoom.isEmpty() || (bedInExistingRoom.size() < existingRoom.getRoomCapacity())) {
			Bed bed = BedBuilder.buildBedFromBedRequestDto(bedRequestDTO);
			bed.setRoom(existingRoom);
			BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(bedRepository.save(bed));
			return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added Bed Number " + bedRequestDTO.getBedNumber() + " into Room Number " + bedRequestDTO.getRoomNumber());
		}

		throw new IllegalStateException("Cannot add Bed into Room Number " + bedRequestDTO.getRoomNumber() + " as the room is full");
	}

	@Override
	public ResponseEntity<String> removeBed(long bedNumber, long roomNumber) {
		Bed bed = bedRepository.findBedWithRoomNumber(bedNumber, roomNumber).orElseThrow(()-> new BedNotFoundException("Bed Not Found with BedNumber :"+bedNumber+" in RoomNumber: "+roomNumber));

		bedRepository.delete(bed);
		return ResponseEntity.ok("Bed removed from the room");
	}


	@Override
	public ResponseEntity<BedDetailsResponseDTO> updateBedDetails(long roomNumber, long bedNumber,
			BedRequestDTO bedRequestDTO) {
		Room existingRoom = roomRepository.findById(roomNumber).orElseThrow(()->new RoomNotFoundException("Room not Found with Id:"+roomNumber));
		Bed existingBed = bedRepository.findBedWithRoomNumber(bedNumber, existingRoom.getRoomNumber())
				 										.orElseThrow(()-> new BedNotFoundException("Bed Not Found with BedNumber :"+bedNumber+" in RoomNumber: "+roomNumber));
		existingBed.setOccupied(bedRequestDTO.isOccupied());
		existingBed.setRoom(existingRoom);
		Bed savedBed = bedRepository.save(existingBed);
		BedDetailsResponseDTO response = BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(savedBed);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public List<BedDetailsResponseDTO> getBedsByRoomId(long roomNumber) {

		Room room = roomRepository.findById(roomNumber)
	            .orElseThrow(() -> 
	                new RoomNotFoundException("Room not found with room number " + roomNumber));

	    List<Bed> beds = bedRepository.findByRoomRoomNumber(roomNumber);

	    if (beds.isEmpty()) {
	        throw new BedNotFoundException("No beds found for room number " + roomNumber);
	    }

	    return beds.stream()
	            .map(BedResponseDtoBuilder::buildBedDetailsResponseDtoFromBed)
	            .toList();
	}
	
	@Override
	public List<BedDetailsResponseDTO> getVacantBedsByRoomNumber(long roomNumber) {

		Room room = roomRepository.findById(roomNumber)
	            .orElseThrow(() ->
	                new RoomNotFoundException("Room not found with room number " + roomNumber));

	    List<Bed> vacantBeds = bedRepository.findByRoomRoomNumberAndIsOccupiedFalse(roomNumber);

	    if (vacantBeds.isEmpty()) {
	        throw new BedUnavailableException("No vacant beds available in room number " + roomNumber);
	    }

	    return vacantBeds.stream()
	            .map(BedResponseDtoBuilder::buildBedDetailsResponseDtoFromBed)
	            .toList();
	}

	@Override
	public List<BedDetailsResponseDTO> getAllBedDetails() {
		List<Bed> allBeds = bedRepository.findAll();
		List<BedDetailsResponseDTO> allBedsResponse = allBeds.stream().map(bed ->BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(bed)).toList();
		return allBedsResponse;
	}

}
