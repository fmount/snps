package org.osgi.snps.base.common;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class SensHybrid extends ABComponent implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Sensor> sensors;

	@SuppressWarnings("rawtypes")
	public Dictionary dict;

	Subscriber sbs;

	final static String[] topics = { "osgi/testEvent" };

	public enum modes {
		sync, async
	}

	public SensHybrid(String id, String name, String model, String type,
			String state, String description, String nature,
			List<Sensor> sensors) {
		super(id, name, model, type, state, description, nature);
		this.sensors = sensors;

		dict = new Properties();

		//sbs = null;
		
		//addFilter(this.getID());
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

	@Override
	public String getData(BundleContext context, String mode,String[]options,String action) {
		List<Sensor> sensList = this.getSensors();
		Iterator<Sensor> it = sensList.iterator();
		// Recupero i dati per ogni sensore..
		int i = 0;
		switch (modes.valueOf(mode)) {
		case sync:
			List<String> values = new ArrayList<String>();
			while (it.hasNext()) {
				System.out.println("Getting data " + i);
				Sensor s = it.next();
				// s.updateFilter(this.getID());
				values.add(s.getData(context, mode,null,action));// mode: sync or async
				i++;
			}
			/*
			 * Ritorno i dati al chiamante..
			 */
			
			return values.toString();
		case async:
			while (it.hasNext()) {
				System.out.println("Getting data " + i);
				Sensor s = it.next();
				// s.updateFilter(this.getID());
				s.getData(context, mode,options,"none");// mode: sync or async
				i++;
			}
			//addSubs(context);
			return "Return something..";

		default:
			return "Error!!";
		}
	}

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public void addSubs(BundleContext context) {
		if(sbs!=null) return;
		// Creo un subs..
		dict.put(EventConstants.EVENT_TOPIC, topics);
		System.out.println("[SUBSCRIBER " + this.getID()
				+ "]: Create subs on topics: "
				+ dict.get(EventConstants.EVENT_TOPIC).toString());

		// Registro l'EventHandler del subscriber, in modo da poter
		// abilitarlo all'ascolto sul topic..

		
		sbs = new Subscriber(this.getID(),"sensdata",context,null);
		//Registro l'EventHandler del subscriber, in modo da poter abilitarlo all'ascolto sul topic..
		ServiceRegistration  registration = context.registerService(EventHandler.class.getName(), sbs, dict);
	}

	@SuppressWarnings({ "unchecked" })
	public void addFilter(String filter) {
		// System.out.println("Adding filter: "+filter);
		dict.put(EventConstants.EVENT_FILTER, "(sid=" + filter + ")");
	}

	public void removeFilter() {
		// System.out.println("Removing filter: "+dict.get(EventConstants.EVENT_FILTER));
		dict.remove(EventConstants.EVENT_FILTER);
	}

	public void updateFilter(String filter) {
		removeFilter();
		addFilter(filter);
		System.out.println("[Sensor: " + this.getID()
				+ "] -> Filter changed to: " + filter);
	}
}
