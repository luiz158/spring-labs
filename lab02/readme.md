Lab 02 - container basics
==
Topics
--
* How to use Spring to [configure POJOs][1].

* How to use organize Spring configurations effectively.

* How to [create a Spring `ApplicationContext`][3] and [get beans from it][4].

* How to visualize your configuration in Spring IDE.

* [Constructor][7] and [setter][8] based dependency injection.

* [Spring XML][2] configuration syntax.

* How to customize a instantiation logic with a [`FactoryBean`][9].

Instructions
--
1. Define application configuration in `application-context.xml`. Consider [bean naming conventions][5]
   and how the [beans are instantiated][6] using a default constructor.

2. Enable `Spring nature` for the project and configure `Spring Explorer` to visualize created configuration.

3. Define test infrastructure configuration in `datasource-testcontext.xml`.

4. Extend `Spring Explorer` configuration to visualize the complete test configuration.

5. Utilize a [`ClassPathXmlApplicationContext`][3] in provided module test stub to fill in the set up logic.
   [Retrieve][4] beans for test from configured context.

[Back to index](..)

 [1]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-basics
 [2]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-metadata
 [3]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-instantiation
 [4]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-client
 [5]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-beanname
 [6]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-class-ctor
 [7]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-constructor-injection
 [8]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-setter-injection
 [9]: http://docs.spring.io/spring/docs/3.2.4.RELEASE/spring-framework-reference/html/beans.html#beans-factory-extension-factorybean
