package za.co.antenna.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Development status: Locked by John - 05/01/2021
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.antenna.dtos.FileImageMeasurementDto;
import za.co.antenna.dtos.FileImageMeasurementPersistRequest;
import za.co.antenna.dtos.FileImageMeasurementPersistResponse;
import za.co.antenna.dtos.FileImageMeasurementRecordPojo;
import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.repositories.FileImageMeasurementRepository;

@Service
public class FileImageMeasurementServiceImpl implements FileImageMeasurementService {
	
	@Autowired
	FileImageMeasurementRepository fileImageMeasurementRepository;

	@Override
	public FileImageMeasurementRetrieveMultipleResponse getFileImageMeasurementsByAntennaCode(String antennaCode, String orderBy) throws ServiceFailedException {
		FileImageMeasurementRetrieveMultipleResponse response = new FileImageMeasurementRetrieveMultipleResponse();
		
		FileImageMeasurementDto fileImageMeasurementDto = null;
		List<FileImageMeasurementDto> fileImageMeasurementDtos = null;
		
		List<FileImageMeasurementRecordPojo> fileImageMeasurementRecords = fileImageMeasurementRepository.getFileImageMeasurementRecordsByAntennaCode(antennaCode, orderBy);

		if (fileImageMeasurementRecords != null && !fileImageMeasurementRecords.isEmpty()) {
			if (fileImageMeasurementRecords.size() >= 1) {
				fileImageMeasurementDtos = new ArrayList<>();

				for (FileImageMeasurementRecordPojo fileImageMeasurementRecordPojo : fileImageMeasurementRecords) {
					fileImageMeasurementDto = new FileImageMeasurementDto();
					fileImageMeasurementDto.setFileMeasurementId(fileImageMeasurementRecordPojo.getFileMeasurementId());
					fileImageMeasurementDto.setAntennaCode(fileImageMeasurementRecordPojo.getAntennaCode());
					fileImageMeasurementDto.setDeterminant(fileImageMeasurementRecordPojo.getDeterminant());
					fileImageMeasurementDto.setValue(fileImageMeasurementRecordPojo.getValue());
					fileImageMeasurementDtos.add(fileImageMeasurementDto);
				}

				response.setFileImageMeasurementDtos(fileImageMeasurementDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											fileImageMeasurementRecords.size() +
											" file image measurement record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.MEASUREMENT_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.MEASUREMENT_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}

	@Override
	public FileImageMeasurementRetrieveMultipleResponse getAllFileImageMeasurements(String orderBy) throws ServiceFailedException {
		FileImageMeasurementRetrieveMultipleResponse response = new FileImageMeasurementRetrieveMultipleResponse();
		
		FileImageMeasurementDto fileImageMeasurementDto = null;
		List<FileImageMeasurementDto> fileImageMeasurementDtos = null;
		
		List<FileImageMeasurementRecordPojo> fileImageMeasurementRecords = fileImageMeasurementRepository.getAllFileImageMeasurementRecords(orderBy);

		if (fileImageMeasurementRecords != null && !fileImageMeasurementRecords.isEmpty()) {
			if (fileImageMeasurementRecords.size() >= 1) {
				fileImageMeasurementDtos = new ArrayList<>();

				for (FileImageMeasurementRecordPojo fileImageMeasurementRecordPojo : fileImageMeasurementRecords) {
					fileImageMeasurementDto = new FileImageMeasurementDto();
					fileImageMeasurementDto.setFileMeasurementId(fileImageMeasurementRecordPojo.getFileMeasurementId());
					fileImageMeasurementDto.setAntennaCode(fileImageMeasurementRecordPojo.getAntennaCode());
					fileImageMeasurementDto.setDeterminant(fileImageMeasurementRecordPojo.getDeterminant());
					fileImageMeasurementDto.setValue(fileImageMeasurementRecordPojo.getValue());
					fileImageMeasurementDtos.add(fileImageMeasurementDto);
				}

				response.setFileImageMeasurementDtos(fileImageMeasurementDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											fileImageMeasurementRecords.size() +
											" file image measurement record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.MEASUREMENT_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.MEASUREMENT_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}	

	@Override
	public FileImageMeasurementPersistResponse deleteFileImageMeasurementsByAntennaCode(String antennaCode) throws ServiceFailedException {
		FileImageMeasurementPersistResponse response = new FileImageMeasurementPersistResponse();
		
		int numberOfRecordsDeleted = fileImageMeasurementRepository.deleteFileImageMeasurementRecordsByAntennaCode(antennaCode);

		String responseCode = ErrorCodes.SUCCESS.getCode();
		String responseMessage = ErrorCodes.SUCCESS.getMessage();
		response.setResponseCode(responseCode);
		response.setResponseMessage(responseMessage +
									" " +
									numberOfRecordsDeleted +
									"' file image measurement records deleted.");

		return response;
	}

	@Override
	public FileImageMeasurementPersistResponse insertFileImageMeasurementRecord(FileImageMeasurementPersistRequest request) throws ServiceFailedException {
		FileImageMeasurementPersistResponse response = new FileImageMeasurementPersistResponse();
		int numberOfRecordsInserted = 0;

		numberOfRecordsInserted = fileImageMeasurementRepository.insertFileImageMeasurementRecord(request);

		if (numberOfRecordsInserted == 1) {
			String responseCode = ErrorCodes.SUCCESS.getCode();
			String responseMessage = ErrorCodes.SUCCESS.getMessage();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage +
										" " +
										numberOfRecordsInserted +
										"' file image measurement record successfully created.");
		}
		else {
			String responseMessage = ErrorCodes.MEASUREMENT_INSERTION_FAILURE.getMessage();
			String responseCode = ErrorCodes.MEASUREMENT_INSERTION_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);
		}

		return response;
	}
	
	@Override
	public FileImageMeasurementPersistResponse publishFileImageMeasurements(String antennaCode) throws ServiceFailedException {
		FileImageMeasurementPersistResponse response = new FileImageMeasurementPersistResponse();
		int numberOfRecordsPublished = 0;
		
		fileImageMeasurementRepository.deleteDataRecordsByAntennaCode(antennaCode);
		numberOfRecordsPublished = fileImageMeasurementRepository.publishFileImageMeasurementRecords(antennaCode);
		fileImageMeasurementRepository.setFileImageRecordStatusByAntennaCode(antennaCode, "PUBLISHED");
		
		if (numberOfRecordsPublished >= 1) {
			String responseCode = ErrorCodes.SUCCESS.getCode();
			String responseMessage = ErrorCodes.SUCCESS.getMessage();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage +
										" " +
										numberOfRecordsPublished +
										" measurement records successfully published.");
		}
		else {
			String responseMessage = ErrorCodes.PUBLISH_FROM_DATABASE_FAILURE.getMessage();
			String responseCode = ErrorCodes.PUBLISH_FROM_DATABASE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);
		}

		return response;
	}
}
