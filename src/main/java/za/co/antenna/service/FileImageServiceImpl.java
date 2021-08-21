package za.co.antenna.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.antenna.dtos.FileImageDto;
import za.co.antenna.dtos.FileImageRecordPojo;
import za.co.antenna.dtos.FileImageRetrieveSingleResponse;
import za.co.antenna.dtos.FileImageRetrieveMultipleResponse;
import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImagePersistResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.repositories.FileImageRepository;

@Service
public class FileImageServiceImpl implements FileImageService {
	
	@Autowired
	FileImageRepository fileImageRepository;

	@Override
	public FileImageRetrieveSingleResponse getFileImageByAntennaCode(String antennaCode) throws ServiceFailedException {
		FileImageRetrieveSingleResponse response = new FileImageRetrieveSingleResponse();;
		FileImageDto fileImageDto = null;
		
		List<FileImageRecordPojo> fileImageRecords = fileImageRepository.getFileImageRecordByAntennaCode(antennaCode);

		if (fileImageRecords != null && !fileImageRecords.isEmpty()) {
			if (fileImageRecords.size() == 1) {
				fileImageDto = new FileImageDto();

				for (FileImageRecordPojo fileImageRecordPojo : fileImageRecords) {
					fileImageDto.setFileId(fileImageRecordPojo.getFileId());
					fileImageDto.setAntennaCode(fileImageRecordPojo.getAntennaCode());
					fileImageDto.setAntennaTypeCode(fileImageRecordPojo.getAntennaTypeCode());
					fileImageDto.setDateUploaded(fileImageRecordPojo.getDateUploaded());
					fileImageDto.setFileName(fileImageRecordPojo.getFileName());
					fileImageDto.setMeasurementCode(fileImageRecordPojo.getMeasurementCode());
					fileImageDto.setNumberOfRecords(fileImageRecordPojo.getNumberOfRecords());
					fileImageDto.setStatus(fileImageRecordPojo.getStatus());
					break;
				}

				response.setFileImageDto(fileImageDto);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											fileImageRecords.size() +
											" antenna record retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}

	@Override
	public FileImageRetrieveMultipleResponse getAllFileImages(String orderBy) throws ServiceFailedException {
		FileImageRetrieveMultipleResponse response = new FileImageRetrieveMultipleResponse();
		
		FileImageDto fileImageDto = null;
		List<FileImageDto> fileImageDtos = null;
		
		List<FileImageRecordPojo> fileImageRecords = fileImageRepository.getAllFileImageRecords(orderBy);

		if (fileImageRecords != null && !fileImageRecords.isEmpty()) {
			if (fileImageRecords.size() >= 1) {
				fileImageDtos = new ArrayList<>();

				for (FileImageRecordPojo fileImageRecordPojo : fileImageRecords) {					
					fileImageDto = new FileImageDto();
					fileImageDto.setFileId(fileImageRecordPojo.getFileId());
					fileImageDto.setAntennaCode(fileImageRecordPojo.getAntennaCode());
					fileImageDto.setAntennaTypeCode(fileImageRecordPojo.getAntennaTypeCode());
					fileImageDto.setDateUploaded(fileImageRecordPojo.getDateUploaded());
					fileImageDto.setFileName(fileImageRecordPojo.getFileName());
					fileImageDto.setMeasurementCode(fileImageRecordPojo.getMeasurementCode());
					fileImageDto.setNumberOfRecords(fileImageRecordPojo.getNumberOfRecords());
					fileImageDto.setStatus(fileImageRecordPojo.getStatus());
					fileImageDtos.add(fileImageDto);
				}

				response.setFileImageDtos(fileImageDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											fileImageRecords.size() +
											" file image record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}

	@Override
	public FileImageRetrieveMultipleResponse getAllFileImagesByType(String antennaType, String orderBy) throws ServiceFailedException {
		FileImageRetrieveMultipleResponse response = new FileImageRetrieveMultipleResponse();
		
		FileImageDto fileImageDto = null;
		List<FileImageDto> fileImageDtos = null;
		
		List<FileImageRecordPojo> fileImageRecords = fileImageRepository.getAllFileImageRecordsByType(antennaType, orderBy);

		if (fileImageRecords != null && !fileImageRecords.isEmpty()) {
			if (fileImageRecords.size() >= 1) {
				fileImageDtos = new ArrayList<>();

				for (FileImageRecordPojo fileImageRecordPojo : fileImageRecords) {
					fileImageDto = new FileImageDto();
					fileImageDto.setFileId(fileImageRecordPojo.getFileId());
					fileImageDto.setAntennaCode(fileImageRecordPojo.getAntennaCode());
					fileImageDto.setAntennaTypeCode(fileImageRecordPojo.getAntennaTypeCode());
					fileImageDto.setDateUploaded(fileImageRecordPojo.getDateUploaded());
					fileImageDto.setFileName(fileImageRecordPojo.getFileName());
					fileImageDto.setMeasurementCode(fileImageRecordPojo.getMeasurementCode());
					fileImageDto.setNumberOfRecords(fileImageRecordPojo.getNumberOfRecords());
					fileImageDto.setStatus(fileImageRecordPojo.getStatus());
					fileImageDtos.add(fileImageDto);
				}

				response.setFileImageDtos(fileImageDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											fileImageRecords.size() +
											" file image record(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}

	@Override
	public FileImageRetrieveMultipleResponse getAllFileImagesByStatus(String status, String orderBy) throws ServiceFailedException {
		FileImageRetrieveMultipleResponse response = new FileImageRetrieveMultipleResponse();
		
		FileImageDto fileImageDto = null;
		List<FileImageDto> fileImageDtos = null;
		
		List<FileImageRecordPojo> fileImageRecords = fileImageRepository.getAllFileImageRecordsByStatus(status, orderBy);

		if (fileImageRecords != null && !fileImageRecords.isEmpty()) {
			if (fileImageRecords.size() >= 1) {
				fileImageDtos = new ArrayList<>();

				for (FileImageRecordPojo fileImageRecordPojo : fileImageRecords) {
					fileImageDto = new FileImageDto();
					fileImageDto.setFileId(fileImageRecordPojo.getFileId());
					fileImageDto.setAntennaCode(fileImageRecordPojo.getAntennaCode());
					fileImageDto.setAntennaTypeCode(fileImageRecordPojo.getAntennaTypeCode());
					fileImageDto.setDateUploaded(fileImageRecordPojo.getDateUploaded());
					fileImageDto.setFileName(fileImageRecordPojo.getFileName());
					fileImageDto.setMeasurementCode(fileImageRecordPojo.getMeasurementCode());
					fileImageDto.setNumberOfRecords(fileImageRecordPojo.getNumberOfRecords());
					fileImageDto.setStatus(fileImageRecordPojo.getStatus());
					fileImageDtos.add(fileImageDto);
				}

				response.setFileImageDtos(fileImageDtos);
				String responseCode = ErrorCodes.SUCCESS.getCode();
				String responseMessage = ErrorCodes.SUCCESS.getMessage();
				response.setResponseCode(responseCode);
				response.setResponseMessage(responseMessage +
											fileImageRecords.size() +
											" file image records(s) retrieved.");
			}
			else {
				String responseMessage = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getMessage();
				String responseCode = ErrorCodes.FILE_IMAGE_FROM_DATABASE_FAILURE.getCode();
				response.setResponseMessage(responseMessage);
				response.setResponseCode(responseCode);				
			}
		}
		else {
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);			
		}

		return response;
	}	

	@Override
	public FileImagePersistResponse deleteFileImageByAntennaCode(String antennaCode) throws ServiceFailedException {
		FileImagePersistResponse response = new FileImagePersistResponse();
		
		int numberOfRecordsDeleted = fileImageRepository.deleteFileImageRecordByAntennaCode(antennaCode);

		String responseCode = ErrorCodes.SUCCESS.getCode();
		String responseMessage = ErrorCodes.SUCCESS.getMessage();
		response.setResponseCode(responseCode);
		response.setResponseMessage(responseMessage +
									" " +
									numberOfRecordsDeleted +
									"' file image record(s) deleted.");

		return response;
	}

	@Override
	public FileImagePersistResponse insertFileImage(FileImagePersistRequest request) throws ServiceFailedException {
		FileImagePersistResponse response = new FileImagePersistResponse();
		int numberOfRecordsInserted = 0;

		numberOfRecordsInserted = fileImageRepository.insertFileImageRecord(request);

		if (numberOfRecordsInserted == 1) {
			String responseCode = ErrorCodes.SUCCESS.getCode();
			String responseMessage = ErrorCodes.SUCCESS.getMessage();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage +
										" " +
										numberOfRecordsInserted +
										"' file image record successfully created.");
		}
		else {
			String responseMessage = ErrorCodes.FILE_IMAGE_INSERTION_FAILURE.getMessage();
			String responseCode = ErrorCodes.FILE_IMAGE_INSERTION_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);
		}

		return response;
	}
}
