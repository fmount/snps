package org.osgi.snps.base.common;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.snps.base.common.ABComponent;
import org.osgi.snps.base.interfaces.iCoreInterface;








import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Timer;
import java.text.DateFormat;
import java.text.ParseException;

public class ServiceData {

	Thread t;
	BundleContext context;
	// Sensor s;
	List<String> sids;

	public ServiceData(BundleContext context, List<String> sids, String mode,
			String start, int time, String finish, String action) {
		this.context = context;
		this.sids = sids;
		System.out.println("Starting Service Data Thread..");
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = formatter.parse(start);
			endDate = formatter.parse(finish);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Timer timer = new Timer();
		timer.schedule(new Task(sids, context, timer, endDate), startDate, time);
		System.out.println(startDate.toString());
	}
}
