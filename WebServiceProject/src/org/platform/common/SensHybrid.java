package org.platform.common;


import java.util.List;


public class SensHybrid extends ABComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Sensor> sensors;


	final static String[] topics = { "osgi/testEvent" };

	public enum modes {
		sync, async
	}

	public SensHybrid(String id, String name, String model, String type,
			String state, String description, String nature,
			List<Sensor> sensors) {
		super(id, name, model, type, state, description, nature);
		this.sensors = sensors;

	}
	

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += " , Sensors: [ ";
		for(int i=0;i<getSensors().size();i++){
			str += getSensors().get(i).toString();
		}
		str +=" ]";
		return str;

	}
}
