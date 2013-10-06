Lab 03 - bean lifecycle
==
Topics
--
* How to implement your own bean lifecycle behaviours with `@PostConstruct`, `@PreDestroy`
  and `<context:annotation-config />`.

* How to modify Spring bean definition at runtime with `BeanFactoryPostProcessor`, `PropertySourcesPlaceholderConfigurer`
  and `<context:property-placeholder />`.

* How to apply custom custom configuration behaviours to Spring beans with `@Required`
  and `RequiredAnnotationBeanPostProcessor`.

Instructions
--
1. Enhance `JdbcMerchantRepository` with lifecycle behaviours.

2. Use `JdbcMerchantRepositoryTest` unit test to verify proper implementation.

3. Correct `datasource-testcontext.xml` to make the `PaybackBookKeeperModuleTest` pass.

4. Configure a resolver in `datasource-testcontext.xml` that will replace placeholders in bean definitions with
   defined properties values.

5. See how to use `@Required` by following instructions is `application-context.xml`.
