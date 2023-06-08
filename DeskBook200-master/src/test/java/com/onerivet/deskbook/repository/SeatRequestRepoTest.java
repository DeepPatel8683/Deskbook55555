package com.onerivet.deskbook.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;

public class SeatRequestRepoTest {
	
	 @Mock
	    private SeatRequestRepo seatRequestRepo;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testFindSeatRequestBySeatId_ReturnsSeatRequestList() {
	        // Arrange
	        SeatNumber seatId = new SeatNumber();
	        List<SeatRequest> seatRequestList = new ArrayList<>();
	        when(seatRequestRepo.findSeatRequestBySeatId(any(SeatNumber.class), any(Pageable.class)))
	                .thenReturn(seatRequestList);

	        // Act
	        List<SeatRequest> result = seatRequestRepo.findSeatRequestBySeatId(seatId, Pageable.unpaged());

	        // Assert
	        assertEquals(seatRequestList, result);
	    }

	    @Test
	    void testFindSeatRequestBySeatIdAndRequestStatus_ReturnsSeatRequestList() {
	        // Arrange
	        SeatNumber seatId = new SeatNumber();
	        List<SeatRequest> seatRequestList = new ArrayList<>();
	        when(seatRequestRepo.findSeatRequestBySeatIdAndRequestStatus(any(SeatNumber.class), any(Pageable.class), anyInt()))
	                .thenReturn(seatRequestList);

	        // Act
	        List<SeatRequest> result = seatRequestRepo.findSeatRequestBySeatIdAndRequestStatus(seatId, Pageable.unpaged(), 1);

	        // Assert
	        assertEquals(seatRequestList, result);
	    }

	    @Test
	    void testGetByFirstNameOrLastName_ReturnsSeatRequestList() {
	        // Arrange
	        List<SeatRequest> seatRequestList = new ArrayList<>();
	        when(seatRequestRepo.getByFirstNameOrLastName(anyString(), anyString(), any(Pageable.class)))
	                .thenReturn(seatRequestList);

	        // Act
	        List<SeatRequest> result = seatRequestRepo.getByFirstNameOrLastName("John", "Doe", Pageable.unpaged());

	        // Assert
	        assertEquals(seatRequestList, result);
	    }

}
