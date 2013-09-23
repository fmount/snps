package org.osgi.snps.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.snps.base.common.ABComponent;
import org.osgi.snps.base.common.SamplingPlan;
import org.osgi.snps.base.common.SensHybrid;
import org.osgi.snps.base.common.Sensor;
import org.osgi.snps.base.common.SimpleData;
import org.osgi.snps.base.interfaces.iCompose;
import org.osgi.snps.base.interfaces.iCoreInterface;
import org.osgi.snps.base.interfaces.iDaoInterface;
import org.osgi.snps.base.interfaces.iDataFlow;
import org.osgi.snps.base.interfaces.iEventPublisherInterface;
import org.osgi.snps.base.interfaces.iGWInterface;
import org.osgi.snps.base.interfaces.iMonitor;
import org.osgi.snps.base.interfaces.iRegistryInterface;
import org.osgi.snps.base.util.*;
import org.osgi.snps.core.dataservice.ServiceData;
import org.w3c.dom.Document;

import smlparser.Parser;

public class CoreServices extends Observable implements iCoreInterface {

	BundleContext context;
	protected Map<String, ABComponent> sensList;
	protected Map<String, String> actions;
	
	
	ServiceData sThread;
	public ArrayList<String> testCmds;

	iGWInterface interpreterService;
	iCompose composerService;
	iDataFlow processorService;
	Parser pservice;
	iEventPublisherInterface pubservice;
	iRegistryInterface registryservice;
	iDaoInterface daoservice;
	iMonitor monitorService;

	@SuppressWarnings("rawtypes")
	ServiceReference serviceRef;

	public CoreServices(BundleContext context) {
		this.context = context;
		sensList = new HashMap<String, ABComponent>();
		actions = new HashMap<String, String>();
		testCmds = new ArrayList<String>();

		//Inserisco un sensore virtuale per testare i servizi..
		Sensor test = new Sensor("test", "test", "test", "DETECTOR", "on", "test",
				"temperature", new HashMap<String, List<String>>(),
				new HashMap<String, List<String>>(),
				new HashMap<String, String>());
		
		List<Sensor> mList = JSonUtil.JSONTOAList(loadData());
		Iterator<Sensor> it = mList.iterator();
		while(it.hasNext()){
			ABComponent s = (ABComponent) it.next();
			sensList.put(s.getID(), s);
		}
		sensList.put("test", test);
		
	}

	public enum commands {
		testSampl, persist
	}

	public enum registrycommands {
		persist, image, check, updateComponent, removeComponent, getSensor, getSDesc,
		getAllSensors, sensType, history, allhistory, getSensInfo, getsenslist, 
		getzonelist, inszone, insbs, rmvzone, rmvbs, getbsinfo, getzoneinfo, 
		insertentry, rmventry, updateentry, getnodelist, detection, detectbydate,
		detectbytime, detectbydateandtime, detectbyzone, getSensorbyzone, getSensorbynode
	}

	public enum interpretercommands {
		splan, check, splanTest, enable, disable, genCmd
	}

	public enum composercommands {
		compose, destroy
	}

	public enum processorcommands {
		push, process, recmeas, rmvmeas
	}

	@Override
	public Map<String, String> getActions() {
		return actions;
	}

	public void setActions(Map<String, String> actions) {
		this.actions = actions;
	}

	
	public String loadData(){
		String jsonSensors = regCall("getAllSensors", 3, "", null, null, "", null);
		return jsonSensors;
	}
	
	
	
	@Override
	public String sayHello() {
		Random random = new Random();
		// Create a number between 0 and 5
		int nextInt = random.nextInt(6);
		switch (nextInt) {
		case 0:
			return "[Core]: Hello, I'm SNPS Core, version: 1.0 [English Style]\n";
		case 1:
			return "[Core]: Hola, soy el nùcleo SNPS [Spanish Style]\n";
		case 2:
			return "[Core]: Hello, Saya SNPS teras! [Malaysian Style]\n";
		case 3:
			return "[Core]: Bonjour, je suis SNPS base [French Style]\n";
		case 4:
			return "[Core]: Hallo, ich bin SNPS Kern [German Style]\n";
		default:
			return "[Core]: Ciao, sono il core di SNPS! [Italian Style]\n";
		}
	}

