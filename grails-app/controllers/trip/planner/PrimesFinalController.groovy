package trip.planner

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PrimesFinalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PrimesFinal.list(params), model:[primesFinalCount: PrimesFinal.count()]
    }

    def show(PrimesFinal primesFinal) {
        respond primesFinal
    }

    def create() {
        log.error("\n\ncreate called! params:${params}")
        respond new PrimesFinal(params)
    }

    @Transactional
    def save(PrimesFinal primesFinal) {
        if (primesFinal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (primesFinal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond primesFinal.errors, view:'create'
            return
        }

        primesFinal.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'primesFinal.label', default: 'PrimesFinal'), primesFinal.id])
                redirect primesFinal
            }
            '*' { respond primesFinal, [status: CREATED] }
        }
    }

    def edit(PrimesFinal primesFinal) {
        respond primesFinal
    }

    @Transactional
    def update(PrimesFinal primesFinal) {
        if (primesFinal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (primesFinal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond primesFinal.errors, view:'edit'
            return
        }

        primesFinal.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'primesFinal.label', default: 'PrimesFinal'), primesFinal.id])
                redirect primesFinal
            }
            '*'{ respond primesFinal, [status: OK] }
        }
    }

    @Transactional
    def delete(PrimesFinal primesFinal) {

        if (primesFinal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        primesFinal.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'primesFinal.label', default: 'PrimesFinal'), primesFinal.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'primesFinal.label', default: 'PrimesFinal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
