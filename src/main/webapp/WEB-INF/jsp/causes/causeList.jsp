<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>Causes</h2>

	<table id="causesTable" class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Total of donations</th>
				<th>Budget target</th>
				<th>Donate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${causes}" var="cause">
				<spring:url value="/causes/{causeId}/donate" var="createDonation">
					<spring:param name="causeId" value="${cause.id}" />
				</spring:url>
				<tr>
					<td><spring:url value="/causes/{causeId}" var="causeUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url> <a href="${fn:escapeXml(causeUrl)}"> <c:out value="${cause.name}" /></a></td>
					<td><c:out value="${cause.totalAmountOfDonations}" /></td>
					<td><c:out value="${cause.budgetTarget}" /></td>

					<td><c:if test="${cause.closed == false}">
							<a href="${fn:escapeXml(createDonation)}" class="btn btn-default">Donate</a>
						</c:if></td>
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
