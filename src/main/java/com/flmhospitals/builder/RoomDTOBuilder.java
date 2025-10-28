package com.flmhospitals.builder;


import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import com.flmhospitals.dto.BedDetailsResponseDTO;
import com.flmhospitals.dto.RoomResponseDto;
import com.flmhospitals.model.Bed;
import com.flmhospitals.model.Room;

public class RoomDTOBuilder {
	
	public static RoomResponseDto buildRoomResponseDtofromRoom(Room room) {
		
		return RoomResponseDto
				.builder()
				.roomId(room.getRoomId())
				.roomNumber(room.getRoomNumber())
				.roomType(room.getRoomType())
				.roomCapacity(room.getRoomCapacity())
				.beds(buildBedDetailsResponseDtos(room.getBeds()))
				.build();

	}

	private static List<BedDetailsResponseDTO> buildBedDetailsResponseDtos(List<Bed> beds) {

		List<BedDetailsResponseDTO> bedDetailsResponseDto = new ArrayList<>();
		for (Bed bed : beds) {
			BedDetailsResponseDTO bedDto = new BedDetailsResponseDTO();
			BeanUtils.copyProperties(bed, bedDto);
			bedDetailsResponseDto.add(bedDto);
		}

		return bedDetailsResponseDto;
	}
}
