package it.sella.pinpoint.plugin.weblogic;

import static com.navercorp.pinpoint.common.util.VarArgs.va;

import java.security.ProtectionDomain;

import com.navercorp.pinpoint.bootstrap.instrument.InstrumentClass;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentException;
import com.navercorp.pinpoint.bootstrap.instrument.InstrumentMethod;
import com.navercorp.pinpoint.bootstrap.instrument.Instrumentor;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformCallback;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplate;
import com.navercorp.pinpoint.bootstrap.instrument.transformer.TransformTemplateAware;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin;
import com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginSetupContext;

public class WeblogicPlugin implements ProfilerPlugin, TransformTemplateAware {

    private TransformTemplate transformTemplate;
    protected PLogger logger = PLoggerFactory.getLogger(this.getClass());
    
    @Override
    public void setup(ProfilerPluginSetupContext context) {
        context.addApplicationTypeDetector(new WeblogicDetector());
        WeblogicConfiguration config = new WeblogicConfiguration(context.getConfig());
        
        addServerInterceptor(config);
    }

    private void addServerInterceptor(final WeblogicConfiguration config){
      /*  transformTemplate.transform("com.ibm.ws.webcontainer.WSWebContainer",  new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);

                InstrumentMethod handleMethodEditorBuilder = target.getDeclaredMethod("handleRequest", "com.ibm.websphere.servlet.request.IRequest", "com.ibm.websphere.servlet.response.IResponse");
                if (handleMethodEditorBuilder != null) {
                    handleMethodEditorBuilder.addInterceptor("com.navercorp.pinpoint.plugin.websphere.interceptor.ServerHandleInterceptor", va(config.getWebsphereExcludeUrlFilter()));
                    return target.toBytecode();
                }

                return target.toBytecode();
            }
        });*/
        
        transformTemplate.transform("javax.servlet.http.HttpServlet",  new TransformCallback() {
            @Override
            public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {
                InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);
                logger.info("Weblogic Pinpoint Plugin Transform");
                
                InstrumentMethod handleMethodEditorBuilder = target.getDeclaredMethod("doGet", "javax.servlet.http.HttpServletRequest" , "javax.servlet.http.HttpServletResponse");
                if (handleMethodEditorBuilder != null) {
                    handleMethodEditorBuilder.addInterceptor("it.sella.pinpoint.plugin.weblogic.interceptor.ServerHandleInterceptor", va(config.getWeblogicExcludeUrlFilter()));
                    return target.toBytecode();
                }

                return target.toBytecode();
            }
        });
    }

    @Override
    public void setTransformTemplate(TransformTemplate transformTemplate) {
        this.transformTemplate = transformTemplate;
    }
}
