package it.sella.pinpoint.plugin.weblogic;

import com.navercorp.pinpoint.bootstrap.config.ExcludePathFilter;
import com.navercorp.pinpoint.bootstrap.config.Filter;
import com.navercorp.pinpoint.bootstrap.config.ProfilerConfig;
import com.navercorp.pinpoint.bootstrap.config.SkipFilter;

public class WeblogicConfiguration {

	private final Filter<String> weblogicExcludeUrlFilter;

	public WeblogicConfiguration(ProfilerConfig config) {
		final String weblogicExcludeURL = config.readString("profiler.weblogic.excludeurl", "");

		if (!weblogicExcludeURL.isEmpty()) {
			this.weblogicExcludeUrlFilter = new ExcludePathFilter(weblogicExcludeURL);
		} else {
			this.weblogicExcludeUrlFilter = new SkipFilter<String>();
		}
	}

	public Filter<String> getWeblogicExcludeUrlFilter() {
		return weblogicExcludeUrlFilter;
	}
}
