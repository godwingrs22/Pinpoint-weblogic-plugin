package it.sella.pinpoint.plugin.weblogic;

import com.navercorp.pinpoint.bootstrap.plugin.ApplicationTypeDetector;
import com.navercorp.pinpoint.bootstrap.resolver.ConditionProvider;
import com.navercorp.pinpoint.common.trace.ServiceType;

public class WeblogicDetector implements ApplicationTypeDetector {

    private static final String REQUIRED_MAIN_CLASS = "weblogic.Server";

    @Override
    public ServiceType getApplicationType() {
        return WeblogicConstants.WEBLOGIC;
    }

    @Override
    public boolean detect(ConditionProvider provider) {
        return provider.checkMainClass(REQUIRED_MAIN_CLASS);
    }
}
