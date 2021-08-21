package za.co.antenna.validators;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.antenna.dtos.FileImageMeasurementPersistRequest;
import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleRequest;
import za.co.antenna.enums.ErrorCodes;

public class FileImageMeasurementRequestValidator {
	private static final Logger log = LoggerFactory.getLogger(FileImageMeasurementRequestValidator.class);

	private static final String IS_REQUEST_VALID = "isRequestValid";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";

	private static String requestType;

	public static Map<String, Object> validateAntennaRequest(FileImageMeasurementRetrieveMultipleRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(FileImageMeasurementRetrieveMultipleRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | FileImageMeasurementRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAntennaCode()) &&
		   (requestType.equals("getFileImageMeasurementsByAntennaCode")
			)) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_CODE.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_CODE.getMessage());
			log.error("ANTENNA | FileImageMeasurementRequestValidator | validateAntennaRequest | INVALID_ANTENNA_CODE ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | FileImageMeasurementRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}

	public static Map<String, Object> validateAntennaRequest(FileImageMeasurementPersistRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(FileImageMeasurementPersistRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | FileImageMeasurementRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAntennaCode()) &&
		   (requestType.equals("deleteFileImageMeasurementsByAntennaCode") ||
			requestType.equals("insertFileImageMeasurementRecord") ||
			requestType.equals("publishFileImageMeasurements")
			)) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_CODE.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_CODE.getMessage());
			log.error("ANTENNA | FileImageMeasurementRequestValidator | validateAntennaRequest | INVALID_ANTENNA_CODE ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | FileImageMeasurementRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}
}
