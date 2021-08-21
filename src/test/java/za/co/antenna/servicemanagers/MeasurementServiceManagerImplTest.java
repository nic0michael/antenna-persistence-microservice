package za.co.antenna.servicemanagers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import za.co.antenna.dtos.Measurement;
import za.co.antenna.dtos.MeasurementPojo;
import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.enums.MeasurementType;
import za.co.antenna.enums.TestType;
import za.co.antenna.service.MeasurementService;
import za.co.antenna.service.MeasurementServiceImpl;
import za.co.antenna.service.MockMeasurementServiceImpl;

@RunWith(SpringRunner.class)
public class MeasurementServiceManagerImplTest {

	@Test
	public void getGainResponseSuccessfulTest() {

		MeasurementService measurementService = new MockMeasurementServiceImpl(TestType.PASSING);
		MeasurementServiceManager measurementServiceManager = new MeasurementServiceManagerImpl( measurementService);

		MeasurementRetrieveRequest request = makeGainMeasurementRequest();

		MeasurementRetrieveResponse response = measurementServiceManager.getMeasurementRetrieveResponse(request,
																										MeasurementType.GAIN);
		assertThat(response, notNullValue());

		List<Measurement> measurements = response.getMeasurements();
		assertThat(measurements, notNullValue());
		Measurement measurement = measurements.get(0);
		assertThat(measurement, notNullValue());
		List<MeasurementPojo> pojos = measurement.getMeasurementPojos();
		assertThat(pojos, notNullValue());
		assertThat(pojos.isEmpty(), is(false));

		String responseCode = response.getResponseCode();
		String responseMessage = response.getResponseMessage();
		System.out.println("responseCode : " + responseCode);
		System.out.println("responseMessage : " + responseMessage);

		assertThat(responseCode, is(ErrorCodes.SUCCESS.getCode()));
		assertThat(responseMessage, is(ErrorCodes.SUCCESS.getMessage()));
	}

	@Test
	public void getGainResponseFailedTest() {		

		MeasurementService measurementService = new MockMeasurementServiceImpl(TestType.FAILING);
		MeasurementServiceManager measurementServiceManager = new MeasurementServiceManagerImpl( measurementService);

		MeasurementRetrieveRequest request = makeGainMeasurementRequest();

		MeasurementRetrieveResponse response = measurementServiceManager.getMeasurementRetrieveResponse(request,
																										MeasurementType.GAIN);
		assertThat(response, notNullValue());

		List<Measurement> measurements = response.getMeasurements();
		assertThat(measurements, is(IsNull.nullValue()));

		String responseCode = response.getResponseCode();
		String responseMessage = response.getResponseMessage();
		System.out.println("responseCode : " + responseCode);
		System.out.println("responseMessage : " + responseMessage);

		assertThat(responseCode, is(ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode()));
		assertThat(responseMessage, is(ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage()));
	}

	@Test
	public void getGainResponseThrowsExceptionTest() {

		MeasurementService measurementService = new MockMeasurementServiceImpl(TestType.THROWS_EXCEPTIONS);
		MeasurementServiceManager measurementServiceManager = new MeasurementServiceManagerImpl( measurementService);

		MeasurementRetrieveRequest request = makeGainMeasurementRequest();

		MeasurementRetrieveResponse response = measurementServiceManager.getMeasurementRetrieveResponse(request,
																										MeasurementType.GAIN);
		assertThat(response, notNullValue());

		List<Measurement> measurements = response.getMeasurements();
		assertThat(measurements, is(IsNull.nullValue()));

		String responseCode = response.getResponseCode();
		String responseMessage = response.getResponseMessage();
		System.out.println("responseCode : " + responseCode);
		System.out.println("responseMessage : " + responseMessage);

		assertThat(responseCode, is(ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode()));
		assertThat(responseMessage, is(ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage()));
	}

	private MeasurementRetrieveRequest makeGainMeasurementRequest() {
		MeasurementRetrieveRequest request = new MeasurementRetrieveRequest();
		request.setMeasurementType(MeasurementType.GAIN.name().toUpperCase());
		request.setAntennaCodes("ANT001|Ant005|Ant007");
		return request;
	}

}