	/* Metodi di Interazione con il bundle iRegistry */

	@Override
	@SuppressWarnings({ "unchecked"})
	public String regCall(String command, int opcode, String key,
			Document description, ABComponent s, String type, List<String> params) {
		try {
			serviceRef = context.getServiceReference(iRegistryInterface.class
					.getName());
			registryservice = (iRegistryInterface) context
					.getService(serviceRef);

			switch (registrycommands.valueOf(command)) {
			case check:
				return registryservice.sayHello();
			case image:
				serviceRef = context
						.getServiceReference(Parser.class.getName());
				pservice = (Parser) context.getService(serviceRef);
				/*
				 * USING XERCES..
				 * Sensor sens = pservice.DOMparse(description);
				 * 
				 * */
				//I USE XPATH (is the better way..)
				Sensor sens = pservice.parse(description);
				// System.out.println("Putting: "+sens.toString());
				sensList.put(sens.getID(), sens);
				serviceRef = context
						.getServiceReference(iEventPublisherInterface.class
								.getName());
				pubservice = (iEventPublisherInterface) context
						.getService(serviceRef);
				pubservice.sendEvent(sens.getID(), "registration");
				return sens.getID();

			case persist:

				if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
										
					return String.valueOf(registryservice.serializeComponent(
							opcode, key, JSonUtil.DocumentTOJson(description),
							s.getNature(),JSonUtil.HybridToJSON((SensHybrid) s)));
				}else{
					
					try{
					String nodeId = ((Sensor)s).getNetParams().get("base_station").get(0);
					
					String zoneId = ((Sensor)s).getNetParams().get("zone").get(0);
					
					sensList.put(s.getID(), s);
					
					registryservice.insertEntry(opcode, key, nodeId, zoneId);
					
					registryservice.insertBS(opcode, nodeId, 
							((Sensor)s).getNetParams().get("base_station").get(1),
							((Sensor)s).getNetParams().get("base_station").get(2),
							((Sensor)s).getNetParams().get("base_station").get(3));
					
					registryservice.insertZone(opcode, zoneId, 
							((Sensor)s).getNetParams().get("zone").get(1),
							((Sensor)s).getNetParams().get("zone").get(2),
							((Sensor)s).getNetParams().get("zone").get(3),
							((Sensor)s).getNetParams().get("zone").get(4));
					
					return String.valueOf(registryservice.serializeComponent(
							opcode, key, JSonUtil.DocumentTOJson(description),
							s.getNature(),JSonUtil.SensorToJSON((Sensor) s)));
					}catch(Exception e){
						e.printStackTrace();
						return "";
					}
				}

			case updateComponent:
				if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
					return String.valueOf(registryservice.updateComponent(
							opcode, key, JSonUtil.DocumentTOJson(description),
							s.getNature(),JSonUtil.HybridToJSON((SensHybrid) s)));
				}else{
					return String.valueOf(registryservice.updateComponent(
							opcode, key, JSonUtil.DocumentTOJson(description),
							s.getNature(),JSonUtil.SensorToJSON((Sensor) s)));
				}

			case removeComponent:
				
				if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
					return String.valueOf(registryservice.removeComponent(opcode,
							key));
				}
				else{
					
					//Elimino le info ad esso associate..
					
					registryservice.removeEntry(opcode, key);
					registryservice.removeBaseStation(opcode, ((Sensor)s).getNetParams().get("base_station").get(0));
					registryservice.removeZone(opcode, ((Sensor)s).getNetParams().get("zone").get(0));
					
					//Rimuovo il componente..
					
					return String.valueOf(registryservice.removeComponent(opcode,
							key));
				}

