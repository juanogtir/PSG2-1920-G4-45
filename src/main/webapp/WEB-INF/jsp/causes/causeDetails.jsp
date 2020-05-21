<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">

	<h2>Cause details</h2>


	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${cause.name}" /></b></td>
		</tr>
		<tr>
			<th>Descripción</th>
			<td><c:out value="${cause.description}" /></td>
		</tr>
		<tr>
			<th>Cantidad Objetivo</th>
			<td><c:out value="${cause.budgetTarget}" /></td>
		</tr>
		<tr>
			<th>Organización</th>
			<td><c:out value="${cause.organization}" /></td>
		</tr>
		<tr>
			<th>Cantidad total donada</th>
			<td><c:out value="${cause.totalAmountOfDonations}" /></td>
		</tr>
		<tr>
			<th>Cerrada</th>
			<td><c:choose>
					<c:when test="${cause.closed==true}">
                Si
            </c:when>
					<c:otherwise>
             	No
            </c:otherwise>
				</c:choose></td>
		</tr>
	</table>

	<table class="table table-striped">
	<h3 class="donacionesCauseDetails">Donaciones</h3>
		<c:forEach var="donation" items="${cause.donations}">

			<tr>
				<td valign="top">
					<dl class="dl-horizontal">
						<dt>Fecha de donación</dt>
						<dd>
							<c:out value="${donation.donationDate}" />
						</dd>
						<dt>Cantidad</dt>
						<dd>
							<c:out value="${donation.amount}" />
						</dd>
						<dt>Cliente</dt>
						<dd>
							<c:out value="${donation.client.firstName} ${donation.client.lastName}" />
						</dd>
					</dl>
				</td>
			</tr>

		</c:forEach>
	</table>
	
	<a href="/causes/" class="btn btn-default">Volver al listado de causas</a>

</petclinic:layout>
