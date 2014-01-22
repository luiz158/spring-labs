package savings.controller;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import savings.repository.impl.RepositoryConfiguration;
import savings.service.impl.ServiceConfiguration;

public class SpringLabWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataSourceConfig.class, ServiceConfiguration.class, RepositoryConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ControllerConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }
}