			case getSensor:
				
				return registryservice.getImageComponent(opcode, key);
				
			case getSensorbyzone:
				if(params==null){
					System.out.println("Error processing dates!");
					return null;
				}
				String zoneid = params.get(0);
				return JSonUtil.ArrayListToJSON((ArrayList<String>) registryservice.getSensorBYZone(opcode, zoneid));
				
			case getSensorbynode:
				if(params==null){
					System.out.println("Error processing dates!");
					return null;
				}
				String bsid = params.get(0);
				return JSonUtil.ArrayListToJSON((ArrayList<String>) registryservice.getSensorBYNode(opcode, bsid));
				
			case getSDesc:
				
				return registryservice.getComponentDescription(opcode, key);

			case getAllSensors:
				
				List<String> slist = registryservice.getAllImages(opcode);
				
				return JSonUtil.ArrayListToJSON((ArrayList<String>) slist);

			case sensType:
				List<String> list = registryservice.getSensorBYType(opcode,type);
				
				return JSonUtil.ArrayListToJSON((ArrayList<String>) list);
				
			case getSensInfo:
				return JSonUtil
						.HashMapToJSON((HashMap<String, String>) registryservice
								.getSensorPosition(opcode, key));

			case getbsinfo:
				return JSonUtil
						.HashMapToJSON((HashMap<String, String>) registryservice
								.getSensBS(opcode, key));

			case getzoneinfo:
				return JSonUtil
						.HashMapToJSON((HashMap<String, String>) registryservice
								.getSensZone(opcode, key));

			case history:
				List<String> sd = registryservice.history(opcode, key);
				return JSonUtil.ArrayListToJSON((ArrayList<String>) sd);
			case allhistory:
				Map<String,List<String>> datas = registryservice.Allhistory(opcode);
			    return JSonUtil.HLToJSon((HashMap<String, List<String>>) datas);
				
			case detection:
				if(params==null){
					System.out.println("Error processing dates!");
					return null;
				}
				String id_meas = params.get(0);
				return registryservice.getSingleDetect(opcode, id_meas);
			
			case detectbydate:
				if(params==null){
					System.out.println("Error processing dates!");
					return null;
				}
				String initDate = params.get(0);
				String endDate = params.get(1);
				return JSonUtil.ArrayListToJSON((ArrayList<String>) registryservice.getDetectionByDate(
						opcode, key, initDate, endDate));
			case detectbytime:
				if(params==null){
					System.out.println("Error processing dates!");
					return null;
				}
				String date = params.get(0);
				String initTime = params.get(1);
				String endTime = params.get(2);
				return JSonUtil.ArrayListToJSON((ArrayList<String>) registryservice.getDetectionByTime(
						opcode, key, date, initTime, endTime));
				
			case detectbydateandtime:
				if(params==null){
					System.out.println("Error processing dates!");
					return null;
				}
				String date1 = params.get(0);
				String date2 = params.get(1);
				String initTime1 = params.get(2);
				String endTime2 = params.get(3);
				return JSonUtil.ArrayListToJSON((ArrayList<String>) registryservice.getDetectionByDateAndTime(
						opcode, key,date1, date2, initTime1, endTime2));
			case detectbyzone:
				if(params==null){
					System.out.println("Error processing dates!");
					return null;
				}
				String zoneId = params.get(0);
				return registryservice.getDetectionByZone(opcode, zoneId);
			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println("[Core:Alert] -> Command or Parameters not found!");
			//System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/** Metodi di Interazione con l'Interprete **/

