Lab 03 - bean lifecycle
==
Topics
--
* How to use more compact XML configuration syntax with [`p`][1] and [`c`][2] namespaces.

* How to implement your own bean [lifecycle][3] behaviours with [annotation configuration][4] utilizing
  [`@PostConstruct`][6], [`@PreDestroy`][6].

* How to modify Spring bean definition at runtime with [`BeanFactoryPostProcessor`][9],
  [`PropertySourcesPlaceholderConfigurer`][10] and [`<context:property-placeholder />`][10].

* How to apply custom custom configuration behaviours to Spring beans with [`@Required`][5] utilizing a
  [`BeanPostProcessor`][7] such as [`RequiredAnnotationBeanPostProcessor`][8].

Instructions
--
1. Enhance `JdbcMerchantRepository` with [lifecycle][3] behaviours.

2. Use `JdbcMerchantRepositoryTest` unit test to verify proper implementation.

3. Correct `datasource-testcontext.xml` to make the `PaybackBookKeeperModuleTest` pass.

4. Configure a [resolver][10] in `datasource-testcontext.xml` that will replace placeholders in bean definitions with
   defined properties values.

5. See how to use [`@Required`][5] by following instructions is `application-context.xml`.

 [1]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-p-namespace
 [2]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-c-namespace
 [3]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-lifecycle
 [4]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-annotation-config
 [5]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-required-annotation
 [6]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-postconstruct-and-predestroy-annotations
 [7]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-extension-bpp
 [8]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-extension-bpp-examples-rabpp
 [9]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-extension-factory-postprocessors
 [10]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-placeholderconfigurer
