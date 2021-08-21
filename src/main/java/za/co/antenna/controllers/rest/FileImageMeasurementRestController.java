package za.co.antenna.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleResponse;
import za.co.antenna.dtos.FileImageMeasurementPersistRequest;
import za.co.antenna.dtos.FileImageMeasurementPersistResponse;
import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleRequest;
import za.co.antenna.servicemanagers.FileImageMeasurementServiceManager;

@RestController
@RequestMapping("/fileimagemeasurement")
public class FileImageMeasurementRestController {

	@Autowired
	FileImageMeasurementServiceManager fileImageMeasurementServiceManager;

	@PostMapping(value = "/findall", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageMeasurementRetrieveMultipleResponse getAllFileImageMeasurements(@RequestBody FileImageMeasurementRetrieveMultipleRequest request) {
		return fileImageMeasurementServiceManager.getAllFileImageMeasurements(request);
	}

	@PostMapping(value = "/findallbyantennacode", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageMeasurementRetrieveMultipleResponse getFileImageMeasurementsByAntennaCode(@RequestBody FileImageMeasurementRetrieveMultipleRequest request) {
		return fileImageMeasurementServiceManager.getFileImageMeasurementsByAntennaCode(request);
	}

	@PostMapping(value = "/delete", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageMeasurementPersistResponse deleteFileImageMeasurementsByAntennaCode(@RequestBody FileImageMeasurementPersistRequest request) {
		return fileImageMeasurementServiceManager.deleteFileImageMeasurementsByAntennaCode(request);
	}

	@PostMapping(value = "/insert", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageMeasurementPersistResponse insertFileImageMeasurement(@RequestBody FileImageMeasurementPersistRequest request) {
		return fileImageMeasurementServiceManager.insertFileImageMeasurementRecord(request);
	}

	@PostMapping(value = "/publish", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageMeasurementPersistResponse publishFileImageMeasurements(@RequestBody FileImageMeasurementPersistRequest request) {
		return fileImageMeasurementServiceManager.publishFileImageMeasurements(request);
	}
}
