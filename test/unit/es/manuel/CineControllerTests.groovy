package es.manuel



import org.junit.*
import grails.test.mixin.*

@TestFor(CineController)
@Mock(Cine)
class CineControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		params['nombre'] = 'Manuel'
		params['numeroSalas'] = 10
	
    }

    void testIndex() {
        controller.index()
        assert "/cine/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cineInstanceList.size() == 0
        assert model.cineInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cineInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cineInstance != null
        assert view == '/cine/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cine/show/1'
        assert controller.flash.message != null
        assert Cine.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cine/list'

        populateValidParams(params)
        def cine = new Cine(params)

        assert cine.save() != null

        params.id = cine.id

        def model = controller.show()

        assert model.cineInstance == cine
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cine/list'

        populateValidParams(params)
        def cine = new Cine(params)

        assert cine.save() != null

        params.id = cine.id

        def model = controller.edit()

        assert model.cineInstance == cine
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cine/list'

        response.reset()

        populateValidParams(params)
        def cine = new Cine(params)

        assert cine.save() != null

        // test invalid parameters in update
        params.id = cine.id
        //TODO: add invalid values to params object
		params['nombre'] = 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'

        controller.update()

        assert view == "/cine/edit"
        assert model.cineInstance != null

        cine.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cine/show/$cine.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cine.clearErrors()

        populateValidParams(params)
        params.id = cine.id
        params.version = -1
        controller.update()

        assert view == "/cine/edit"
        assert model.cineInstance != null
        assert model.cineInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cine/list'

        response.reset()

        populateValidParams(params)
        def cine = new Cine(params)

        assert cine.save() != null
        assert Cine.count() == 1

        params.id = cine.id

        controller.delete()

        assert Cine.count() == 0
        assert Cine.get(cine.id) == null
        assert response.redirectedUrl == '/cine/list'
    }
}
