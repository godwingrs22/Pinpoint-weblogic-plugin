package it.sella.pinpoint.plugin.weblogic.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.navercorp.pinpoint.bootstrap.config.Filter;
import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;

@TargetMethod(name = "doGet", paramTypes = { "javax.servlet.http.HttpServletRequest" })
public class ServerHandleInterceptor extends AbstractServerHandleInterceptor {

    public ServerHandleInterceptor(TraceContext traceContext, MethodDescriptor descriptor, Filter<String> excludeFilter) {
        super(traceContext, descriptor, excludeFilter);
    }

    @Override
    protected HttpServletRequest getRequest(Object[] args) {
        final Object iRequestObject = args[0];
        if (!(iRequestObject instanceof javax.servlet.http.HttpServletRequest)) {
           return null;
        }
        return (HttpServletRequest) iRequestObject;
    }

}
