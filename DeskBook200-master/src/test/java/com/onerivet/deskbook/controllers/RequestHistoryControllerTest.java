package com.onerivet.deskbook.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.payload.RequestHistoryDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.RequestHistoryService;
import com.onerivet.deskbook.util.PaginationAndSorting;

public class RequestHistoryControllerTest {
	
	
	@Mock
    private RequestHistoryService requestHistoryService;

    @Mock
    private Principal principal;

    private RequestHistoryController requestHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requestHistoryController = new RequestHistoryController(requestHistoryService);
    }

    @Test
    void testGetReqestHistory_WithRequestStatus_ReturnsGenericResponse() {
        // Arrange
        List<RequestHistoryDto> requestHistoryList = new ArrayList<>();
        when(requestHistoryService.getRequestHistory(anyString(), any(Pageable.class), anyInt()))
                .thenReturn(requestHistoryList);

        // Act
        GenericResponse<List<RequestHistoryDto>> response = requestHistoryController.getReqestHistory(principal, new PaginationAndSorting(), 1);

        // Assert
        assertEquals(requestHistoryList, response.getData());
        assertEquals(null, response.getError());
    }

    @Test
    void testGetReqestHistory_WithoutRequestStatus_ReturnsGenericResponse() {
        // Arrange
        List<RequestHistoryDto> requestHistoryList = new ArrayList<>();
        when(requestHistoryService.getRequestHistory(anyString(), any(Pageable.class), isNull()))
                .thenReturn(requestHistoryList);

        // Act
        GenericResponse<List<RequestHistoryDto>> response = requestHistoryController.getReqestHistory(principal, new PaginationAndSorting(), null);

        // Assert
        assertEquals(requestHistoryList, response.getData());
        assertEquals(null, response.getError());
    }

    @Test
    void testGetAllbyFirstNameandLastName_ReturnsGenericResponse() {
        // Arrange
        List<RequestHistoryDto> requestHistoryList = new ArrayList<>();
        when(requestHistoryService.searchByFirstNameOrLastName(anyString(), any(Pageable.class)))
                .thenReturn(requestHistoryList);

        // Act
        GenericResponse<List<RequestHistoryDto>> response = requestHistoryController.getAllbyFirstNameandLastName("John Doe", new PaginationAndSorting());

        // Assert
        assertEquals(requestHistoryList, response.getData());
        assertEquals(null, response.getError());
    }

}
