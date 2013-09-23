package org.osgi.snps.base.interfaces;

import java.util.List;

import org.osgi.snps.base.common.SensHybrid;
import org.osgi.snps.base.common.Sensor;

public interface iCompose {

	public SensHybrid compose(List<Sensor> ids);
	
	public boolean destroy(List<String> isd);
	
}
