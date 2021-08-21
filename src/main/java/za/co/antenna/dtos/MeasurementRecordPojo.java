package za.co.antenna.dtos;

public class MeasurementRecordPojo {
	String dataId;
	String dateCreated;
	String measurementCode;
	String measurementName;
	String antennaTypeCode;
	String antennaDesription ;
	String antennaCode;
	String antennaName;
	String determinant;
	String value;

	public MeasurementRecordPojo() {}

	public MeasurementRecordPojo(String dataId,
								 String dateCreated,
								 String measurementCode,
								 String measurementName,
								 String antennaTypeCode,
								 String antennaDesription,
								 String antennaCode,
								 String antennaName,
								 String determinant,
								 String value) {
		super();
		this.dataId = dataId;
		this.dateCreated = dateCreated;
		this.measurementCode = measurementCode;
		this.measurementName = measurementName;
		this.antennaTypeCode = antennaTypeCode;
		this.antennaDesription = antennaDesription;
		this.antennaCode = antennaCode;
		this.antennaName = antennaName;
		this.determinant = determinant;
		this.value = value;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getMeasurementCode() {
		return measurementCode;
	}

	public void setMeasurementCode(String measurementCode) {
		this.measurementCode = measurementCode;
	}

	public String getMeasurementName() {
		return measurementName;
	}

	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}

	public String getAntennaTypeCode() {
		return antennaTypeCode;
	}

	public void setAntennaTypeCode(String antennaTypeCode) {
		this.antennaTypeCode = antennaTypeCode;
	}

	public String getAntennaDesription() {
		return antennaDesription;
	}

	public void setAntennaDesription(String antennaDesription) {
		this.antennaDesription = antennaDesription;
	}

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
	}

	public String getAntennaName() {
		return antennaName;
	}

	public void setAntennaName(String antennaName) {
		this.antennaName = antennaName;
	}

	public String getDeterminant() {
		return determinant;
	}

	public void setDeterminant(String determinant) {
		this.determinant = determinant;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MeasurementRecordPojo [dataId=" +
				dataId +
				", dateCreated=" +
				dateCreated +
				", measurementCode=" +
				measurementCode +
				", measurementName=" +
				measurementName +
				", antennaTypeCode=" +
				antennaTypeCode +
				", antennaDesription=" +
				antennaDesription +
				", mainAntennaCode=" +
				antennaCode +
				", antennaName=" +
				antennaName +
				", determinant=" +
				determinant +
				", value=" +
				value +
				"]";
	}
}
