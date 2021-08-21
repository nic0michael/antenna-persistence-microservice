package za.co.antenna.servicemanagers;

import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImagePersistResponse;
import za.co.antenna.dtos.FileImageRetrieveMultipleRequest;
import za.co.antenna.dtos.FileImageRetrieveMultipleResponse;
import za.co.antenna.dtos.FileImageRetrieveSingleRequest;
import za.co.antenna.dtos.FileImageRetrieveSingleResponse;

public interface FileImageServiceManager {
	public FileImageRetrieveSingleResponse getFileImageByAntennaCode(FileImageRetrieveSingleRequest request);

	public FileImageRetrieveMultipleResponse getAllFileImages(FileImageRetrieveMultipleRequest request);

	public FileImageRetrieveMultipleResponse getAllFileImagesByType(FileImageRetrieveMultipleRequest request);

	public FileImageRetrieveMultipleResponse getAllFileImagesByStatus(FileImageRetrieveMultipleRequest request);

	public FileImagePersistResponse deleteFileImageByAntennaCode(FileImagePersistRequest request);

	public FileImagePersistResponse insertFileImage(FileImagePersistRequest request);
}
