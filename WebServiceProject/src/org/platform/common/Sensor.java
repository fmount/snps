package org.platform.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Sensor extends ABComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<String,List<String>> netParams;
	public Map<String,List<String>> capabilities;
	private Map<String,List<String>>  INPUT_LIST;
	private Map<String,List<String>> OUTPUT_LIST;
	public Map<String, String> position;
	public List<String> referToHybrid;

	//final static String[] topics = { "osgi/testEvent" }; 
	//final static String[] topics = { "osgi/dataEvent" };
	
	
	
	
	/**
	 * Costruttore di base, per sensori interni che non fanno parte della WSN
	 * @param id
	 * @param name
	 * @param model
	 * @param type
	 * @param state
	 * @param description
	 * @param nature
	 * @param IL
	 * @param OL
	 */
	public Sensor(String id, String name, String model, String type,String state,
			String description,String nature, Map<String,List<String>> IL, Map<String,List<String>> OL,
			Map<String, String> position){
		super(id, name, model, type,state, description,nature);
		netParams = new HashMap<String, List<String>>();
		capabilities = new HashMap<String, List<String>>();
		this.position = new HashMap<String, String>();
		INPUT_LIST = IL;
		OUTPUT_LIST = OL;
	/*	sbs=null;
		dict = new Properties();
		addFilter(this.getID());*/
		
		referToHybrid = new ArrayList<String>();
	}

	/**
	 * Costruttore che rispecchia il SensorML di un sensore proveniente dalla WSN
	 * @param id
	 * @param name
	 * @param model
	 * @param description
	 * @param state
	 * @param nature
	 * @param IL
	 * @param OL
	 * @param netParams
	 * @param capabilities
	 * @param position
	 */
	public Sensor(String id, String name, String model,String type,String state,
			String description,String nature,
			Map<String,List<String>> IL, 
			Map<String,List<String>> OL,
			Map<String,List<String>> netParams,
			Map<String,List<String>> capabilities,
			Map<String, String> position
			){
		super(id, name, model,type,state,description,nature);
		this.capabilities=capabilities;
		this.netParams=netParams;
		this.position = position;
		INPUT_LIST = IL;
		OUTPUT_LIST = OL;
		
	/*	dict = new Properties();
		addFilter(this.getID());
		sbs=null; */
		
		referToHybrid = new ArrayList<String>();
	}
	
	/*GETTERS AND SETTERS*/
	
	public List<String> getReferToHybrid() {
		return referToHybrid;
	}

	public void setReferToHybrid(List<String> referToHybrid) {
		this.referToHybrid = referToHybrid;
	}

	public Map<String, String> getPosition() {
		return position;
	}

	public void setPosition(Map<String, String> position) {
		this.position = position;
	}

	public Map<String, List<String>> getNetParams() {
		return netParams;
	}

	public void setNetParams(Map<String, List<String>> netParams) {
		this.netParams = netParams;
	}

	public Map<String, List<String>> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(Map<String, List<String>> capabilities) {
		this.capabilities = capabilities;
	}
	
	public Map<String, List<String>> getINPUT_LIST() {
		return INPUT_LIST;
	}

	public void setINPUT_LIST(Map<String, List<String>> IL) {
		INPUT_LIST = IL;
	}

	public Map<String, List<String>> getOUTPUT_LIST() {
		return OUTPUT_LIST;
	}

	public void setOUTPUT_LIST(Map<String, List<String>> OL) {
		OUTPUT_LIST = OL;
	}
	
	@Override
	public String toString(){
		String str = super.toString();
		str += ",capabilities: "+this.getCapabilities().toString()+
				",net parameters: "+this.getNetParams().toString()+
				",position: "+this.getPosition().toString()+
				",inputList: "+getINPUT_LIST().toString()+
				",outputList: "+getOUTPUT_LIST().toString()+
				",ReferToHybrids: "+getReferToHybrid()+ "}";
		return str;
	}
	
	
}
