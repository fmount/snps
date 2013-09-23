package org.osgi.remote.wsn;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
//import java.util.Random;

import org.osgi.demo.actuator.HttpExecutor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.rxtx.arduino.SerialTest;
import org.osgi.snps.base.common.SimpleData;
import org.osgi.snps.base.interfaces.*;
import org.osgi.snps.base.util.JSonUtil;
import org.osgi.snps.base.util.Util;
import org.w3c.dom.Document;

import smlparser.Parser;

import ch.ethz.iks.r_osgi.RemoteOSGiException;
import ch.ethz.iks.r_osgi.RemoteOSGiService;
import ch.ethz.iks.r_osgi.RemoteServiceReference;
import ch.ethz.iks.r_osgi.URI;

public class WSNService implements iWsnInterface{

	BundleContext context;
	Parser pservice;
	iGWInterface igwservice;
	SerialTest t;
	
	public WSNService(BundleContext context,SerialTest s){
		this.context = context;
		this.t = s;		
	}
	
	/**
	 * Messaggio di benvenuto per ogni bundle, al fine di testare la comunicazione (sia remota che locale)
	 */
	@Override
	public String helloMsg() {
		String str = "[WSN GATEWAY:Info] -> I'm the WSN Gateway!!";
		return str;
	}

	
	@Override
	public boolean setSPlan(String plan) {
		/* 
		 * Setto il piano di campionamento per il sensore dialogando con il sensore 
		 * fisico..una volta che il comando è andato a buon fine, invocare il metodo
		 * push(String data) per inviare dati al middleware..
		 * N.B. Eseguire controlli di priorità rispetto ai parametri settati nel piano
		 * di campionamento (range ed intervalli).. 
		 * */
		return true;
	}

	@Override
	public String getData(String sensorId,String[]options) {
		/**
		 * XXX: Qui sta il codice per recuperare il dato dal sensore
		 * fisico, per poi wrapparlo nella classe SimpleData (convertita
		 * in JSON attraverso le opportune librerie) e ritornare il tutto.
		 * **/
		
		
		
	/*	SerialTest main = new SerialTest();
		main.initialize();
		
		Thread t=new Thread() {
				public void run() {
						//the following line will keep this app alive for 1000 seconds,
						//waiting for events to occur and responding to them (printing incoming messages to console).
					try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
				}
		};
		
		t.start();
		System.out.println("Started");*/
		
		//Creo un dato fittizio..
		/*SimpleData sd = new SimpleData(sensorId,
				String.valueOf((Math.random() * 10)), "",
				System.currentTimeMillis()); */
				
		
		/*RETRIEVE DATA FROM ARDUINO...*/
		SimpleData sd = new SimpleData(sensorId, t.getPhysicData(sensorId),"",Util.whatDayIsToday(),
				Util.whatTimeIsIt());
		
		sd.set_id_meas(Util.IdGenerator().replace("-", ""));
		if(options!=null){
			sd.setRef(options[0]);
		}
		//System.out.println(JSonUtil.SimpleDataToJSON(sd));

		//Lo converto in JSon..
		return JSonUtil.SimpleDataToJSON(sd);
	}

