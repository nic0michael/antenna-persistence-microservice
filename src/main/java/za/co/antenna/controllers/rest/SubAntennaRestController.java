package za.co.antenna.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaPersistResponse;
import za.co.antenna.dtos.AntennaRetrieveMultipleRequest;
import za.co.antenna.dtos.AntennaRetrieveMultipleResponse;
import za.co.antenna.dtos.AntennaRetrieveSingleRequest;
import za.co.antenna.dtos.AntennaRetrieveSingleResponse;
import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.enums.MeasurementType;
import za.co.antenna.servicemanagers.AntennaServiceManager;
import za.co.antenna.servicemanagers.AntennaServiceManagerImpl;
import za.co.antenna.servicemanagers.MeasurementServiceManagerImpl;

@RestController
@RequestMapping("/subantenna")
public class SubAntennaRestController {
	private static final Logger log = LoggerFactory.getLogger(SubAntennaRestController.class);
	
	@Autowired
	AntennaServiceManager antennaServiceManager;
	
	@Autowired
	MeasurementServiceManagerImpl measurementServiceManager;
	
	@PostMapping(value = "/findbyantennacode", 
			produces = {  MediaType.APPLICATION_JSON_VALUE }, 
			consumes = {  MediaType.APPLICATION_JSON_VALUE })
	public AntennaRetrieveSingleResponse getAntennaByAntennaCode(@RequestBody AntennaRetrieveSingleRequest request)  {
		return antennaServiceManager.getAntennaByAntennaCode(request);
	}
	
	@PostMapping(value = "/findall", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AntennaRetrieveMultipleResponse getAllSubAntennas(@RequestBody AntennaRetrieveMultipleRequest request)  {
		log.info("ANTENNA : SubAntennaRestController : getAllSubAntennas : AntennaRetrieveMultipleRequest : "+request);
		AntennaRetrieveMultipleResponse antennaRetrieveMultipleResponse = antennaServiceManager.getAllSubAntennaRecordsByAntennaCode(request);
		log.info("ANTENNA : SubAntennaRestController : getAllSubAntennas : AntennaRetrieveMultipleResponse : "+antennaRetrieveMultipleResponse);
		return antennaRetrieveMultipleResponse;
	}


	@PostMapping(value = "/delete", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AntennaPersistResponse deleteAntenna(@RequestBody AntennaPersistRequest request) {
		return antennaServiceManager.deleteAntenna(request);
	}

	@PostMapping(value = "/insertsubantenna", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AntennaPersistResponse insertSubAntenna(@RequestBody AntennaPersistRequest request) {
		return antennaServiceManager.insertSubAntenna(request);
	}


	@PostMapping(value = "/updatesubantenna", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AntennaPersistResponse updateAntenna(@RequestBody AntennaPersistRequest request) {
		return antennaServiceManager.updateAntenna(request);
	}
}
