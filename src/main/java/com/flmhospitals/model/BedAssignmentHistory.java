package com.flmhospitals.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bed_assignment_history")
public class BedAssignmentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bedAssignmentHistoryId;
	
	@ManyToOne
	@JoinColumn(name = "bed_id", nullable = false)
	private Bed bed;
	
	@Column(name = "patient_id", nullable = false)
	private Long patientId;
	
	@Column(name = "assigned_at", nullable = false)
	private LocalDateTime assignedAt;
	
	@Column(name = "vacated_at")
	private LocalDateTime vacatedAt;

	public BedAssignmentHistory(Bed bed, Long patientId, LocalDateTime assignedAt, LocalDateTime vacatedAt) {
		super();
		this.bed = bed;
		this.patientId = patientId;
		this.assignedAt = assignedAt;
		this.vacatedAt = vacatedAt;
	}
	
	
}
