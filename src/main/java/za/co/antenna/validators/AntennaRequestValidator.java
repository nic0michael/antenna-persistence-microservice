package za.co.antenna.validators;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaRetrieveMultipleRequest;
import za.co.antenna.dtos.AntennaRetrieveSingleRequest;
import za.co.antenna.enums.ErrorCodes;

public class AntennaRequestValidator {
	private static final Logger log = LoggerFactory.getLogger(AntennaRequestValidator.class);

	private static final String IS_REQUEST_VALID = "isRequestValid";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";

	private static String requestType;

	public static Map<String, Object> validateAntennaRequest(AntennaRetrieveSingleRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(AntennaRetrieveSingleRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}

	public static Map<String, Object> validateAntennaRequest(AntennaRetrieveMultipleRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(AntennaRetrieveMultipleRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAntennaTypeCode()) &&
		   (requestType.equals("getAllAntennasByType"))) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_TYPE.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_TYPE.getMessage());
			log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | INVALID_ANTENNA_TYPE ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}

	public static Map<String, Object> validateAntennaRequest(AntennaPersistRequest request,
															 String requestedType) {
		requestType = requestedType;
		return validateAntennaRequest(request);
	}

	private static Map<String, Object> validateAntennaRequest(AntennaPersistRequest request) {
		Map<String, Object> validationResultMap = new HashMap<>();

		if (request == null) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.INVALID_REQUEST.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.INVALID_REQUEST.getMessage());
			log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | INVALID_REQUEST ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAntennaCode()) &&
		   (requestType.equals("deleteAntenna") || requestType.equals("persistAntenna"))) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_CODE.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_CODE.getMessage());
			log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | INVALID_ANTENNA_CODE ");
			return validationResultMap;
		}

		if (StringUtils.isEmpty(request.getAction()) &&
		   (requestType.equals("deleteAntenna") || requestType.equals("persistAntenna"))) {
			validationResultMap.put(IS_REQUEST_VALID, false);
			validationResultMap.put(ERROR_CODE, ErrorCodes.MISSING_ANTENNA_ACTION.getCode());
			validationResultMap.put(ERROR_MESSAGE, ErrorCodes.MISSING_ANTENNA_ACTION.getMessage());
			log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | INVALID_ANTENNA_ACTION ");
			return validationResultMap;
		}

		validationResultMap.put(IS_REQUEST_VALID, true);
		log.error("ANTENNA | AntennaRequestValidator | validateAntennaRequest | Completed");
		return validationResultMap;		
	}
}
