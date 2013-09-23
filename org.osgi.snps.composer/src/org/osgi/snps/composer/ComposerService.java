package org.osgi.snps.composer;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.snps.base.common.*;
import org.osgi.snps.base.interfaces.iCompose;
import org.osgi.snps.base.interfaces.iEventPublisherInterface;
import org.osgi.snps.base.interfaces.iRegistryInterface;
import org.osgi.framework.ServiceReference;




public class ComposerService implements iCompose{

	BundleContext context;
	iRegistryInterface registryservice;
	iEventPublisherInterface pubservice;
	
	public ComposerService(BundleContext context){
		this.context = context;
		
	}
	
	
	//@SuppressWarnings({ "rawtypes", "unchecked" })
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SensHybrid compose(List<Sensor> slist) {
		SensHybrid s;
		try {
			
			s = new SensHybrid(genId(slist), genName(slist), "",genType(slist),
					genState(slist),genDesc(slist),genNature(slist), slist);
			
			
			
			//TODO: DECOMMENTARE PER DIALOGARE CON IL REGISTRY E SALVARE IL SENSORE VIRTUALE..
			
		/*	ServiceReference serviceRef = context
					.getServiceReference("org.osgi.snps.iregistry.RegisterService");
			registryservice = (iRegistryInterface) context
					.getService(serviceRef);
			
			registryservice.serializeComponent(3, sid, 
					SensorMLParser.genDescription(slist, sid, "aaa",cname,
							"DETECTOR", "ACTIVE"), s); */
			ServiceReference reference = context.getServiceReference(iEventPublisherInterface.class.getName());
			pubservice = (iEventPublisherInterface) context.getService(reference);
			pubservice.sendEvent(s.getID(),"registration");
			/*EventPublisher publisher =	getPublisher(context);
			publisher.sendEvent(s.getID(),"registration");*/
			
			
		} catch (Exception e) {
				e.printStackTrace();
				return null;
		}
		return s;
	}
	
	
	/* UTILITY DI COMPOSIZIONE */

	public String genId(List<Sensor> slist){
		String id="";
		for(int i=0;i<slist.size();i++){
			if(i==0)
				id +=slist.get(i).getID();
			else
				id +="_"+slist.get(i).getID();
		}
		return id;
	}
	
	public String genName(List<Sensor> slist){
		String name="";
		for(int i=0;i<slist.size();i++){
			if(i==0)
				name +="A Virtual Sensor composed by: "+slist.get(i).getID();
			else
				name +=","+slist.get(i).getID();
		}
		return name;
	}
	
	public String genType(List<Sensor> slist){
		String type="DETECTOR";
		String toret="";
		for(int i=0;i<slist.size();i++){
			if(slist.get(i).getType().equalsIgnoreCase(type)){
				toret="DETECTOR";
			}
			else toret="ACTUATOR";
		}
		return toret;
	}
	
	public String genDesc(List<Sensor> slist){
		String desc="";
		for(int i=0;i<slist.size();i++){
			if(i==0)
				desc +="Virtualization with these features: "+"+ "+slist.get(i).getDescription();
			else
				desc +=""+"+ "+slist.get(i).getDescription();
		}
		return desc;
	}
	
	public String genNature(List<Sensor> slist){
		return "todo";
	}
	
	
	/**
	 * Regola: se almeno uno dei sensori che lo compongono è nello stato off, di 
	 * default anche il sensore ibrido si troverà nello stato off; on altrimenti.
	 * @param slist
	 * @return
	 */
	public String genState(List<Sensor> slist){
		String state="on";
		for(int i=0;i<slist.size();i++){
			if(slist.get(i).getState().equalsIgnoreCase("off"))
				state = "off";
		}
		return state;
	}
	
	@Override
	public boolean destroy(List<String> isd) {
		// TODO Auto-generated method stub
		return false;
	}
}
