package za.co.antenna.servicemanagers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.antenna.dtos.FileImageMeasurementPersistRequest;
import za.co.antenna.dtos.FileImageMeasurementPersistResponse;
import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleRequest;
import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.service.FileImageMeasurementService;
import za.co.antenna.validators.FileImageMeasurementRequestValidator;

@Component
public class FileImageMeasurementServiceManagerImpl implements FileImageMeasurementServiceManager {
	private static final Logger log = LoggerFactory.getLogger(FileImageMeasurementServiceManagerImpl.class);

	private static final String IS_REQUEST_VALID = "isRequestValid";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	@Autowired
	FileImageMeasurementService fileImageMeasurementService;

	public FileImageMeasurementServiceManagerImpl() {
	}

	public FileImageMeasurementServiceManagerImpl(FileImageMeasurementService fileImageMeasurementService) {
		super();
		this.fileImageMeasurementService = fileImageMeasurementService;
	}

	public FileImageMeasurementRetrieveMultipleResponse getFileImageMeasurementsByAntennaCode(FileImageMeasurementRetrieveMultipleRequest request) {
		Map<String, Object> validationResult = FileImageMeasurementRequestValidator.validateAntennaRequest(request, "getFileImageMeasurementsByAntennaCode");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageMeasurementRetrieveMultipleResponse response = new FileImageMeasurementRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : getFileImageMeasurementsByAntennaCode : Validation failed : response : " + response);
			return response;
		}

		String antennaCode = request.getAntennaCode();
		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		FileImageMeasurementRetrieveMultipleResponse response;

		try {
			response = fileImageMeasurementService.getFileImageMeasurementsByAntennaCode(antennaCode, orderBy);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new FileImageMeasurementRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : getAntennaByAntennaCode : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageMeasurementServiceManager : getAntennaByAntennaCode : response " + response);
		return response;
	}

	public FileImageMeasurementRetrieveMultipleResponse getAllFileImageMeasurements(FileImageMeasurementRetrieveMultipleRequest request) {
		Map<String, Object> validationResult = FileImageMeasurementRequestValidator.validateAntennaRequest(request, "getAllFileImageMeasurements");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageMeasurementRetrieveMultipleResponse response = new FileImageMeasurementRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : getAllFileImageMeasurements : Validation failed : response : " + response);
			return response;
		}

		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		FileImageMeasurementRetrieveMultipleResponse response;

		try {
			response = fileImageMeasurementService.getAllFileImageMeasurements(orderBy);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new FileImageMeasurementRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : getAllFileImageMeasurements : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageMeasurementServiceManager : getAllFileImageMeasurements : response " + response);
		return response;
	}

	public FileImageMeasurementPersistResponse deleteFileImageMeasurementsByAntennaCode(FileImageMeasurementPersistRequest request) {
		Map<String, Object> validationResult = FileImageMeasurementRequestValidator.validateAntennaRequest(request, "deleteFileImageMeasurementsByAntennaCode");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageMeasurementPersistResponse response = new FileImageMeasurementPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : deleteFileImageMeasurementsByAntennaCode : Validation failed : response : " + response);
			return response;
		}

		String antennaCode = request.getAntennaCode();
		FileImageMeasurementPersistResponse response;

		try {
			response = fileImageMeasurementService.deleteFileImageMeasurementsByAntennaCode(antennaCode);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new FileImageMeasurementPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : deleteFileImageMeasurementsByAntennaCode : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageMeasurementServiceManager : deleteFileImageMeasurementsByAntennaCode : response " + response);
		return response;
	}

	public FileImageMeasurementPersistResponse insertFileImageMeasurementRecord(FileImageMeasurementPersistRequest request) {
		Map<String, Object> validationResult = FileImageMeasurementRequestValidator.validateAntennaRequest(request, "insertFileImageMeasurementRecord");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageMeasurementPersistResponse response = new FileImageMeasurementPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : insertFileImageMeasurementRecord : Validation failed : response : " + response);
			return response;
		}

		FileImageMeasurementPersistResponse response;

		try {
			response = fileImageMeasurementService.insertFileImageMeasurementRecord(request);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new FileImageMeasurementPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : insertFileImageMeasurementRecord : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageMeasurementServiceManager : insertFileImageMeasurementRecord : response " + response);
		return response;
	}

	public FileImageMeasurementPersistResponse publishFileImageMeasurements(FileImageMeasurementPersistRequest request) {
		Map<String, Object> validationResult = FileImageMeasurementRequestValidator.validateAntennaRequest(request, "publishFileImageMeasurements");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			FileImageMeasurementPersistResponse response = new FileImageMeasurementPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : publishFileImageMeasurements : Validation failed : response : " + response);
			return response;
		}

		String antennaCode = request.getAntennaCode();
		FileImageMeasurementPersistResponse response;

		try {
			response = fileImageMeasurementService.publishFileImageMeasurements(antennaCode);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.PUBLISH_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.PUBLISH_SERVICE_FAILURE.getMessage();
			response = new FileImageMeasurementPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : FileImageMeasurementServiceManager : publishFileImageMeasurements : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : FileImageMeasurementServiceManager : publishFileImageMeasurements : response " + response);
		return response;
	}
}
