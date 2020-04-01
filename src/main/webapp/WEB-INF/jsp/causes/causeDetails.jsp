<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Información de causa</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Objetivo</th>
            <td><c:out value="${cause.budgetTarget}"/></td>
        </tr>
        <tr>
            <th>Organización</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
         <tr>
            <th>Recaudación total de donaciones</th>
            <td><c:choose>
            <c:when test="${cause.totalAmountOfDonations!=null}">
                <c:out value="${cause.totalAmountOfDonations}"/>
            </c:when>
            <c:otherwise>
             	0
            </c:otherwise>
        </c:choose></td>
        </tr>
         <tr>
            <th>Cerrado</th>
            <td><c:choose>
            <c:when test="${cause.closed==true}">
                Sí
            </c:when>
            <c:otherwise>
             	No
            </c:otherwise>
        </c:choose></td>
        </tr>
    </table>

 <table class="table table-striped">
        <c:forEach var="donation" items="${cause.donations}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Fecha de donación</dt>
                        <dd><c:out value="${donation.donationDate}"/></dd>
                        <dt>Cantidad</dt>
                        <dd><c:out value="${donation.donation}"/></dd>
                        <dt>Cliente</dt>
                        <dd><c:out value="${donation.client.firstName} ${donation.client.lastName}"/></dd>
                    </dl>
                </td>
                 </tr>

        </c:forEach>
    </table>
   

</petclinic:layout>
