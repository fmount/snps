package org.osgi.snps.base.interfaces;

import java.util.List;





public interface iWsnInterface {
	
	//public Document getDocument(String SMLPath);
	
	public boolean setSPlan(String splan);
	
	public String getData(String sensorId,String[] options);
	
	public boolean sendCommand(List<String> sids, String command);
	
	public String registerSensor();

	String helloMsg();
		
}