	@Override
	public boolean sendCommand(List<String> sids, String command) {
		
		
		/**
		 * XXX: Qui sta il codice per propagare il comando verso la 
		 * wsn e ritornare true or false al chiamante in funzione dello
		 * stato dell'operazione (success / failure)
		 * **/
		
		//Selezionare una porta random [0-8]
		//Random random = new Random();
		// Create a number between 0 and 8
		//int nextInt = random.nextInt(8);
		
		//STUPIDAMENTE...
		HttpExecutor.exec(command+":"+0);
		System.out.println("Sended command "+command+":"+1+" to: "+sids.toString());
		return true;
	}
	
	
	/**
	 * Metodo utilizzato ogni qual volta c'è necessità di inviare un dato al 
	 * middleware: un piano di campionamento, ad esempio, deve far uso di questo
	 * metodo perché periodicamente deve inviare (prendendo l'iniziativa) un dato
	 * al middleware, richiamando l'apposito servizio messo a disposizione dal 
	 * gateway.
	 * -----------------------------------------------------------------------
	 * -----------------------------------------------------------------------
	 * N.B.
	 * Si suppone che il parametro "data" sia già in formato JSON, utilizzando
	 * l'apposito metodo di conversione che si trova all'interno della JSONUtil.
	 * -----------------------------------------------------------------------
	 * -----------------------------------------------------------------------
	 * 
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean sendDataToMiddleware(String data){
		ServiceReference serviceRef = context
				.getServiceReference(RemoteOSGiService.class.getName());
		if (serviceRef == null) {
			System.out.println("R-OSGi service not found!");
		} else {
			final RemoteOSGiService remote = (RemoteOSGiService) context
					.getService(serviceRef);
			URI uri = new URI("r-osgi://127.0.0.1:9279");
			try {
				remote.connect(uri);

				final RemoteServiceReference[] references = remote
						.getRemoteServiceReferences(uri,
								iGWInterface.class.getName(), null);
				if (references == null) {
					System.out.println("[WSN to MDW] -> Service not found!");
				} else {
					final iGWInterface igw = (iGWInterface) remote
							.getRemoteService(references[0]);
					return igw.push(data,null);
				}
			} catch (RemoteOSGiException e) {
				// e.printStackTrace();
				System.out.println("No NetworkChannelFactory for r-osgi found");
			} catch (IOException e) {
				//e.printStackTrace();
				 System.out.println("No NetworkChannelFactory for r-osgi found");
			} finally {
				// bundleContext.ungetService(serviceRef);
			}
		}
		return false;		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Document getDocument(String SMLPath){
		ServiceReference serviceRef = context
				.getServiceReference(Parser.class.getName());
		pservice = (Parser) context.getService(serviceRef);
		//return pservice.getDOMDocument(SMLPath);
		return pservice.getDocument(SMLPath);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String getSId(Document doc){
		ServiceReference serviceRef = context
				.getServiceReference(Parser.class.getName());
		pservice = (Parser) context.getService(serviceRef);
		//return pservice.DOMSID(doc);
		try{
		
			XPath xpath = XPathFactory.newInstance().newXPath();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder =  factory.newDocumentBuilder();
			return pservice.getId(xpath, doc);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	@Override
	public String registerSensor(){
		Document doc = getDocument("SensorML.xml");
		String ns = getSId(doc);
		
		igwservice = setRemoteConnection();
		return igwservice.registerSensor(ns, doc);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public iGWInterface setRemoteConnection(){
		ServiceReference serviceRef = context
				.getServiceReference(RemoteOSGiService.class.getName());
		if (serviceRef == null) {
			System.out.println("R-OSGi service not found!");
		} else {
			final RemoteOSGiService remote = (RemoteOSGiService) context
					.getService(serviceRef);
			URI uri = new URI("r-osgi://127.0.0.1:9278");
			try {
				remote.connect(uri);

				final RemoteServiceReference[] references = remote
						.getRemoteServiceReferences(uri,
								iGWInterface.class.getName(), null);
				if (references == null) {
					System.out.println("[WSN to MDW] -> Service not found!");
				} else {
					igwservice = (iGWInterface) remote.getRemoteService(references[0]);
					//return igw.registerSensor("t1", doc);
					return igwservice;
				}
			} catch (RemoteOSGiException e) {
				 e.printStackTrace();
				System.out.println("No NetworkChannelFactory for r-osgi found");
			} catch (IOException e) {
				e.printStackTrace();
				 System.out.println("No NetworkChannelFactory for r-osgi found");
			} finally {
				// bundleContext.ungetService(serviceRef);
			}
		}
		return null;
		
	}

}
