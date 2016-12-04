package it.sella.pinpoint.plugin.weblogic;

import static com.navercorp.pinpoint.common.trace.ServiceTypeProperty.*;

import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeFactory;

public final class WeblogicConstants {
    private WeblogicConstants() {
    }

    public static final String TYPE_NAME = "WEBLOGIC";

    public static final ServiceType WEBLOGIC = ServiceTypeFactory.of(1920, "WEBLOGIC", RECORD_STATISTICS);
    public static final ServiceType WEBLOGIC_METHOD = ServiceTypeFactory.of(1921, "WEBLOGIC_METHOD");

    public static final String METADATA_TRACE = "trace";
    public static final String METADATA_ASYNC = "async";
    public static final String METADATA_ASYNC_TRACE_ID = "asyncTraceId";

    public static final String ATTRIBUTE_PINPOINT_TRACE = "PINPOINT_TRACE";
}
