package za.co.antenna.servicemanagers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.enums.MeasurementType;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.service.MeasurementService;
import za.co.antenna.validators.MeasurementRequestValidator;

@Component
public class MeasurementServiceManagerImpl implements MeasurementServiceManager{
	private static final Logger log = LoggerFactory.getLogger(MeasurementServiceManagerImpl.class);

	private static final String IS_REQUEST_VALID = "isRequestValid";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";

	@Autowired
	MeasurementService measurementService;

	public MeasurementServiceManagerImpl() {
	}

	public MeasurementServiceManagerImpl(MeasurementService measurementService) {
		super();
		this.measurementService = measurementService;
	}

	public MeasurementRetrieveResponse getMeasurementRetrieveResponse(MeasurementRetrieveRequest request, MeasurementType requestMeasurementType) {
		Map<String, Object> validationResult = MeasurementRequestValidator.validateMeasurementRequest(request, requestMeasurementType);
		boolean requestIsValid = (boolean) validationResult.get(IS_REQUEST_VALID);

		if (!requestIsValid) {
			String responseCode = (String) validationResult.get(ERROR_CODE);
			String responseMessage = (String) validationResult.get(ERROR_MESSAGE);
			MeasurementRetrieveResponse response = new MeasurementRetrieveResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : MeasurementServiceManager : getMeasurementRetrieveResponse : Validation failed : response : " + response);
			return response;
		}

		MeasurementRetrieveResponse response;

		try {
			response = measurementService.getMeasurementRetrieveResponse(request);
		}
		catch (ServiceFailedException e) {
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			response = new MeasurementRetrieveResponse();
			response.setResponseCode(responseCode);
			response.setResponseMessage(responseMessage);
			log.error("ANTENNA : MeasurementServiceManager : getMeasurementRetrieveResponse : Service failure : ", e);
			return response;
		}

		log.info("ANTENNA : MeasurementServiceManager : getMeasurementRetrieveResponse  response " + response);
		return response;
	}
}
