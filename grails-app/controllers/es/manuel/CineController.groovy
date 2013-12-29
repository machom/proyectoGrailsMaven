package es.manuel

import org.springframework.dao.DataIntegrityViolationException

class CineController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cineInstanceList: Cine.list(params), cineInstanceTotal: Cine.count()]
    }

    def create() {
        [cineInstance: new Cine(params)]
    }

    def save() {
        def cineInstance = new Cine(params)
        if (!cineInstance.save(flush: true)) {
            render(view: "create", model: [cineInstance: cineInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cine.label', default: 'Cine'), cineInstance.id])
        redirect(action: "show", id: cineInstance.id)
    }

    def show(Long id) {
        def cineInstance = Cine.get(id)
        if (!cineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cine.label', default: 'Cine'), id])
            redirect(action: "list")
            return
        }

        [cineInstance: cineInstance]
    }

    def edit(Long id) {
        def cineInstance = Cine.get(id)
        if (!cineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cine.label', default: 'Cine'), id])
            redirect(action: "list")
            return
        }

        [cineInstance: cineInstance]
    }

    def update(Long id, Long version) {
        def cineInstance = Cine.get(id)
        if (!cineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cine.label', default: 'Cine'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cineInstance.version > version) {
                cineInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cine.label', default: 'Cine')] as Object[],
                          "Another user has updated this Cine while you were editing")
                render(view: "edit", model: [cineInstance: cineInstance])
                return
            }
        }

        cineInstance.properties = params

        if (!cineInstance.save(flush: true)) {
            render(view: "edit", model: [cineInstance: cineInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cine.label', default: 'Cine'), cineInstance.id])
        redirect(action: "show", id: cineInstance.id)
    }

    def delete(Long id) {
        def cineInstance = Cine.get(id)
        if (!cineInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cine.label', default: 'Cine'), id])
            redirect(action: "list")
            return
        }

        try {
            cineInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cine.label', default: 'Cine'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cine.label', default: 'Cine'), id])
            redirect(action: "show", id: id)
        }
    }
}
