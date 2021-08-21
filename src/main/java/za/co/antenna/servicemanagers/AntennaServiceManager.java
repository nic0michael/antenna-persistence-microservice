package za.co.antenna.servicemanagers;

import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaPersistResponse;
import za.co.antenna.dtos.AntennaRetrieveMultipleRequest;
import za.co.antenna.dtos.AntennaRetrieveMultipleResponse;
import za.co.antenna.dtos.AntennaRetrieveSingleRequest;
import za.co.antenna.dtos.AntennaRetrieveSingleResponse;

public interface AntennaServiceManager {
	public AntennaRetrieveSingleResponse getAntennaByAntennaCode(AntennaRetrieveSingleRequest request);

	public AntennaRetrieveMultipleResponse getAllAntennas(AntennaRetrieveMultipleRequest request);

	public AntennaRetrieveMultipleResponse getAllAntennasByType(AntennaRetrieveMultipleRequest request);

	public AntennaRetrieveMultipleResponse getAllBaseAntennaRecordsByAntennaCode(AntennaRetrieveMultipleRequest request);

	public AntennaRetrieveMultipleResponse getAllSubAntennaRecordsByAntennaCode(AntennaRetrieveMultipleRequest request);

	public AntennaPersistResponse deleteAntenna(AntennaPersistRequest request);

	public AntennaPersistResponse persistAntenna(AntennaPersistRequest request);

	public AntennaPersistResponse insertSubAntenna(AntennaPersistRequest request);

	public AntennaPersistResponse insertBaseAntenna(AntennaPersistRequest request);

	public AntennaPersistResponse updateAntenna(AntennaPersistRequest request);
}
