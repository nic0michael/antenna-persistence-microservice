package za.co.antenna.dtos;

import java.util.List;

public class Measurement {
	private String displayValue;
	private List<MeasurementPojo> measurementPojos;

	public Measurement() {}

	public Measurement(String displayValue, List<MeasurementPojo> measurementPojos) {
		super();
		this.displayValue = displayValue;
		this.measurementPojos = measurementPojos;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public List<MeasurementPojo> getMeasurementPojos() {
		return measurementPojos;
	}

	public void setMeasurementPojos(List<MeasurementPojo> measurementPojos) {
		this.measurementPojos = measurementPojos;
	}

	@Override
	public String toString() {
		return "Measurement [displayValue=" + displayValue + ", measurementPojos=" + measurementPojos + "]";
	}
	
	
}
