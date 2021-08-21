package za.co.antenna.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Development status: Locked by John - 05/01/2021
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.antenna.dtos.AntennaDto;
import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaPersistResponse;
import za.co.antenna.dtos.AntennaRecordPojo;
import za.co.antenna.dtos.AntennaRetrieveMultipleResponse;
import za.co.antenna.dtos.AntennaRetrieveSingleResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.repositories.AntennaRepository;

@Service
public class AntennaServiceImpl implements AntennaService {
	
	@Autowired
	AntennaRepository antennaRepository;

	@Override
	public AntennaRetrieveSingleResponse getAntennaByAntennaCode(String antennaCode) throws ServiceFailedException {
		AntennaRetrieveSingleResponse response = new AntennaRetrieveSingleResponse();;
		AntennaDto antennaDto = null;
		
		List<AntennaRecordPojo> antennaRecords = antennaRepository.getAntennaRecord(antennaCode);

		if (antennaRecords != null && !antennaRecords.isEmpty()) {
			if (antennaRecords.size() == 1) {
				

				for (AntennaRecordPojo antennaRecordPojo : antennaRecords) {
					antennaDto = new AntennaDto();
					antennaDto.setAntennaId(antennaRecordPojo.getAntennaId());
					antennaDto.setAntennaCode(antennaRecordPojo.getAntennaCode());
					antennaDto.setAntennaTypeCode(antennaRecordPojo.getAntennaTypeCode());
					antennaDto.setDateCreated(antennaRecordPojo.getDateCreated());
					antennaDto.setDescription(antennaRecordPojo.getAntennaDesription());
					antennaDto.setMainAntennaCode(antennaRecordPojo.getMainAntennaCode());
					antennaDto.setName(antennaRecordPojo.getAntennaName());
					break;
				}

				response.setAntennaDto(antennaDto);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											antennaRecords.size() +
											" antenna record retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.ANTENNA_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.ANTENNA_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}

	@Override
	public AntennaRetrieveMultipleResponse getAllAntennas(String orderBy) throws ServiceFailedException {
		AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
		
		AntennaDto antennaDto = null;
		List<AntennaDto> antennaDtos = null;
		
		List<AntennaRecordPojo> antennaRecords = antennaRepository.getAllAntennaRecords(orderBy);

		if (antennaRecords != null && !antennaRecords.isEmpty()) {
			if (antennaRecords.size() >= 1) {
				antennaDtos = new ArrayList<>();

				for (AntennaRecordPojo antennaRecordPojo : antennaRecords) {
					antennaDto = new AntennaDto();
					antennaDto.setAntennaCode(antennaRecordPojo.getAntennaCode());
					antennaDto.setAntennaId(antennaRecordPojo.getAntennaId());
					antennaDto.setAntennaTypeCode(antennaRecordPojo.getAntennaTypeCode());
					antennaDto.setDateCreated(antennaRecordPojo.getDateCreated());
					antennaDto.setDescription(antennaRecordPojo.getAntennaDesription());
					antennaDto.setMainAntennaCode(antennaRecordPojo.getMainAntennaCode());
					antennaDto.setName(antennaRecordPojo.getAntennaName());
					antennaDtos.add(antennaDto);
				}

				response.setAntennaDtos(antennaDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											antennaRecords.size() +
											" antenna record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.ANTENNA_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.ANTENNA_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}	

	@Override
	public AntennaRetrieveMultipleResponse getAllAntennasByType(String antennaType, String orderBy) throws ServiceFailedException {
		AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
		
		AntennaDto antennaDto = null;
		List<AntennaDto> antennaDtos = null;
		
		List<AntennaRecordPojo> antennaRecords = antennaRepository.getAllAntennaRecordsByType(antennaType, orderBy);

		if (antennaRecords != null && !antennaRecords.isEmpty()) {
			if (antennaRecords.size() >= 1) {
				antennaDtos = new ArrayList<>();

				for (AntennaRecordPojo antennaRecordPojo : antennaRecords) {
					antennaDto = new AntennaDto();
					antennaDto.setAntennaCode(antennaRecordPojo.getAntennaCode());
					antennaDto.setAntennaId(antennaRecordPojo.getAntennaId());
					antennaDto.setAntennaTypeCode(antennaRecordPojo.getAntennaTypeCode());
					antennaDto.setDateCreated(antennaRecordPojo.getDateCreated());
					antennaDto.setDescription(antennaRecordPojo.getAntennaDesription());
					antennaDto.setMainAntennaCode(antennaRecordPojo.getMainAntennaCode());
					antennaDto.setName(antennaRecordPojo.getAntennaName());
					antennaDtos.add(antennaDto);
				}

				response.setAntennaDtos(antennaDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											antennaRecords.size() +
											" antenna record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.ANTENNA_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.ANTENNA_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}

	public AntennaRetrieveMultipleResponse getAllBaseAntennasByAntennaTypeCode(String baseAntennaTypeCode, String orderBy) throws ServiceFailedException {
		AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
		
		AntennaDto antennaDto = null;
		List<AntennaDto> antennaDtos = null;
		
		List<AntennaRecordPojo> antennaRecords = antennaRepository.getAllBaseAntennaRecordsByAntennaCode(baseAntennaTypeCode, orderBy);

		if (antennaRecords != null && !antennaRecords.isEmpty()) {
			if (antennaRecords.size() >= 1) {
				antennaDto = new AntennaDto();
				antennaDtos = new ArrayList<>();

				for (AntennaRecordPojo antennaRecordPojo : antennaRecords) {
					antennaDto = new AntennaDto();
					antennaDto.setAntennaCode(antennaRecordPojo.getAntennaCode());
					antennaDto.setAntennaId(antennaRecordPojo.getAntennaId());
					antennaDto.setAntennaTypeCode(antennaRecordPojo.getAntennaTypeCode());
					antennaDto.setDateCreated(antennaRecordPojo.getDateCreated());
					antennaDto.setDescription(antennaRecordPojo.getAntennaDesription());
					antennaDto.setMainAntennaCode(antennaRecordPojo.getMainAntennaCode());
					antennaDto.setName(antennaRecordPojo.getAntennaName());
					antennaDtos.add(antennaDto);
				}

				response.setAntennaDtos(antennaDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											antennaRecords.size() +
											" antenna record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.ANTENNA_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.ANTENNA_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}
	

	public AntennaRetrieveMultipleResponse getAllSubAntennasByAntennaTypeCode(String baseAntennaTypeCode, String orderBy) throws ServiceFailedException {
		AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
		
		AntennaDto antennaDto = null;
		List<AntennaDto> antennaDtos = null;
		
		List<AntennaRecordPojo> antennaRecords = antennaRepository.getAllSubAntennaRecordsByAntennaCode(baseAntennaTypeCode, orderBy);

		if (antennaRecords != null && !antennaRecords.isEmpty()) {
			if (antennaRecords.size() >= 1) {
				antennaDtos = new ArrayList<>();

				for (AntennaRecordPojo antennaRecordPojo : antennaRecords) {
					antennaDto = new AntennaDto();
					antennaDto.setAntennaCode(antennaRecordPojo.getAntennaCode());
					antennaDto.setAntennaId(antennaRecordPojo.getAntennaId());
					antennaDto.setAntennaTypeCode(antennaRecordPojo.getAntennaTypeCode());
					antennaDto.setDateCreated(antennaRecordPojo.getDateCreated());
					antennaDto.setDescription(antennaRecordPojo.getAntennaDesription());
					antennaDto.setMainAntennaCode(antennaRecordPojo.getMainAntennaCode());
					antennaDto.setName(antennaRecordPojo.getAntennaName());
					antennaDtos.add(antennaDto);
				}

				response.setAntennaDtos(antennaDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											antennaRecords.size() +
											" antenna record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.ANTENNA_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.ANTENNA_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.ANTENNA_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}

	@Override
	public AntennaPersistResponse deleteAntenna(AntennaPersistRequest request) throws ServiceFailedException {
		AntennaPersistResponse response = new AntennaPersistResponse();;
		
		int numberOfRecordsDeleted = antennaRepository.deleteAntennaRecordByAntennaCode(request.getAntennaCode());

		if (numberOfRecordsDeleted == 1) {
			String responseCode = ErrorCodes.SUCCESS.getCode();
			String responseMessage = ErrorCodes.SUCCESS.getMessage();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage +
										" Antenna '" +
										request.getName() +
										"' with code '" +
										request.getAntennaCode() +
										"' successfully deleted.");
		}
		else {
			if (numberOfRecordsDeleted > 1) {
				String responseMessage = ErrorCodes.ANTENNA_DELETION_SUPERFLUOUS_RECORDS.getMessage();
				String responseCode = ErrorCodes.ANTENNA_DELETION_SUPERFLUOUS_RECORDS.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);
			}
			else {
				String responseMessage = ErrorCodes.ANTENNA_DELETION_FAILURE.getMessage();
				String responseCode = ErrorCodes.ANTENNA_DELETION_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);
			}
		}

		return response;
	}

	@Override
	public AntennaPersistResponse persistAntenna(AntennaPersistRequest request) throws ServiceFailedException {
		AntennaPersistResponse response = new AntennaPersistResponse();
		int numberOfRecordsDeleted = 0;

		if (request.getAction().equals("INSERT")) {
			numberOfRecordsDeleted = antennaRepository.insertAntennaRecord(request);
		}
		else {
			if (request.getAction().equals("UPDATE")) {
				numberOfRecordsDeleted = antennaRepository.updateAntennaRecord(request);
			}
			else {
				String responseMessage = ErrorCodes.INVALID_FIELD_ANTENNA_ACTION.getMessage();
				String responseCode = ErrorCodes.INVALID_FIELD_ANTENNA_ACTION.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);
				return response;
			}
		}

		if (numberOfRecordsDeleted == 1) {
			String successMessageByAction;

			if (request.getAction().equals("INSERT")) {
				successMessageByAction = "' successfully created.";
			}
			else {
				successMessageByAction = "' successfully updated.";
			}

			String responseCode = ErrorCodes.SUCCESS.getCode();
			String responseMessage = ErrorCodes.SUCCESS.getMessage();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage +
										" Antenna '" +
										request.getName() +
										"' with code '" +
										request.getAntennaCode() +
										successMessageByAction);
		}
		else {
			if (numberOfRecordsDeleted > 1) {
				String responseMessage = ErrorCodes.ANTENNA_DELETION_SUPERFLUOUS_RECORDS.getMessage();
				String responseCode = ErrorCodes.ANTENNA_DELETION_SUPERFLUOUS_RECORDS.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);
			}
			else {
				String responseMessage = ErrorCodes.ANTENNA_DELETION_FAILURE.getMessage();
				String responseCode = ErrorCodes.ANTENNA_DELETION_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);
			}
		}

		return response;
	}
}
