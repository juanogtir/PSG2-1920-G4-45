<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pet-hotel">
    <h2>
        Hotel de mascotas
    </h2>
    <form:form modelAttribute="petHotel" class="form-horizontal" id="add-petHotel-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Info" name="info"/>
            <petclinic:inputField label="Fecha de inicio" name="initialDate"/>
            <petclinic:inputField label="Fecha de fin" name="endDate"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">A�adir hotel de mascotas</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
