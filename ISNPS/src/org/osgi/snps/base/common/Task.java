package org.osgi.snps.base.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.snps.base.common.ABComponent;
import org.osgi.snps.base.interfaces.iCoreInterface;
import org.osgi.snps.base.interfaces.iWsnInterface;
import org.osgi.snps.base.util.Util;

class Task extends TimerTask {

	// Sensor s;
	List<String> sids;
	BundleContext context;
	String mode;
	String action;
	iWsnInterface wsnservice;
	iCoreInterface coreservice;
	Date end;
	Timer timer;

	public Task(List<String> sids, BundleContext context, Timer timer, Date end) {
		this.sids = sids;
		this.context = context;
		this.timer = timer;
		this.end = end;

	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() {
		ServiceReference reference;
		reference = context.getServiceReference(iWsnInterface.class.getName());
		wsnservice = (iWsnInterface) context.getService(reference);
		reference = context.getServiceReference(iCoreInterface.class.getName());
		coreservice = (iCoreInterface) context.getService(reference);
		String data = "";

		String today = Util.whatDayIsToday() + " " + Util.whatTimeIsIt();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		try {
			Date datetoday = formatter.parse(today);

			if ((end.getTime() - datetoday.getTime()) >= 0) {
				for (String id : sids) {
					System.out.println("START GET DATA REQUEST");
					data = coreservice.getData(id, "sync", "");
					wsnservice.sendDataToMiddleware(data);
					System.out.println("DATA TO MW: " + data);
				}
			} else {
				timer.cancel();
				timer.purge();
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
