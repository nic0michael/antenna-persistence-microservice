package za.co.antenna.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.antenna.dtos.Measurement;
import za.co.antenna.dtos.MeasurementPojo;
import za.co.antenna.dtos.MeasurementRecordPojo;
import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.enums.ErrorCodes;
import za.co.antenna.exceptions.ServiceFailedException;
import za.co.antenna.repositories.MeasurementRepository;
import za.co.antenna.repositories.AntennaRepository;

@Service
public class MeasurementServiceImpl implements MeasurementService{
	
	@Autowired
	AntennaRepository antennaRepository;
	
	@Autowired
	MeasurementRepository measurementRepository;

	@Override
	public MeasurementRetrieveResponse getMeasurementRetrieveResponse(MeasurementRetrieveRequest request) throws ServiceFailedException {
		MeasurementRetrieveResponse response = null;
		
		String antennaCode = request.getAntennaCodes();
		String measurementCode = request.getMeasurementType();
		List<MeasurementRecordPojo> measurementRecords = measurementRepository.getMeasurementRecords(antennaCode, measurementCode);
		response = makeMeasurementRetrieveResponse(measurementRecords);			
		response.setMeasurementType(request.getMeasurementType());
		return response;
	}
	
	private MeasurementRetrieveResponse makeMeasurementRetrieveResponse(List<MeasurementRecordPojo> measurementRecords ) {
		String previousAntennaCode = null;
		String measurementName = null;
		String lastUploadDate = null;
		List<Measurement> measurements = new ArrayList<>();
		List<MeasurementPojo> measurementPojos = new ArrayList<>();
		MeasurementRetrieveResponse response = new MeasurementRetrieveResponse();
		Measurement measurement = null;
		
		if(measurementRecords != null && !measurementRecords.isEmpty()) {
			for (MeasurementRecordPojo measurementRecordPojo : measurementRecords) {
				String antennaCode = measurementRecordPojo.getAntennaCode();

				if (StringUtils.isNotEmpty(antennaCode) && !antennaCode.equalsIgnoreCase(previousAntennaCode)) { 
					if(StringUtils.isNotEmpty(previousAntennaCode)) {
						measurement.setMeasurementPojos(measurementPojos);
						measurementPojos = new ArrayList<>();
						measurements.add(measurement);
					}

					previousAntennaCode = antennaCode;
					measurement = new Measurement();
					String displayValue = measurementRecordPojo.getAntennaName();
					measurement.setDisplayValue(displayValue);
					measurementName = measurementRecordPojo.getMeasurementName();
					lastUploadDate = measurementRecordPojo.getDateCreated();
				}

				MeasurementPojo pojo = new MeasurementPojo();
				pojo.setDeterminant(measurementRecordPojo.getDeterminant());
				pojo.setValue(measurementRecordPojo.getValue());
				measurementPojos.add(pojo);
			}

			if(StringUtils.isNotEmpty(previousAntennaCode)) {
				measurement.setMeasurementPojos(measurementPojos);
				measurements.add(measurement);
			}
		}

		if (measurements != null && !measurements.isEmpty()) {
			response.setMeasurements(measurements);			
			response.setMeasurementName(measurementName);
			response.setLabel(measurementName);
			response.setLastUploadDate(lastUploadDate);

			String responseMessage = ErrorCodes.SUCCESS.getMessage();
			String responseCode = ErrorCodes.SUCCESS.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);
		}
		else {
			String responseMessage = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getMessage();
			String responseCode = ErrorCodes.MEASUREMENT_SERVICE_FAILURE.getCode();
			response.setResponseMessage(responseMessage);
			response.setResponseCode(responseCode);
		}
		
		return response;
	}
}
