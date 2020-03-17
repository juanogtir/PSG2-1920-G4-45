<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="vets">
	<h2>
<<<<<<< HEAD
		<c:if test="${vet['new']}">Nuevo </c:if>
		Veterinario
	</h2>

	<form:form modelAttribute="vet" class="form-horizontal" id="add-vet-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Nombre" name="firstName" />
			<petclinic:inputField label="Apellidos" name="lastName" />
			<label class="col-sm-2 control-label">Especialidades</label><form:select path="specialties" items="${specialties}" size="5" itemLabel="name" itemValue="id"/>
		</div>
		<div class="form-group">
			<input type="hidden" name="id" value="${vet.id}" />
			<div class="col-sm-offset-2 col-sm-10">
				<!-- <button class="btn btn-default" type="submit">Crear/Actualizar Veterinario</button> -->
				<c:choose>
                        <c:when test="${vet['new']}">
                            <button class="btn btn-default" type="submit">Crear Veterinario</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Actualizar Veterinario</button>
                        </c:otherwise>
                </c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>
=======
		<c:if test="${vet['new']}">New </c:if>
		Vet
	</h2>

	<form:form modelAttribute="vet" class="form-horizontal" id="add-vet-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="First Name" name="firstName" />
			<petclinic:inputField label="Last Name" name="lastName" />
			<form:select path="specialties" items="${specialties}" size="5" itemLabel="name" itemValue="id"/>
		</div>
		<div class="form-group">
			<input type="hidden" name="id" value="${vet.id}" />
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Update Vet</button>
			</div>
		</div>
	</form:form>
</petclinic:layout>
>>>>>>> branch 'createVet' of https://github.com/gii-is-psg2/PSG2-1920-G4-45.git
