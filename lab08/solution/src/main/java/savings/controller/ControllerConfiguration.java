package savings.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.HashSet;

@Configuration
@ComponentScan(basePackageClasses = ControllerConfiguration.class)
@EnableWebMvc
public class ControllerConfiguration {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public FormattingConversionServiceFactoryBean conversionService() {
        FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean =
                new FormattingConversionServiceFactoryBean();
        HashSet<Formatter> formatters = new HashSet<>();
        formatters.add(new MoneyFormatter());
        formattingConversionServiceFactoryBean.setFormatters(formatters);
        return formattingConversionServiceFactoryBean;
    }
}
