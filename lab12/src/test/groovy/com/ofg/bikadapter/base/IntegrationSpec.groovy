package com.ofg.bikadapter.base

import lab12.Application
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [Application])
@WebAppConfiguration
@ActiveProfiles(["test"]) //WARNING: cannot use Profiles class here, thought this has to equal to Profiles.TEST, because: http://jira.codehaus.org/browse/GROOVY-3278
@TransactionConfiguration
abstract class IntegrationSpec extends Specification {
}
