package za.co.antenna.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.antenna.dtos.MeasurementRetrieveRequest;
import za.co.antenna.dtos.MeasurementRetrieveResponse;
import za.co.antenna.enums.MeasurementType;
import za.co.antenna.servicemanagers.MeasurementServiceManagerImpl;

@RestController
@RequestMapping("/measurement")
public class MeasurementRestController {
	
	@Autowired
	MeasurementServiceManagerImpl measurementServiceManager;
	
	@PostMapping(value = "/gain", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public MeasurementRetrieveResponse getGainResponse(@RequestBody MeasurementRetrieveRequest request)  {
		return measurementServiceManager.getMeasurementRetrieveResponse(request, MeasurementType.GAIN);
	}	
	
	@PostMapping(value = "/vswr", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public MeasurementRetrieveResponse getVswrResponse(@RequestBody MeasurementRetrieveRequest request)  {
		return  measurementServiceManager.getMeasurementRetrieveResponse(request, MeasurementType.VSWR);
	}		
	
	@PostMapping(value = "/vpolar", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public MeasurementRetrieveResponse getVerticalpolarPlot(@RequestBody MeasurementRetrieveRequest request)  {
		return measurementServiceManager.getMeasurementRetrieveResponse(request, MeasurementType.VPOLAR);
	}
	
	@PostMapping(value = "/hpolar", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public MeasurementRetrieveResponse getHorizontalPolarPlot(@RequestBody MeasurementRetrieveRequest request)  {
		return measurementServiceManager.getMeasurementRetrieveResponse(request, MeasurementType.HPOLAR);
	}

	@PostMapping(value = "/all", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public MeasurementRetrieveResponse getAnyAntennaPlot(@RequestBody MeasurementRetrieveRequest request)  {
		return measurementServiceManager.getMeasurementRetrieveResponse(request, MeasurementType.ALL);
	}
}
