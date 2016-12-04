package it.sella.pinpoint.plugin.weblogic;

import com.navercorp.pinpoint.common.trace.TraceMetadataProvider;
import com.navercorp.pinpoint.common.trace.TraceMetadataSetupContext;

public class WeblogicTypeProvider implements TraceMetadataProvider {

	@Override
	public void setup(TraceMetadataSetupContext context) {
		context.addServiceType(WeblogicConstants.WEBLOGIC);
		context.addServiceType(WeblogicConstants.WEBLOGIC_METHOD);
	}
}
