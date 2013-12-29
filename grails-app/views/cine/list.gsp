
<%@ page import="es.manuel.Cine" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cine.label', default: 'Cine')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cine" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cine" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'cine.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="numeroSalas" title="${message(code: 'cine.numeroSalas.label', default: 'Numero Salas')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cineInstanceList}" status="i" var="cineInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cineInstance.id}">${fieldValue(bean: cineInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: cineInstance, field: "numeroSalas")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cineInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
