Lab 08 - Spring MVC
==
Topics
--

* [What is DispacherServlet][1] and how to initizalize it

* How to [implement a controller in Spring][2]

* How to [define basic JSP view resolver][3]

* How to [return JSON directly from controller method][4]

* How to [test mappings in MVC][5]

Instructions
--
 1. [Enable WebMVC][6] on ControllerConfiguration and [make #internalResourceViewResolver() return Spring bean][7]

 2. [Map SpringLabsController as controller][8] and [map it to the url '/springlabs'][9]

 3. [Map SpringLabsController#getForm() to url '/springlabs/purchase/new' and request method GET][9]

 4. [Map SpringLabsController#postForm() to url '/springlabs/purchase/new' and request method POST][9] and [bind model attribute to parameter method][10]

 5. [Map SpringLabsController#getMerchantByNumber(merchantNumber) to url '/springlabs/merchant' and request method GET ][9]and [bind request param merchantNumber to method parameter][11] and [return object as JSON][4].

 6. [Map SpringLabsController#getAccountByCreditCard(creditCard) to url '/springlabs/account/{creditCard}' and request method GET ][9]and [bind path variable {creditCard} to method parameter][12] and [return object as JSON][4]



[Back to index](..)

 [1]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#view-jsp-resolver
 [2]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-controller
 [3]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#view-jsp-resolver
 [4]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-responsebody
 [5]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#spring-mvc-test-framework
 [6]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-config-enable
 [7]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#beans-java-bean-annotation
 [8]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-controller
 [9]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestmapping
 [10]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-modelattrib-method-args
 [11]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestparam
 [12]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#mvc-ann-requestmapping-uri-templates

