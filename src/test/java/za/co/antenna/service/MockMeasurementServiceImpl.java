package za.co.antenna.service;

import java.util.ArrayList;
import java.util.List;

import za.co.antenna.dtos.Measurement;
import za.co.antenna.dtos.MeasurementPojo;
import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.enums.TestType;
import za.co.antenna.exceptions.ServiceFailedException;

public class MockMeasurementServiceImpl implements MeasurementService {
	String testType;

	public MockMeasurementServiceImpl(TestType testType) {
		this.testType = testType.name();
	}

	@Override
	public MeasurementRetrieveResponse getMeasurementRetrieveResponse(MeasurementRetrieveRequest request)
			throws ServiceFailedException {
		MeasurementRetrieveResponse response = null;

		switch (testType) {
			case "PASSING":
				response = makeSuccessResponse();
				break;
			case "FAILING":
				response = makeFailedResponse();
				break;
			case "THROWS_EXCEPTIONS":
				throw new ServiceFailedException();
			}
		return response;
	}

	private MeasurementRetrieveResponse makeFailedResponse() {
		MeasurementRetrieveResponse response = new MeasurementRetrieveResponse();
		String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
		String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
		response.setResponseMessage(responseMessage);
		response.setResponseCode(responseCode);
		return response;
	}

	private MeasurementRetrieveResponse makeSuccessResponse() {
		MeasurementRetrieveResponse response = new MeasurementRetrieveResponse();
		String responseMessage = ErrorCodes.SUCCESS.getMessage();
		String responseCode = ErrorCodes.SUCCESS.getCode();
		String vswrAntennaName = "TESTAnt001";
		String vswrLabel = "dummy_vswr_label";
		String vswrLastUploadDate = "2020-12-17";
		String vswrMeasurementName = "dummy_measurement_name";
		response.setMeasurementName(vswrMeasurementName);
		response.setLastUploadDate(vswrLastUploadDate);
		response.setAntennaName(vswrAntennaName);
		response.setLabel(vswrLabel);
		response.setResponseMessage(responseMessage);
		response.setResponseCode(responseCode);
		response.setMeasurements(makeMeasurements());
		return response;
	}

	private List<Measurement> makeMeasurements() {
		List<Measurement> measurements = new ArrayList<>();
		List<MeasurementPojo> measurementPojos = makeMeasurementPojos();
		Measurement measurement = new Measurement();

		String displayValue = "Antenna 007 | vertical whip";
		measurement.setDisplayValue(displayValue);
		measurement.setMeasurementPojos(measurementPojos);
		measurements.add(measurement);
		return measurements;
	}

	private List<MeasurementPojo> makeMeasurementPojos() {

		MeasurementPojo gainPojo = null;
		List<MeasurementPojo> gainPojos = new ArrayList<>();

		gainPojo = new MeasurementPojo("100", "-9");
		gainPojos.add(gainPojo);

		gainPojo = new MeasurementPojo("100", "-6");
		gainPojos.add(gainPojo);

		gainPojo = new MeasurementPojo("110", "-3");
		gainPojos.add(gainPojo);

		gainPojo = new MeasurementPojo("110", "0");
		gainPojos.add(gainPojo);

		gainPojo = new MeasurementPojo("130", "-3");
		gainPojos.add(gainPojo);

		gainPojo = new MeasurementPojo("140", "-6");
		gainPojos.add(gainPojo);

		gainPojo = new MeasurementPojo("150", "-9");
		gainPojos.add(gainPojo);

		return gainPojos;
	}
}
