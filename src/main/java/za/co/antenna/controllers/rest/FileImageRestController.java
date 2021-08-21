package za.co.antenna.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.antenna.dtos.FileImageRetrieveMultipleResponse;
import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImagePersistResponse;
import za.co.antenna.dtos.FileImageRetrieveSingleRequest;
import za.co.antenna.dtos.FileImageRetrieveSingleResponse;
import za.co.antenna.dtos.FileImageRetrieveMultipleRequest;
import za.co.antenna.servicemanagers.FileImageServiceManager;

@RestController
@RequestMapping("/fileimage")
public class FileImageRestController {
	
	@Autowired
	FileImageServiceManager fileImageServiceManager;

	@PostMapping(value = "/findbyantennacode", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageRetrieveSingleResponse getFileImageByAntennaCode(@RequestBody FileImageRetrieveSingleRequest request)  {
		return fileImageServiceManager.getFileImageByAntennaCode(request);
	}

	@PostMapping(value = "/findall", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageRetrieveMultipleResponse getAllFileImages(@RequestBody FileImageRetrieveMultipleRequest request) {
		return fileImageServiceManager.getAllFileImages(request);
	}
	
	@PostMapping(value = "/findallbytype", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageRetrieveMultipleResponse getAllFileImagesByType(@RequestBody FileImageRetrieveMultipleRequest request) {
		return fileImageServiceManager.getAllFileImagesByType(request);
	}
	
	@PostMapping(value = "/findallbystatus", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImageRetrieveMultipleResponse getAllFileImagesByStatus(@RequestBody FileImageRetrieveMultipleRequest request) {
		return fileImageServiceManager.getAllFileImagesByStatus(request);
	}

	@PostMapping(value = "/delete", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImagePersistResponse deleteFileImageByAntennaCode(@RequestBody FileImagePersistRequest request) {
		return fileImageServiceManager.deleteFileImageByAntennaCode(request);
	}

	@PostMapping(value = "/insert", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public FileImagePersistResponse insertFileImage(@RequestBody FileImagePersistRequest request) {
		return fileImageServiceManager.insertFileImage(request);
	}
}
