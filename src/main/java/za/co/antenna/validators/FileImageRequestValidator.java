package za.co.antenna.validators;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImageRetrieveMultipleRequest;
import za.co.antenna.dtos.FileImageRetrieveSingleRequest;
import za.co.antenna.enums.ErrorCodes;

public class FileImageRequestValidator {
	private static final Logger log = LoggerFactory.getLogger(FileImageRequestValidator.class);

	private static final String IS_REQUEST_VALID = "isRequestValid";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";

	private static String requestType;

	public static Map<String, Object> validateAntennaRequest(FileImageRetrieveSingleRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(FileImageRetrieveSingleRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAntennaCode()) &&
		   (requestType.equals("getFileImageByAntennaCode")
			)) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_CODE.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_CODE.getMessage());
			log.error("ANTENNA | FileImageMeasurementRequestValidator | validateAntennaRequest | INVALID_ANTENNA_CODE ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}

	public static Map<String, Object> validateAntennaRequest(FileImageRetrieveMultipleRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(FileImageRetrieveMultipleRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAntennaCode()) &&
		   (requestType.equals("getAllFileImagesByType") ||
			requestType.equals("getAllFileImagesByStatus")
			)) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_CODE.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_CODE.getMessage());
			log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | INVALID_ANTENNA_CODE ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}

	public static Map<String, Object> validateAntennaRequest(FileImagePersistRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(FileImagePersistRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAntennaCode()) &&
		   (requestType.equals("deleteFileImageByAntennaCode") ||
			requestType.equals("insertFileImage")
			)) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_CODE.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_CODE.getMessage());
			log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | INVALID_ANTENNA_CODE ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | FileImageRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}
}
