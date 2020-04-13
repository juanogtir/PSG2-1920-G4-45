<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="donations">
	<h2>
		Donaci�n
	</h2>
	
	<form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Cantidad" name="amount" />
			
			<petclinic:selectField label="Cliente" name="client" size="5" names="${owners}" />
		</div>
		<div class="form-group">
			
			<div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-default" type="submit">Donar</button>
			</div>
		</div>
	</form:form>
</petclinic:layout>

