<<<<<<< HEAD
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
=======
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git

<petclinic:layout pageName="vets">
	<h2>Vets</h2>

<<<<<<< HEAD
    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Especialidades</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td><spring:url value="/vets/{vetId}" var="vetUrl">
							<spring:param name="vetId" value="${vet.id}" />
						</spring:url> <a href="${fn:escapeXml(vetUrl)}">  <c:out value="${vet.firstName} ${vet.lastName}"/>  </a>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">ninguna</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
=======
	<table id="vetsTable" class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Specialties</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vets.vetList}" var="vet">
				<tr>
					<td><spring:url value="/vets/{vetId}" var="vetUrl">
							<spring:param name="vetId" value="${vet.id}" />
						</spring:url> <a href="${fn:escapeXml(vetUrl)}"> <c:out value="${vet.firstName} ${vet.lastName}" /></a></td>
					<td><c:forEach var="specialty" items="${vet.specialties}">
							<c:out value="${specialty.name} " />
						</c:forEach> <c:if test="${vet.nrOfSpecialties == 0}">none</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git

<<<<<<< HEAD
    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">Ver como XML</a>
            </td>
            <td>
            	<a class="btn btn-default" href='<spring:url value="/vets/new" htmlEscape="true"/>'>A�adir veterinario</a>
            </td>            
        </tr>
    </table>
=======
	<table class="table-buttons">
		<tr>
			<td><a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a></td>
		</tr>
	</table>
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git
</petclinic:layout>

