package com.onerivet.deskbook.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.payload.RequestHistoryDto;

public interface RequestHistoryService {

	
	public List<RequestHistoryDto> getRequestHistory(String employeeId,Pageable pageble, int requestStatus);
	
	 public List<RequestHistoryDto> searchByFirstNameOrLastName(String name,Pageable pageble);


}