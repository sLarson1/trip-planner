package trip.planner


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PrimesFinalInterceptor)
class PrimesFinalInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test primesFinal interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"primesFinal")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
