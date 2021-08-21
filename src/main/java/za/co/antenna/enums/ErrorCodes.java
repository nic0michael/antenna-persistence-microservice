package za.co.antenna.enums;

public enum ErrorCodes {
	SUCCESS("200", "Service request processed successfully."), 
	INVALID_REQUEST("ANT300", "Invalid or missing request payload."),
	INVALID_MEASUREMENT_TYPE("ANT301", "The measurement type field is missing or invalid."),
	INVALID_FIELD_ANTENNA_CODES("ANT302", "The antenna codes field is missing or invalid."),
	MISSING_ANTENNA_CODE("ANT303", "The antenna code field is missing."),
	MISSING_ANTENNA_TYPE("ANT304", "The antenna type field is missing."),
	MISSING_ANTENNA_ACTION("ANT305", "The antenna insert/update action field is missing."),
	INVALID_FIELD_ANTENNA_ACTION("ANT306", "The antenna insert/update action field value is invalid."),
	ANTENNA_DELETION_FAILURE("ANT401", "Could not delete antenna record from antenna database."),
	ANTENNA_DELETION_SUPERFLUOUS_RECORDS("ANT402", "Requests for create/update/delete require that antenna codes are unique."),
	FILE_IMAGE_INSERTION_FAILURE("ANT403", "Could not create file image record in antenna database."),
	FILE_IMAGE_DELETION_FAILURE("ANT404", "Could not delete file image record from antenna database."),
	MEASUREMENT_INSERTION_FAILURE("ANT405", "Could not create measurement record in antenna database."),
	MEASUREMENT_SERVICE_FAILURE("ANT406", "Could not retrieve measurement information from measurement service."),
	MEASUREMENT_FROM_DATABASE_FAILURE("ANT407", "Could not retrieve measurement information from antenna database."),
	ANTENNA_SERVICE_FAILURE("ANT408", "Could not retrieve antenna information from antenna service."),
	ANTENNA_FROM_DATABASE_FAILURE("ANT409", "Could not retrieve antenna information from antenna database."),
	FILE_IMAGE_SERVICE_FAILURE("ANT410", "Could not retrieve file image information from antenna service."),
	FILE_IMAGE_FROM_DATABASE_FAILURE("ANT411", "Could not retrieve file image information from antenna database."),
	PUBLISH_SERVICE_FAILURE("ANT412", "Could not publish measurement information from antenna service."),
	PUBLISH_FROM_DATABASE_FAILURE("ANT413", "Could not publish measurement information from antenna database.");

	String code;
	String message;

	private ErrorCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public String getCode() {
		return this.code;
	}
}
