package com.onerivet.deskbook.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.models.payload.RequestHistoryDto;
import com.onerivet.deskbook.models.payload.SeatRequestDto;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatRequestRepo;
import com.onerivet.deskbook.services.RequestHistoryService;
import jakarta.transaction.Transactional;


@Transactional
@Service
public class RequestHistoryImpl  implements RequestHistoryService{
	
	
	@Autowired
	private SeatRequestRepo seatRequestRepo;
	

	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<RequestHistoryDto> getRequestHistory(String employeeId,Pageable pageble, int requestStatus) {
		
		
		
	
		SeatConfiguration seatConfiguration = seatConfigurationRepo.findByEmployeeAndDeletedByNull(new Employee(employeeId));
		
		if(requestStatus == 0) {// call for all
			List<SeatRequest> sr = this.seatRequestRepo.findSeatRequestBySeatId(new SeatNumber(seatConfiguration.getSeatNumber().getId()),pageble);
			
			List<RequestHistoryDto> list = new ArrayList<>();
			for(int i=0; i< sr.size(); i++ ) {
				
				RequestHistoryDto requestHistoryDto = new RequestHistoryDto();
				requestHistoryDto.setName(sr.get(i).getEmployee().getFirstName() + "" + sr.get(i).getEmployee().getLastName());
				requestHistoryDto.setDeskNo(sr.get(i).getSeatId().getColumn().getColumnName() + "" + sr.get(i).getSeatId().getSeatNumber());
				requestHistoryDto.setEmailId(sr.get(i).getEmployee().getEmailId());
				requestHistoryDto.setRequestFor(sr.get(i).getBookingDate());
				requestHistoryDto.setRequestDate(sr.get(i).getCreatedDate());
				requestHistoryDto.setFloorNo(sr.get(i).getSeatId().getColumn().getFloor().getId());
				requestHistoryDto.setStatus(sr.get(i).getRequestStatus());
				
				list.add(requestHistoryDto);
			}
			return list;
		}
		
		List<SeatRequest> sr = this.seatRequestRepo.findSeatRequestBySeatIdAndRequestStatus(new SeatNumber(seatConfiguration.getSeatNumber().getId()),pageble, requestStatus);
		
		List<RequestHistoryDto> list = new ArrayList<>();
		for(int i=0; i< sr.size(); i++ ) {
			
			RequestHistoryDto requestHistoryDto = new RequestHistoryDto();
			requestHistoryDto.setName(sr.get(i).getEmployee().getFirstName() + "" + sr.get(i).getEmployee().getLastName());
			requestHistoryDto.setDeskNo(sr.get(i).getSeatId().getColumn().getColumnName() + "" + sr.get(i).getSeatId().getSeatNumber());
			requestHistoryDto.setEmailId(sr.get(i).getEmployee().getEmailId());
			requestHistoryDto.setRequestFor(sr.get(i).getBookingDate());
			requestHistoryDto.setRequestDate(sr.get(i).getCreatedDate());
			requestHistoryDto.setFloorNo(sr.get(i).getSeatId().getColumn().getFloor().getId());
			requestHistoryDto.setStatus(sr.get(i).getRequestStatus());
			
			list.add(requestHistoryDto);
		}
		return list;
	}

	@Override
	public List<RequestHistoryDto> searchByFirstNameOrLastName(String name, Pageable pageble) {
		
		List<SeatRequest> sr = this.seatRequestRepo.getByFirstNameOrLastName(name,pageble);
		
		List<RequestHistoryDto> list = new ArrayList<>();
		for(int i=0; i< sr.size(); i++ ) {
			
			RequestHistoryDto requestHistoryDto = new RequestHistoryDto();
			requestHistoryDto.setName(sr.get(i).getEmployee().getFirstName() + "" + sr.get(i).getEmployee().getLastName());
			requestHistoryDto.setDeskNo(sr.get(i).getSeatId().getColumn().getColumnName() + "" + sr.get(i).getSeatId().getSeatNumber());
			requestHistoryDto.setEmailId(sr.get(i).getEmployee().getEmailId());
			requestHistoryDto.setRequestFor(sr.get(i).getBookingDate());
			requestHistoryDto.setRequestDate(sr.get(i).getCreatedDate());
			requestHistoryDto.setFloorNo(sr.get(i).getSeatId().getColumn().getFloor().getId());
			requestHistoryDto.setStatus(sr.get(i).getRequestStatus());
			
			list.add(requestHistoryDto);
    }
		return list;
	
}
}
