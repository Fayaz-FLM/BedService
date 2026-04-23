package com.flmhospitals.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flmhospitals.model.Bed;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long>{
	
	@Query(value = "SELECT b from Bed b where b.bedNumber=:bedNumber and b.room.roomNumber=:roomNumber")
	Optional<Bed> findBedWithRoomNumber(@Param("bedNumber")long bedNumber,@Param("roomNumber")long roomNumber);

	Bed findByBedNumberAndRoom_RoomNumber(long bedNumber, long roomNumber);
	
	List<Bed> findByRoomRoomNumber(long roomNumber);

	List<Bed> findByRoomRoomNumberAndIsOccupiedFalse(long roomNumber);
}
