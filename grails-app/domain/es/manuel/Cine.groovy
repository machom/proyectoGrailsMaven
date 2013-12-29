package es.manuel

class Cine {

	String nombre
	Integer numeroSalas

	static constraints = {
		nombre(blank:false, size: 1..10)
	}
}
