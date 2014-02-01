Lab 08 - Introduction to Spring MVC
==
Topics
--

* How to do [MVC server side integration test][5] without deploying to a container.

* What is [DispacherServlet][1] and how to initialize it.

* How to implement an [MVC controller][2] with Spring.

* How to integrate basic server side [view technologies like JSP][3] with Spring.

* How to implement simple [RESTful web service][4] with Spring.

Instructions
--
 1. [Setup][15] and use `PaybackControllerTest` to validate your solutions to the first part about classical Spring MVC.

 2. [Configure][6] classical Spring MVC and [customize] it by properly annotating `WebConfiguration` class and
    enabling serving [static resources][8] and [JSP][3]s.

 3. [Mark][9] and [map][10] `PaybackController` and its methods properly. Also [customize data binding][11]
    for the purpose of using Joda Money class.

 4. Now that `PaybackControllerTest` is passing, analyze `WebAppInitializer` and deploy the application
    on your favorite servlet container to see it running.

 5. Use `PaybackRestControllerTest` to validate your solutions to the second part about RESTful MVC.

 6. Most of configuration is done, but we still need to add a [JSON message converter][7] to `WebConfiguration`
    to tell Spring how we want request JSON parsed and response JSON written based on entities.

 7. [Now][10] you [only need][12] to add [just a few][13] simple [annotations][14] in `PaybackRestController`
    and voilà! - a simple RESTful web service.

 8. Now that `PaybackRestControllerTest` you can deploy the application again and try to interact with it via REST.

[Back to index](..)

 [1]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-servlet
 [2]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-controller
 [3]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#view-jsp-resolver
 [4]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#new-feature-rest-support
 [5]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#spring-mvc-test-server
 [6]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-config-enable
 [7]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-config-customize
 [8]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-config-static-resources
 [9]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-controller
 [10]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestmapping
 [11]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-initbinder
 [12]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html
 [13]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-responsebody
 [14]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestbody
 [15]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#spring-mvc-test-server-setup-options
