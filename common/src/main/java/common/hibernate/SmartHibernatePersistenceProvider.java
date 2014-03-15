package common.hibernate;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.hibernate.service.ServiceRegistry;

/**
 * https://jira.spring.io/browse/SPR-10910
 */
public class SmartHibernatePersistenceProvider extends HibernatePersistenceProvider {

    private String[] annotatedPackages = new String[0];

    /**
     * Overridden to allow specifying annotated packages. Without this, when you use Spring's
     * {@link LocalContainerEntityManagerFactoryBean#setPackagesToScan(String...)} package-level
     * annotations are not picked up.
     */
    @Override
    public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map properties) {
        return new EntityManagerFactoryBuilderImpl(new PersistenceUnitInfoDescriptor(info), properties) {
            @Override
            public Configuration buildHibernateConfiguration(ServiceRegistry serviceRegistry) {
                Configuration configuration = super.buildHibernateConfiguration(serviceRegistry);
                for (String annotatedPackage : annotatedPackages) {
                    configuration.addPackage(annotatedPackage);
                }
                return configuration;
            }
        }.build();
    }

    public SmartHibernatePersistenceProvider withAnnotatedPackages(String...annotatedPackages) {
        this.annotatedPackages = annotatedPackages;
        return this;
    }

}
