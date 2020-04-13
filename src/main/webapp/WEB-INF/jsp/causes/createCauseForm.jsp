<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>

		Nueva Causa
	</h2>

	<form:form modelAttribute="cause" class="form-horizontal" id="add-cause-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Nombre" name="name" />
			<petclinic:inputField label="Descripción" name="description" />
			<petclinic:inputField label="Cantidad Objetivo" name="budgetTarget" />
			<petclinic:inputField label="Organización" name="organization" />
		</div>
		<div class="form-group">
			<input type="hidden" name="id" value="${cause.id}" />
			<div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Crear Causa</button>
			</div>
		</div>
	</form:form>
</petclinic:layout>

