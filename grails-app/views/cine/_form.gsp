<%@ page import="es.manuel.Cine" %>



<div class="fieldcontain ${hasErrors(bean: cineInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="cine.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="10" required="" value="${cineInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cineInstance, field: 'numeroSalas', 'error')} required">
	<label for="numeroSalas">
		<g:message code="cine.numeroSalas.label" default="Numero Salas" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroSalas" type="number" value="${cineInstance.numeroSalas}" required=""/>
</div>