	@Override
	@SuppressWarnings({ "unchecked" })
	public String interprCall(String command, SamplingPlan sPlan,
			List<String> sids, String mode) {
		boolean check=true;
		try {
			serviceRef = context.getServiceReference(iGWInterface.class
					.getName());
			interpreterService = (iGWInterface) context.getService(serviceRef);

			switch (interpretercommands.valueOf(command)) {
			case check:
				return interpreterService.sayhello();
			
			case splan:
				List<String> sIds = sPlan.getNodesId();
				if(sensorExist(sIds)){
					/*
					 * Verificare se si tratta di un sensore semplice o di un
					 * sensore Ibrido..
					 *  
					 */
					for(int i=0;i<sIds.size();i++){
						try {
							ABComponent s = sensList.get(sIds.get(i));
							if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
									System.out.println("Hybrid!");
									SensHybrid s1 = (SensHybrid)sensList.get(sIds.get(i));
									sIds.remove(s1.getID());
									List<Sensor> ids = s1.getSensors();
									for(int h=0;h<ids.size();h++){
										sIds.add(ids.get(h).getID());
									}
							}else{
								System.out.println("Simple!");
							}
						}catch(Exception e){
							e.printStackTrace();
							return "Error";
						}
					}
					rmvDpl(sIds);
					System.out.println("LIST: "+sIds.toString());
					return String.valueOf(interpreterService.setSPlan(sPlan));
				}
				else{
					System.out.println("Cannot send sPlan to this sens list!");
					return "Error";
				}
			
			case enable:
				if(sensorExist(sids)){
					//System.out.println("Sensor exist!");
					for(int i=0;i<sids.size();i++){
						if(checkState(sids.get(i), "on")){
							check=false;
						}
					}
					if(check){					
						/*
						 * Verifico se ci sono sensori ibridi, al fine di inviare le richieste
						 * ai singoli sensori che li compongono..
						 * 
						 */
						for(int i=0;i<sids.size();i++){
							try {
								ABComponent s = sensList.get(sids.get(i));
								if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
										//Change sensor's state..	
										getSensList().get(sids.get(i)).setState("on");
										System.out.println("Hybrid!");
										SensHybrid s1 = (SensHybrid)sensList.get(sids.get(i));
										sids.remove(s1.getID());
										List<Sensor> ids = s1.getSensors();
										for(int h=0;h<ids.size();h++){
											sids.add(ids.get(h).getID());
										}
								}else{
									System.out.println("Simple!");
								}
							}catch(Exception e){
								e.printStackTrace();
								return "Error";
							}
						}
						rmvDpl(sids);
						System.out.println("Send command to phisical sensors: "+sids.toString());
						for(int i=0;i<sids.size();i++){
							getSensList().get(sids.get(i)).setState("on");
							//Aggiorno eventuali riferimenti a sensori ibridi..
							/*Sensor s = (Sensor)getSensList().get(sids.get(i));
							if(!(s.getReferToHybrid().isEmpty())){
								updateRef(s.getID(),s.getReferToHybrid(),"on");
							}*/
						}
						return String.valueOf(interpreterService.sendCommand(command,sids, mode));
					}
					else{
						System.out.println("Sensor already active!");
						return "Sensor Already Active";
					}
				}else{
					System.out.println("Error retrieving sensor!");
					return "Error Retrieving Sensor";
				}
			
			case disable:
				if(sensorExist(sids)){
					//System.out.println("Sensor exist!");
					for(int i=0;i<sids.size();i++){
						if(checkState(sids.get(i), "off")){
							check=false;
						}
					}
					if(check){						
						/*
						 * Verifico se ci sono sensori ibridi, al fine di inviare le richieste
						 * ai singoli sensori che li compongono..
						 * 
						 */
						for(int i=0;i<sids.size();i++){
							try {
								ABComponent s = sensList.get(sids.get(i));
								if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
										//Change sensor's state..	
										getSensList().get(sids.get(i)).setState("off");
										System.out.println("Hybrid!");
										SensHybrid s1 = (SensHybrid)sensList.get(sids.get(i));
										sids.remove(s1.getID());
										List<Sensor> ids = s1.getSensors();
										for(int h=0;h<ids.size();h++){
											sids.add(ids.get(h).getID());
										}
								}else{
									System.out.println("Simple!");
								}
							}catch(Exception e){
								e.printStackTrace();
								return "Error";
							}
						}
						rmvDpl(sids);
						System.out.println("Send command to phisical sensors: "+sids.toString());
						for(int i=0;i<sids.size();i++){
							getSensList().get(sids.get(i)).setState("off");
						}
						return String.valueOf(interpreterService.sendCommand(command,sids, mode));
					}
					else{
						System.out.println("Sensor already disabled!");
						return "Sensor already disabled!";
					}
				}else{
					System.out.println("Error retrieving sensor!");
					return "Error retrieving sensor!";
				}
				
