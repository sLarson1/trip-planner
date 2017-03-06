package trip.planner


class PrimesFinalInterceptor {

    boolean before() {
        println("before called!!!!"+params)
        log.error("request:"+request)
        //log.error("output:"+output)
        log.error("output???:"+params?.output)
        params.each {
            log.error("it:"+it)

/*            if("output".equalsIgnoreCase(it.)){
                log.error("\n\nfound it!!\n")

            }*/
        }
        log.error("\n\n")
        true
    }

    boolean after() {
        println("after!!!!")
        true
    }

    void afterView() {
        println("afterView!!!!")
        // no-op
    }
}
