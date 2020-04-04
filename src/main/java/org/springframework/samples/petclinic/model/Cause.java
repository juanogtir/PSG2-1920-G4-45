/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "causes")
public class Cause extends BaseEntity {

	@Column(name = "name")
	@NotEmpty
	private String	name;

	@Column(name = "description")
	@NotEmpty
	private String	description;

	@Column(name = "budget_target")
	@NotNull
	@Min(0)
	private Integer	budgetTarget;

	@Column(name = "organization")
	@NotEmpty
	private String	organization;

	@Column(name = "closed")
	@NotNull
	private Boolean	closed;


	//@Transient
	//private Integer totalAmountOfDonations;
	public Integer getTotalAmountOfDonations() {
		Integer totalAmountOfDonations = 0;
		for (Donation donation : this.getDonations()) {
			totalAmountOfDonations += donation.getAmount();
		}
		return totalAmountOfDonations;
	}


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause", fetch = FetchType.EAGER)
	private List<Donation> donations;
}
