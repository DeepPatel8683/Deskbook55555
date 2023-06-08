package com.onerivet.deskbook.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.models.payload.RequestHistoryDto;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatRequestRepo;

public class RequestHistoryImplTest {
	
	
	 @Mock
	    private SeatRequestRepo seatRequestRepo;

	    @Mock
	    private SeatConfigurationRepo seatConfigurationRepo;

	    private RequestHistoryImpl requestHistoryService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        requestHistoryService = new RequestHistoryImpl(seatRequestRepo, seatConfigurationRepo);
	    }

	    @Test
	    void testGetRequestHistory_WithNullRequestStatus_ReturnsRequestHistoryDtoList() {
	        // Arrange
	        String employeeId = "123";
	        Pageable pageable = Pageable.unpaged();
	        Integer requestStatus = null;
	        SeatConfiguration seatConfiguration = new SeatConfiguration();
	        SeatNumber seatNumber = new SeatNumber();
	        seatConfiguration.setSeatNumber(seatNumber);
	        SeatRequest seatRequest = new SeatRequest();
	        Employee employee = new Employee();
	        seatRequest.setEmployee(employee);
	        seatRequest.setSeat(seatNumber);
	        List<SeatRequest> seatRequestList = new ArrayList<>();
	        seatRequestList.add(seatRequest);
	        when(seatConfigurationRepo.findByEmployeeAndDeletedByNull(any(Employee.class)))
	                .thenReturn(seatConfiguration);
	        when(seatRequestRepo.findSeatRequestBySeatId(any(SeatNumber.class), any(Pageable.class)))
	                .thenReturn(seatRequestList);

	        // Act
	        List<RequestHistoryDto> result = requestHistoryService.getRequestHistory(employeeId, pageable, requestStatus);

	        // Assert
	        assertEquals(seatRequestList.size(), result.size());
	    }

	    @Test
	    void testGetRequestHistory_WithValidRequestStatus_ReturnsRequestHistoryDtoList() {
	        // Arrange
	        String employeeId = "123";
	        Pageable pageable = Pageable.unpaged();
	        Integer requestStatus = 1;
	        SeatConfiguration seatConfiguration = new SeatConfiguration();
	        SeatNumber seatNumber = new SeatNumber();
	        seatConfiguration.setSeatNumber(seatNumber);
	        SeatRequest seatRequest = new SeatRequest();
	        Employee employee = new Employee();
	        seatRequest.setEmployee(employee);
	        seatRequest.setSeat(seatNumber);
	        seatRequest.setRequestStatus(requestStatus);
	        List<SeatRequest> seatRequestList = new ArrayList<>();
	        seatRequestList.add(seatRequest);
	        when(seatConfigurationRepo.findByEmployeeAndDeletedByNull(any(Employee.class)))
	                .thenReturn(seatConfiguration);
	        when(seatRequestRepo.findSeatRequestBySeatIdAndRequestStatus(any(SeatNumber.class), any(Pageable.class), anyInt()))
	                .thenReturn(seatRequestList);

	        // Act
	        List<RequestHistoryDto> result = requestHistoryService.getRequestHistory(employeeId, pageable, requestStatus);

	        // Assert
	        assertEquals(seatRequestList.size(), result.size());
	    }

	    @Test
	    void testGetRequestHistory_WithInvalidRequestStatus_ReturnsEmptyList() {
	        // Arrange
	        String employeeId = "123";
	        Pageable pageable = Pageable.unpaged();
	        Integer requestStatus = 2;
	        SeatConfiguration seatConfiguration = new SeatConfiguration();
	        SeatNumber seatNumber = new SeatNumber();
	        seatConfiguration.setSeatNumber(seatNumber);
	        when(seatConfigurationRepo.findByEmployeeAndDeletedByNull(any(Employee.class)))
	                .thenReturn(seatConfiguration);
	        when(seatRequestRepo.findSeatRequestBySeatIdAndRequestStatus(any(SeatNumber.class), any(Pageable.class), anyInt()))
	                .thenReturn(new ArrayList<>());

	        // Act
	        List<RequestHistoryDto> result = requestHistoryService.getRequestHistory(employeeId, pageable, requestStatus);

	        // Assert
	        assertTrue(result.isEmpty());
	    }
	    

}
