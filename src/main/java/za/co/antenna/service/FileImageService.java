package za.co.antenna.service;

import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImagePersistResponse;
import za.co.antenna.dtos.FileImageRetrieveMultipleResponse;
import za.co.antenna.dtos.FileImageRetrieveSingleResponse;
import za.co.antenna.exceptions.ServiceFailedException;

public interface FileImageService {
	public FileImageRetrieveSingleResponse getFileImageByAntennaCode(String antennaCode) throws ServiceFailedException;

	public FileImageRetrieveMultipleResponse getAllFileImages(String orderBy) throws ServiceFailedException;

	public FileImageRetrieveMultipleResponse getAllFileImagesByType(String antennaType, String orderBy) throws ServiceFailedException;

	public FileImageRetrieveMultipleResponse getAllFileImagesByStatus(String status, String orderBy) throws ServiceFailedException;

	public FileImagePersistResponse deleteFileImageByAntennaCode(String antennaCode) throws ServiceFailedException;

	public FileImagePersistResponse insertFileImage(FileImagePersistRequest fileImagePersistRequest) throws ServiceFailedException;
}
