<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>Causas</h2>

	<table id="causesTable" class="table table-striped">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Cantidad total donada</th>
				<th>Cantidad objetivo</th>
				<th>Donar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${causes}" var="cause">
				<tr>
					<!-- <td><spring:url value="/causes/{causeId}" var="causeUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url>
						<a href="${fn:escapeXml(causeUrl)}"> <c:out value="${cause.name}" /></a>
					</td> -->
					<td>
						<c:out value="${cause.name}" />
					</td>
					<td>
						<c:out value="${cause.totalAmountOfDonations}" />
					</td>
					<td>
						<c:out value="${cause.budgetTarget}" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<!-- <table class="table-buttons">
		<tr>
			<td><a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a></td>
			<td>
            	<a class="btn btn-default" href='<spring:url value="/vets/new" htmlEscape="true"/>'>Añadir veterinario</a>
            </td>
		</tr>
	</table> -->

</petclinic:layout>
