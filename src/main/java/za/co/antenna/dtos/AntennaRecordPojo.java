package za.co.antenna.dtos;

public class AntennaRecordPojo {
	String antennaId;
	String dateCreated;
	String antennaCode;
	String antennaName;
	String antennaTypeCode;
	String antennaDesription ;
	String mainAntennaCode;

	public AntennaRecordPojo() {}

	public AntennaRecordPojo(String antennaId,
							 String dateCreated,
							 String antennaCode,
							 String antennaName,
							 String antennaTypeCode,
							 String antennaDesription,
							 String mainAntennaCode) {
		super();
		this.antennaId = antennaId;
		this.dateCreated = dateCreated;
		this.antennaCode = antennaCode;
		this.antennaName = antennaName;
		this.antennaTypeCode = antennaTypeCode;
		this.antennaDesription = antennaDesription;
		this.mainAntennaCode = mainAntennaCode;
	}

	public String getAntennaId() {
		return antennaId;
	}

	public void setAntennaId(String antennaId) {
		this.antennaId = antennaId;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
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

	public String getMainAntennaCode() {
		return mainAntennaCode;
	}

	public void setMainAntennaCode(String mainAntennaCode) {
		this.mainAntennaCode = mainAntennaCode;
	}

	@Override
	public String toString() {
		return "antennaRecordPojo [dataId=" +
				antennaId +
				", dateCreated=" +
				dateCreated +
				", antennaCode=" +
				antennaCode +
				", antennaName=" +
				antennaName +
				", antennaTypeCode=" +
				antennaTypeCode +
				", antennaDesription=" +
				antennaDesription +
				", mainAntennaCode=" +
				mainAntennaCode +
				"]";
	}
}
