<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="petHotelList">
    <h2>Reservas</h2>
	<p><th style="width: 150px;">Mascota: 
       		<c:forEach items="${petHotels}" var="petHotel" begin="0" end="0"><c:out value="${petHotel.pet.name}"/></c:forEach>
       </th>
    </p>
    <table id="petHotelTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Información</th>
            <th>Fecha de inicio</th>
            <th>Fecha final</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${petHotels}" var="petHotel">
            <tr>
                <td>
                    <c:out value="${petHotel.info}"/>
                </td>
                <td>
                    <c:out value="${petHotel.initialDate}"/>
                </td>
                <td>
               		<c:out value="${petHotel.endDate}"/>
           		</td>
           		 <td>
                <spring:url value="/owners/{ownerId}/pets/{petId}/pet-hotels/delete/{petHotelId}" var="petHotelUrl">
        		<spring:param name="petHotelId" value="${petHotel.id}"/>
        		<spring:param name="ownerId" value="${owner.id}"/>
        		<spring:param name="petId" value="${pet.id}"/>
        		
    			</spring:url>
    			<a href="${fn:escapeXml(petHotelUrl)}">Eliminar reserva</a>
    			</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
