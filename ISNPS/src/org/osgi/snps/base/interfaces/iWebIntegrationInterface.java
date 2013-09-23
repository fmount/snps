package org.osgi.snps.base.interfaces;

import java.util.List;


public interface iWebIntegrationInterface {

	
	String sayhello();
	
	String sendCommand(String command, List<String> sids, String mode);
	
	String setSPlan(String splan);
	
	String stopSPlan(String idPlan);
		
	String buildVirtualSensor(List<String> sensors,String schema);
	
	String getData(String sId, String mode, String action);

	String DiscoverySensorsAndMeasurements(String command, String sid,
			String type, String id_detection, String initDate, String endDate,
			String initTime, String endTime, String zoneid, String bsid);
	
}
