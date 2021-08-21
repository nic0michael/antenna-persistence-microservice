package za.co.antenna.service;

import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.exceptions.ServiceFailedException;

public interface MeasurementService {
	MeasurementRetrieveResponse getMeasurementRetrieveResponse(MeasurementRetrieveRequest request)
			throws ServiceFailedException;
}
