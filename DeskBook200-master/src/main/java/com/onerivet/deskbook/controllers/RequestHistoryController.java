package com.onerivet.deskbook.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.RequestHistoryDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.RequestHistoryService;
import com.onerivet.deskbook.util.PaginationAndSorting;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class RequestHistoryController {
	
	@Autowired
	private RequestHistoryService requestHistoryService;
	
	@GetMapping("/request-history/{requestStatus}")
	public GenericResponse<List<RequestHistoryDto>>  getReqestHistory(Principal principal,  PaginationAndSorting pagination, @PathVariable int requestStatus){
		System.out.println(pagination.getSize());
		GenericResponse<List<RequestHistoryDto>> genericResponse = new GenericResponse<>(this.requestHistoryService.getRequestHistory(principal.getName(),pagination.createPageRequest(), requestStatus), null);
		return genericResponse;
		
		
	}
	
	@GetMapping("/search/{name}")
	 public GenericResponse<List<RequestHistoryDto>> getAllbyFirstNameandLastName(@PathVariable("name") String name,PaginationAndSorting sorting) {
		
	 GenericResponse<List<RequestHistoryDto>> genericResponse = new GenericResponse<>(this.requestHistoryService.searchByFirstNameOrLastName(name,sorting.createPageRequest()), null);
	 return genericResponse;
	
	}
}
