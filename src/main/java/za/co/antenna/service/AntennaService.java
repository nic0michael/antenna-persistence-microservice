package za.co.antenna.service;

import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaPersistResponse;
import za.co.antenna.dtos.AntennaRetrieveMultipleResponse;
import za.co.antenna.dtos.AntennaRetrieveSingleResponse;
import za.co.antenna.exceptions.ServiceFailedException;

public interface AntennaService {
	public AntennaRetrieveSingleResponse getAntennaByAntennaCode(String antennaCode) throws ServiceFailedException;;

	public AntennaRetrieveMultipleResponse getAllAntennas(String orderBy) throws ServiceFailedException;

	public AntennaRetrieveMultipleResponse getAllAntennasByType(String antennaType, String orderBy) throws ServiceFailedException;

	public AntennaRetrieveMultipleResponse getAllBaseAntennasByAntennaTypeCode(String antennaTypeCode, String orderBy) throws ServiceFailedException;

	public AntennaRetrieveMultipleResponse getAllSubAntennasByAntennaTypeCode(String antennaTypeCode, String orderBy) throws ServiceFailedException;

	public AntennaPersistResponse deleteAntenna(AntennaPersistRequest request) throws ServiceFailedException;

	public AntennaPersistResponse persistAntenna(AntennaPersistRequest request) throws ServiceFailedException;
}
