package za.co.antenna.servicemanagers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Development status: Locked by John - 05/01/2021
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaPersistResponse;
import za.co.antenna.dtos.AntennaRetrieveMultipleResponse;
import za.co.antenna.dtos.AntennaRetrieveSingleResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.dtos.AntennaRetrieveMultipleRequest;
import za.co.antenna.dtos.AntennaRetrieveSingleRequest;
import za.co.antenna.service.AntennaService;
import za.co.antenna.validators.AntennaRequestValidator;

@Component
public class AntennaServiceManagerImpl implements AntennaServiceManager {
	private static final Logger log = LoggerFactory.getLogger(AntennaServiceManagerImpl.class);

	private static final String IS_REQUEST_VALID = "isRequestValid";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	@Autowired
	AntennaService antennaService;

	public AntennaServiceManagerImpl() {
	}

	public AntennaServiceManagerImpl(AntennaService antennaService) {
		super();
		this.antennaService = antennaService;
	}

	public AntennaRetrieveSingleResponse getAntennaByAntennaCode(AntennaRetrieveSingleRequest request) {
		log.info("ANTENNA : AntennaServiceManager : getAntennaByAntennaCode : AntennaRetrieveSingleRequest : "+request);
		Map<String, Object> validationResult = AntennaRequestValidator.validateAntennaRequest(request, "getAntennaByAntennaCode");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			AntennaRetrieveSingleResponse response = new AntennaRetrieveSingleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAntennaByAntennaCode : Validation failed : response : " + response);
			return response;
		}

		String antennaCode = request.getAntennaCode();

		AntennaRetrieveSingleResponse response;

		try {
			response = antennaService.getAntennaByAntennaCode(antennaCode);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new AntennaRetrieveSingleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAntennaByAntennaCode : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : AntennaServiceManager : getAntennaByAntennaCode : response " + response);
		return response;
	}

	public AntennaRetrieveMultipleResponse getAllAntennas(AntennaRetrieveMultipleRequest request) {
		log.info("ANTENNA : AntennaServiceManager : getAllAntennas : AntennaRetrieveMultipleRequest : "+request);
		Map<String, Object> validationResult = AntennaRequestValidator.validateAntennaRequest(request, "getAllAntennas");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennas : Validation failed : response : " + response);
			return response;
		}

		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		AntennaRetrieveMultipleResponse response;

		try {
			response = antennaService.getAllAntennas(orderBy);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennas : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : AntennaServiceManager : getAllAntennas : response " + response);
		return response;
	}

	public AntennaRetrieveMultipleResponse getAllAntennasByType(AntennaRetrieveMultipleRequest request) {
		log.info("ANTENNA : AntennaServiceManager : getAllAntennasByType : AntennaRetrieveMultipleRequest : "+request);
		Map<String, Object> validationResult = AntennaRequestValidator.validateAntennaRequest(request, "getAllAntennasByType");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennasByType : Validation failed : response : " + response);
			return response;
		}

		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		String antennaType = request.getAntennaTypeCode();

		AntennaRetrieveMultipleResponse response;

		try {
			response = antennaService.getAllAntennasByType(antennaType, orderBy);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennasByType : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : AntennaServiceManager : getAllAntennasByType : response " + response);
		return response;
	}

	public AntennaRetrieveMultipleResponse getAllBaseAntennaRecordsByAntennaCode(AntennaRetrieveMultipleRequest request){
		log.info("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : AntennaRetrieveMultipleRequest : "+request);
		Map<String, Object> validationResult = AntennaRequestValidator.validateAntennaRequest(request, "getAntennaByAntennaCode");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : Validation failed : response : " + response);
			return response;
		}

		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		AntennaRetrieveMultipleResponse response;

		try {
			
			String antennaTypeCode="B";
			response = antennaService.getAllBaseAntennasByAntennaTypeCode(antennaTypeCode, orderBy);
		} catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : response " + response);
		return response;
	}
	

	@Override
	public AntennaRetrieveMultipleResponse getAllSubAntennaRecordsByAntennaCode(AntennaRetrieveMultipleRequest request) {
		log.info("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : AntennaRetrieveMultipleRequest : "+request);
		Map<String, Object> validationResult = AntennaRequestValidator.validateAntennaRequest(request, "getAntennaByAntennaCode");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			AntennaRetrieveMultipleResponse response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : Validation failed : response : " + response);
			return response;
		}

		String orderBy = null;

		if (request.getOrderBy() == null || request.getOrderBy().equals("")) {
			orderBy = "antenna_name";
		}
		else {
			orderBy = request.getOrderBy();
		}

		AntennaRetrieveMultipleResponse response;

		try {
			
			String antennaTypeCode="S";
			response = antennaService.getAllSubAntennasByAntennaTypeCode(antennaTypeCode, orderBy);
		} catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new AntennaRetrieveMultipleResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : AntennaServiceManager : getAllAntennasByBaseAntennaCode : response " + response);
		return response;
	}
	
	public AntennaPersistResponse deleteAntenna(AntennaPersistRequest request) {
		log.info("ANTENNA : AntennaServiceManager : deleteAntenna : AntennaPersistRequest : "+request);
		Map<String, Object> validationResult = AntennaRequestValidator.validateAntennaRequest(request, "deleteAntenna");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			AntennaPersistResponse response = new AntennaPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : deleteAntenna : Validation failed : response : " + response);
			return response;
		}

		AntennaPersistResponse response;

		try {
			response = antennaService.deleteAntenna(request);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new AntennaPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : deleteAntenna : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : AntennaServiceManager : deleteAntenna : response " + response);
		return response;
	}

	public AntennaPersistResponse persistAntenna(AntennaPersistRequest request) {
		log.info("ANTENNA : AntennaServiceManager : persistAntenna : AntennaPersistRequest : "+request);
		Map<String, Object> validationResult = AntennaRequestValidator.validateAntennaRequest(request, "persistAntenna");
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			AntennaPersistResponse response = new AntennaPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : persistAntenna : Validation failed : response : " + response);
			return response;
		}

		AntennaPersistResponse response;

		try {
			response = antennaService.persistAntenna(request);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new AntennaPersistResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : AntennaServiceManager : persistAntenna : Service faiure : ", e);
			return response;
		}

		log.info("ANTENNA : AntennaServiceManager : persistAntenna : response " + response);
		return response;
	}

	public AntennaPersistResponse insertSubAntenna(AntennaPersistRequest request) {
		log.info("ANTENNA : AntennaServiceManager : insertSubAntenna : AntennaPersistRequest : "+request);
		request.setAction("INSERT");
		return persistAntenna(request);
	}

	public AntennaPersistResponse insertBaseAntenna(AntennaPersistRequest request) {
		log.info("ANTENNA : AntennaServiceManager : insertBaseAntenna : AntennaPersistRequest : "+request);
		request.setAction("INSERT");
		request.setMainAntennaCode(request.getAntennaCode());
		return persistAntenna(request);
	}

	public AntennaPersistResponse updateAntenna(AntennaPersistRequest request) {
		log.info("ANTENNA : AntennaServiceManager : updateAntenna : AntennaPersistRequest : "+request);
		request.setAction("UPDATE");
		return persistAntenna(request);
	}
}
