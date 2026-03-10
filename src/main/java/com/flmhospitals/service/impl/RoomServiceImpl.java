package com.flmhospitals.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flmhospitals.builder.BedBuilder;
import com.flmhospitals.builder.RoomBuilder;
import com.flmhospitals.builder.RoomDTOBuilder;
import com.flmhospitals.controller.RoomController;
import com.flmhospitals.dao.RoomRepository;
import com.flmhospitals.dto.BedRequestDTO;
import com.flmhospitals.dto.RoomRequestDto;
import com.flmhospitals.dto.RoomResponseDto;
import com.flmhospitals.exception.RoomNotFoundException;
import com.flmhospitals.model.Bed;
import com.flmhospitals.model.Room;
import com.flmhospitals.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {


	private final RoomRepository roomRepository;
	

	public RoomServiceImpl(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}
	


	@Override
	public RoomResponseDto addRoom(RoomRequestDto roomRequestDto) {
		
		System.out.println("RoomRequestDto "+roomRequestDto);

	    Room room = RoomBuilder.buildRoomFromRoomDTO(roomRequestDto);

	    if (roomRequestDto.getBeds() != null) {
	        List<Bed> bedEntities = new ArrayList<>();
	        for (BedRequestDTO bedDto : roomRequestDto.getBeds()) {
	           
	            Bed bedEntity = BedBuilder.buildBedFromBedRequestDto(bedDto);
	            bedEntities.add(bedEntity);
	            bedEntity.setRoom(room);
	            
	        }
	        room.setBeds(bedEntities);
	    }
	   System.out.println("Room "+room.getBeds());
	    Room savedRoom = roomRepository.save(room);
	  	    
	    return RoomDTOBuilder.buildRoomResponseDtofromRoom(savedRoom);
	}

	


	@Override
	public RoomResponseDto UpdateRoomDetails(long roomNumber,  RoomRequestDto roomRequestDto ) {
		Room existingRoom = roomRepository.findById(roomNumber)
											.orElseThrow(()-> new RoomNotFoundException("Room Not Found with Room Number :"+roomNumber));
	
		// Check if reducing capacity below current bed count
		int currentBedCount = existingRoom.getBeds() != null ? existingRoom.getBeds().size() : 0;
		if (roomRequestDto.getRoomCapacity() < currentBedCount) {
			throw new IllegalArgumentException("Cannot reduce room capacity to " + roomRequestDto.getRoomCapacity() + 
					" as room currently has " + currentBedCount + " beds");
		}

		existingRoom.setRoomType(roomRequestDto.getRoomType());
		existingRoom.setRoomCapacity(roomRequestDto.getRoomCapacity());

//		// Clear old beds
//		existingRoom.getBeds().clear();
//
//		for (BedRequestDTO dto : roomRequestDto.getBeds()) {
//		    Bed bed = new Bed();
//		    bed.setBedNumber(dto.getBedNumber());
//		    bed.setOccupied(dto.isOccupied());
//		    bed.setRoom(existingRoom);
//		    existingRoom.getBeds().add(bed);
//		}

		Room room = roomRepository.save(existingRoom);
		return RoomDTOBuilder.buildRoomResponseDtofromRoom(room);
		
	}
	
	@Override
	public boolean removeRoom(long roomNumber) {
		Room room = roomRepository.findById(roomNumber)
				.orElseThrow(() -> new RoomNotFoundException("Room not found with " + roomNumber));
		roomRepository.delete(room);
		return true;
	}
	@Override
	public List<RoomResponseDto> getAllRoomDetails() {
		 List<Room> rooms = roomRepository.findAll();
		 List<RoomResponseDto> roomResponse=new ArrayList<>();
		 for(Room room:rooms) {
			 RoomResponseDto roomResponseDtofromRoom = RoomDTOBuilder.buildRoomResponseDtofromRoom(room);
			 roomResponse.add(roomResponseDtofromRoom);
		 }
		return roomResponse;
	}


}
