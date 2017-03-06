package trip.planner

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PrimesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        println("index")
        params.max = Math.min(max ?: 10, 100)
        respond Primes.list(params), model:[primesCount: Primes.count()]
    }

    def show(Primes primes) {
        println("show")
        respond primes
    }

    def create() {
        println("create:")
        params.each {
            println(it)
        }

        Primes somePrime = new Primes(params)
        println("somePrime :"+somePrime.input)
        respond somePrime
    }

    def createPrime() {
        //
    }

    def getPrimes(int number){
        println("getPrimes")
        mylist = []
        for(i==0; i<number.length; i++){
            if(isPrime(i)){
                mylist.add(i)
            }
        }
        mylist.each {
            println(it)
        }
    }

    def isPrime(int number){
        if((number % 2 != 0) && (number % 3 != 0)){
            println(number+" is a prime!")
            return false
        }else {
            println(number+" is a NOT prime!")
            return true
        }
    }

    @Transactional
    def save(Primes primes) {
        if (primes == null) {
            println("primes.input"+primes.input)
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (primes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond primes.errors, view:'create'
            return
        }

        primes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'primes.label', default: 'Primes'), primes.id])
                redirect primes
            }
            '*' { respond primes, [status: CREATED] }
        }
    }

    def edit(Primes primes) {
        respond primes
    }

    @Transactional
    def update(Primes primes) {
        if (primes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (primes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond primes.errors, view:'edit'
            return
        }

        primes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'primes.label', default: 'Primes'), primes.id])
                redirect primes
            }
            '*'{ respond primes, [status: OK] }
        }
    }

    @Transactional
    def delete(Primes primes) {

        if (primes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        primes.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'primes.label', default: 'Primes'), primes.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'primes.label', default: 'Primes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
