Lab 10 - Spring MVC deeper dive
==
Topics
--

* How to validate your forms using validators?

* How to handle exceptions?

* How to format objects on view

* How to handle locale and i18n

Instructions
--

You can run this application using maven jetty plugin command: maven jetty:run

 1. Add validation ([Spring][1], [JSR-303][2])  for PurchaseForm object in PaybackController. Add [error fields][8] on fields inputs in new.jsp view

 2. [Handle][3] RuntimeException in PaybackController - make it return HttpStatus.I_AM_A_TEAPOT (418) and string "Tea, Earl Grey, Hot"

 3. [Add formatting for date field in Payback class in list.jsp view.][4] (tip: use <spring:eval/> to output the field on view)

 4. Add [CookieLocaleResolver][5], [LocaleInterceptor][6] and [MessageSource][7] (basename set to 'WEB-INF/i18n/messages') to WebConfiguration.

[Back to index](..)

 [1]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/validation.html#validator
 [2]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/validation.html#validation-mvc
 [3]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/mvc.html#mvc-exceptionhandlers
 [4]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/validation.html#format-CustomFormatAnnotations
 [5]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/mvc.html#mvc-localeresolver-cookie
 [6]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/mvc.html#mvc-localeresolver-interceptor
 [7]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/beans.html#context-functionality-messagesource
 [8]: http://docs.spring.io/spring/docs/4.0.0.RELEASE/spring-framework-reference/html/view.html#view-jsp-formtaglib-errorstag