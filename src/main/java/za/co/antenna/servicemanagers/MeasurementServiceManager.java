package za.co.antenna.servicemanagers;

import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.enums.MeasurementType;

public interface MeasurementServiceManager {
	public MeasurementRetrieveResponse getMeasurementRetrieveResponse(MeasurementRetrieveRequest request, MeasurementType requestMeasurementType);

}
