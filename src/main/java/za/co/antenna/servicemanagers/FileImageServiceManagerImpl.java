package za.co.antenna.servicemanagers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Development status: Locked by John - 05/01/2021
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.antenna.dtos.FileImagePersistResponse;
import za.co.antenna.dtos.FileImageRetrieveMultipleResponse;
import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImageRetrieveMultipleRequest;
import za.co.antenna.dtos.FileImageRetrieveSingleRequest;
import za.co.antenna.dtos.FileImageRetrieveSingleResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.service.FileImageService;
import za.co.antenna.validators.FileImageRequestValidator;

@Component
public class FileImageServiceManagerImpl implements FileImageServiceManager {
	private static final Logger log = LoggerFactory.getLogger(FileImageServiceManagerImpl.class);

	private static final String IS_REQUEST_VALID = "isRequestValid";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	@Autowired
	FileImageService fileImageService;

	public FileImageServiceManagerImpl() {
	}

	public FileImageServiceManagerImpl(FileImageService fileImageService) {
		super();
		this.fileImageService = fileImageService;
	}

	public FileImageRetrieveSingleResponse getFileImageByAntennaCode(FileImageRetrieveSingleRequest request) {
		Map<String, Object> validationResult = FileImageRequestValidator.validateAntennaRequest(request, "getFileImageByAntennaCode");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageRetrieveSingleResponse response = new FileImageRetrieveSingleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getFileImageByAntennaCode : Validation failed : response : " + response);
			return response;
		}

		String antennaCode = request.getAntennaCode();

		FileImageRetrieveSingleResponse response;

		try {
			response = fileImageService.getFileImageByAntennaCode(antennaCode);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			response = new FileImageRetrieveSingleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getFileImageByAntennaCode : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageServiceManager : getFileImageByAntennaCode : response " + response);
		return response;
	}

	public FileImageRetrieveMultipleResponse getAllFileImages(FileImageRetrieveMultipleRequest request) {
		Map<String, Object> validationResult = FileImageRequestValidator.validateAntennaRequest(request, "getAllFileImages");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageRetrieveMultipleResponse response = new FileImageRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getAllFileImages : Validation failed : response : " + response);
			return response;
		}

		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		FileImageRetrieveMultipleResponse response;

		try {
			response = fileImageService.getAllFileImages(orderBy);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			response = new FileImageRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getAllFileImageMeasurements : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageServiceManager : getAllFileImageMeasurements : response " + response);
		return response;
	}
	public FileImageRetrieveMultipleResponse getAllFileImagesByType(FileImageRetrieveMultipleRequest request) {
		Map<String, Object> validationResult = FileImageRequestValidator.validateAntennaRequest(request, "getAllFileImagesByType");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageRetrieveMultipleResponse response = new FileImageRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getAllFileImagesByType : Validation failed : response : " + response);
			return response;
		}

		String antennaType = request.getAntennaTypeCode();
		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		FileImageRetrieveMultipleResponse response;

		try {
			response = fileImageService.getAllFileImagesByType(antennaType, orderBy);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			response = new FileImageRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getAllFileImagesByType : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageServiceManager : getAllFileImagesByType : response " + response);
		return response;
	}
	public FileImageRetrieveMultipleResponse getAllFileImagesByStatus(FileImageRetrieveMultipleRequest request) {
		Map<String, Object> validationResult = FileImageRequestValidator.validateAntennaRequest(request, "getAllFileImageMeasurements");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageRetrieveMultipleResponse response = new FileImageRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getAllFileImageMeasurements : Validation failed : response : " + response);
			return response;
		}

		String status = request.getStatus();
		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		FileImageRetrieveMultipleResponse response;

		try {
			response = fileImageService.getAllFileImagesByStatus(status, orderBy);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.FILE_IMAGE_SERVICE_FAILURE.getMessage();
			response = new FileImageRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : getAllFileImageMeasurements : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageServiceManager : getAllFileImageMeasurements : response " + response);
		return response;
	}

	public FileImagePersistResponse deleteFileImageByAntennaCode(FileImagePersistRequest request) {
		Map<String, Object> validationResult = FileImageRequestValidator.validateAntennaRequest(request, "deleteFileImageMeasurementsByAntennaCode");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImagePersistResponse response = new FileImagePersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : deleteFileImageByAntennaCode : Validation failed : response : " + response);
			return response;
		}

		String antennaCode = request.getAntennaCode();
		FileImagePersistResponse response;

		try {
			response = fileImageService.deleteFileImageByAntennaCode(antennaCode);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.FILE_IMAGE_DELETION_FAILURE.getCode();
			String responseMessage = ErrorCodes.FILE_IMAGE_DELETION_FAILURE.getMessage();
			response = new FileImagePersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : deleteFileImageByAntennaCode : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageServiceManager : deleteFileImageByAntennaCode : response " + response);
		return response;
	}

	public FileImagePersistResponse insertFileImage(FileImagePersistRequest request) {
		Map<String, Object> validationResult = FileImageRequestValidator.validateAntennaRequest(request, "insertFileImageMeasurementRecord");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImagePersistResponse response = new FileImagePersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : insertFileImage : Validation failed : response : " + response);
			return response;
		}

		FileImagePersistResponse response;

		try {
			response = fileImageService.insertFileImage(request);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.FILE_IMAGE_INSERTION_FAILURE.getCode();
			String responseMessage = ErrorCodes.FILE_IMAGE_INSERTION_FAILURE.getMessage();
			response = new FileImagePersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageServiceManager : insertFileImage : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageServiceManager : insertFileImage : response " + response);
		return response;
	}
}
