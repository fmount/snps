package org.osgi.remote.wsn;


import java.util.Hashtable;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
//import org.osgi.rxtx.arduino.SerialTest;
import org.osgi.snps.base.interfaces.iWsnInterface;

import ch.ethz.iks.r_osgi.RemoteOSGiService;


public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(BundleContext context) throws Exception {		
		//FASE DI ESPORTAZIONE DEI SERVIZI MESSI A DISPOSIZIONE DALLA WSN
		 final Hashtable properties = new Hashtable();
	     properties.put(RemoteOSGiService.R_OSGi_REGISTRATION, Boolean.TRUE);
	     
	     //context.registerService(iWsnInterface.class.getName(), new WSNService(context,new SerialTest()), properties);
	     context.registerService(iWsnInterface.class.getName(), new WSNService(context), properties);
	     System.out.println("[WSN GATEWAY:Info] -> State: ACTIVE");
	    
	     
	    /* SerialTest main = new SerialTest();
	     main.initialize();
	     main.getPhysicData(); */
			
		/*	Thread t=new Thread() {
					public void run() {
							//the following line will keep this app alive for 1000 seconds,
							//waiting for events to occur and responding to them (printing incoming messages to console).
						try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
					}
			};
			
			t.start();
			System.out.println("Started");*/
		
	     
	     
	     
	     
	     
	     
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
