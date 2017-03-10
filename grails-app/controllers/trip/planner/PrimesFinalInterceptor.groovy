package trip.planner

/**
 *
 * Suppose n is a whole number, and we want to test it to see if it is prime.
 * First, we take the square root (or the 1/2 power) of n;
 * then we round this number up to the next highest whole number.
 * Call the result m.  We must find all of the following quotients:

 qm = n / m
 q(m-1) = n / (m-1)
 q(m-2) = n / (m-2)
 q(m-3) = n / (m-3)
 . . .
 q3 = n / 3
 q2 = n / 2

 The number n is prime if and only if none of the q's, as derived above, are whole numbers.
 */
class PrimesFinalInterceptor {

    boolean before() {
        println("before called!!!!"+params)
        log.error("request:"+request)
        //log.error("output:"+output)
        if(params?.output){
            log.error("output found!!:"+params?.output+" now look for primes")
            getPrimes(Integer.parseInt(params?.output))
        }else{
            log.error("output NOT found!!:"+params?.output)
        }
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

    def getPrimes(int number){
        println("getPrimes")
        List mylist = []
//        Math.sqrt()
        for(int i=0; i<=number; i++){
            if(isPrime(i)){
                mylist.add(i)
            }
        }
        println("go thru list...")
        mylist.each {
            println(it)
        }
    }

    def isPrime(int number){
        if(number == 1 || number == 3){
            println(number+" is a prime!")
            return true
        }
        else if((number % 2 != 0) && (number % 3 != 0)){
            println(number+" is a prime!")
            return true
        }else {
            println(number+" is a NOT prime!")
            return false
        }
    }
}
