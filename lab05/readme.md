Lab 05 - Java-Based Configuration
==
Topics
--
* How to use [annotation-based container configuration][1].

* How to use fully [java-based container configuration][2].

* How to use [annotations to write integration (module) tests][3].

Instructions
--
1. Mark repository classes as [components using proper annotations][4].

2. Enable repository components discovery in `RepositoryConfiguration`.

3. Repeat previous two steps with service layer [components][4] utilizing `ServiceConfiguration`.

4. Reconfigure `PaybackBookKeeperModuleTest` using [annotations][3].
   Use annotation based [unified property management][5] to configure test data source from external properties file.

[Back to index](..)

 [1]: http://docs.spring.io/spring/docs/3.2.5.RELEASE/spring-framework-reference/htmlsingle/#beans-annotation-config
 [2]: http://docs.spring.io/spring/docs/3.2.5.RELEASE/spring-framework-reference/htmlsingle/#beans-java
 [3]: http://docs.spring.io/spring/docs/3.2.5.RELEASE/spring-framework-reference/htmlsingle/#testcontext-ctx-management-javaconfig
 [4]: http://docs.spring.io/spring/docs/3.2.5.RELEASE/spring-framework-reference/htmlsingle/#beans-classpath-scanning
 [5]: http://spring.io/blog/2011/02/15/spring-3-1-m1-unified-property-management/
