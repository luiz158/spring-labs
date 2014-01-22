package savings.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import common.db.EmbeddedDatabaseConfiguration;
import savings.repository.impl.RepositoryConfiguration;
import savings.service.impl.ServiceConfiguration;
import savings.web.impl.WebConfiguration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(
                EmbeddedDatabaseConfiguration.class,
                RepositoryConfiguration.class,
                ServiceConfiguration.class,
                WebConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic dispatcherServlet =
                container.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }

}