			case genCmd:
				if(sensorExist(sids)){
					System.out.println("Sensor exist!");
					for(int i=0;i<sids.size();i++){
						if(checkState(sids.get(i), "off")){
							check=false;
						}
					}
					if(check){						
						/*
						 * Verifico se ci sono sensori ibridi, al fine di inviare le richieste
						 * ai singoli sensori che li compongono..
						 * 
						 */
						for(int i=0;i<sids.size();i++){
							try {
								ABComponent s = sensList.get(sids.get(i));
								if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
										//Change sensor's state..	
										getSensList().get(sids.get(i)).setState("off");
										System.out.println("Hybrid!");
										SensHybrid s1 = (SensHybrid)sensList.get(sids.get(i));
										sids.remove(s1.getID());
										List<Sensor> ids = s1.getSensors();
										for(int h=0;h<ids.size();h++){
											sids.add(ids.get(h).getID());
										}
								}else{
									System.out.println("Simple!");
								}
							}catch(Exception e){
								e.printStackTrace();
								return "Error";
							}
						}
						rmvDpl(sids);
						System.out.println("Send command to phisical sensors: "+sids.toString());
						for(int i=0;i<sids.size();i++){
							getSensList().get(sids.get(i)).setState("off");
						}
						return String.valueOf(interpreterService.sendCommand(command,sids, mode));
					}
					else{
						System.out.println("Sensor already active!");
						return "";
					}
				}else{
					System.out.println("Error retrieving sensor!");
					return "";
				}
				
			default:
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String composerCall(String command, List<String> slist, String mode) {
		try {
			serviceRef = context
					.getServiceReference(iCompose.class.getName());
			composerService = (iCompose) context.getService(serviceRef);

			switch (composercommands.valueOf(command)) {
			case compose:
				// Phase 1: Look for sensor existance..[Core]
				if (sensorExist(slist)) {
					List<Sensor> toCompose = new ArrayList<Sensor>();
					Iterator<String> it = slist.iterator();
					while (it.hasNext()) {
						toCompose.add((Sensor) getSensList().get(it.next()));
					}
					SensHybrid s = composerService.compose(toCompose);
					sensList.put(s.getID(), s);
					
					//Aggiorno il riferimento ai sensori..
					for(int i=0;i<slist.size();i++){
							Sensor s3 = (Sensor)getSensList().get(slist.get(i));
							s3.getReferToHybrid().add(s.getID());
							getSensList().put(s3.getID(), s3);
					}
					
					return s.getID();
				} else {
					System.out.println("Error retrieving sensors!!");
					return "Error retrieving sensors!";
				}
			case destroy:
				return "todo";
			default:
				return "Service not found!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error!";
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getData(String sId, String mode,String action) {
		
			try {
				serviceRef = context.getServiceReference(iDataFlow.class.getName());
				processorService = (iDataFlow) context.getService(serviceRef);
			ABComponent s = sensList.get(sId);
			if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
				System.out.println("Hybrid!");
				
				String[] options = {s.getID()+"#"+Util.IdGenerator().replace("-","")};
				
				processorService.Accumulate(options[0],new ArrayBlockingQueue<String>(
						((SensHybrid) s).getSensors().size()),context,action);
				
				
				
				return ((SensHybrid) s).getData(context, mode,options,action);
			} else {
				System.out.println("Selected Simple sensor: "+s.getID());
				return ((Sensor) s).getData(context, mode,null,action);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error retrieving sensor!");
			return "Error!";
		}
	}

	
	@Override
	public String processorCall(String command, SimpleData sd, String mode,
			String[] options) {
		try {
			switch (processorcommands.valueOf(command)) {
			case push:
				
				System.out.println("[PUSH] -> Core Event generated..waiting for Subs");
				return String.valueOf(processorService.pushData(sd, mode,context));
			case process:
				return "";
			default:
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";

		}
	}


	/* UTILITY METHODS */

	@Override
	public Map<String, ABComponent> getSensList() {
		return sensList;
	}

	@Override
	public boolean addToSensList(String key, Sensor value) {
		try {
			sensList.put(key, value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean addToActionList(String key, String value) {
		try {
			actions.put(key, value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Controllo se il sensore esiste in memoria e, successivamente nello strato
	 * di persistenza (nel caso in cui il primo tentativo fallisce)..
	 */

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean sensorExist(List<String> sId) {
		Iterator<String> it = sId.iterator();
		System.out.println(sId.toString());
		while (it.hasNext()) {
			String cur = it.next();
			// Controllo di primo livello (in memoria)
			if (!(getSensList().containsKey(cur))) {
				// Se non e' presente in memoria, cerco sul dao..
				ServiceReference reference = context
						.getServiceReference(iDaoInterface.class.getName());
				iDaoInterface service = (iDaoInterface) context
						.getService(reference);
				String s = service.getImageComponent(3, cur);
				if (s == null)
					return false;
			}
		}
		return true;
	}

	// true = se NON posso effettuare l'operazione di modifica, false
	// altrimenti..
	public boolean checkState(String sid, String state) {
		try {
			ABComponent s = getSensList().get(sid);
			if (s.getClass().getSimpleName().equalsIgnoreCase("senshybrid")) {
					System.out.println("Hybrid!");
					SensHybrid s1 = (SensHybrid)getSensList().get(sid);
					System.out.println(s1.getState()+" - "+state);
					if (s1.getState().equalsIgnoreCase(state))
						return true;
					return false;
			}else{
				System.out.println("Simple!");
				Sensor s2 = (Sensor) getSensList().get(sid);
				System.out.println(s2.getState()+" - "+state);
				if (s2.getState().equalsIgnoreCase(state))
					return true;
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public ArrayList<String> getTestCmd() {
		return testCmds;
	}

	@Override
	public boolean addTestCmd(String key) {
		try {
			testCmds.add(key);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void rmvDpl(List<String> sIds){
		for (int i = 0 ; i < sIds.size() ; i++){
			String curr=sIds.get(i);
			for (int j=i+1 ; j< sIds.size() ; j++){
				String tmp=sIds.get(j);
				if (curr.equals(tmp)){
					sIds.remove(j);
					j--;
				}
			}
		}
	}
	
	public void updateRef(String sid,List<String> referTo, String state){
		
		for(int i=0;i<referTo.size();i++){
			SensHybrid s = (SensHybrid)getSensList().get(referTo.get(i));
			List<Sensor> sl = s.getSensors();
			for(int j=0;j<sl.size();j++){
				if(sl.get(j).getID().equalsIgnoreCase(sid)){
					System.out.println("Update reference..");
					Sensor s1 = sl.get(j);
					sl.remove(s1);
					s1.setState(state);
					sl.add(s1);
				}
			}
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> getActuators(){
		List<String> actList = new ArrayList<String>();
		Iterator it = getSensList().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        ABComponent a = getSensList().get(pairs.getKey());
		        if(a.getType().equalsIgnoreCase("ACTUATOR"))
		        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        	actList.add(a.getID());
		    }
		return actList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean monitorCall(String action, String param) {
		try {
			if(action.equals("stop")){
				serviceRef = context.getServiceReference(iMonitor.class
						.getName());
				monitorService = (iMonitor) context.getService(serviceRef);
				return monitorService.stop(param);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	
}
