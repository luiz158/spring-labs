Lab 07 - Transactions and other Cross-Cutting Concerns
==
Topics
--
* How to do [Aspect-oriented programming][1] with [Spring][2] and [AspectJ][3].

* How to [apply transactions][9] to methods that need them.

Instructions
--
1. [Declare][4] `RepositoryPerformanceMonitor` as an `@Aspect`, define proper [pointcut][5]
    and [around][6] [advice][7].

2. [Enable @AspectJ support][8] in `RepositoryConfiguration`. Than run the tests and see the logs.

3. Use `PaybackBookKeeperTransactionTest` to detect the need for transaction in `PaybackBookKeeperImpl`.

4. Define a read-write and [required][11] [transaction][10] for `PaybackBookKeeperImpl#registerPaybackFor()` method.

3. [Enable transaction supports][10] in `ServiceConfiguration` and run the test again.

[Back to index](..)

 [1]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#aop
 [2]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#aop-ataspectj
 [3]: http://eclipse.org/aspectj/
 [4]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#aop-at-aspectj
 [5]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#aop-pointcuts
 [6]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#aop-ataspectj-around-advice
 [7]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#aop-advice
 [8]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#aop-aspectj-support
 [9]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#transaction
 [10]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#transaction-declarative-annotations
 [11]: http://docs.spring.io/spring/docs/3.2.6.RELEASE/spring-framework-reference/htmlsingle/#tx-propagation

