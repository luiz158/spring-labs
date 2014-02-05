package eu.solidcraft.starter.conf.init

import org.springframework.core.annotation.Order
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.XmlWebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer

import javax.servlet.Filter

@Order(1)
class WebAppInitializer extends AbstractDispatcherServletInitializer {

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return createWebApplicationContext("classpath:spring/main.ioc.xml")
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        return createWebApplicationContext("classpath:spring/webmvc.ioc.xml")
    }

    private WebApplicationContext createWebApplicationContext(String configLocation) {
        XmlWebApplicationContext webApplicationContext = new XmlWebApplicationContext()
        webApplicationContext.setConfigLocation(configLocation)
        return webApplicationContext
    }

    @Override
    protected String[] getServletMappings() {
        return ["/*"] as String[]
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return [ characterEncodingFilter ] as Filter[]
    }
}
