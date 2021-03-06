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

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "donations")
public class Donation extends BaseEntity {

	@Column(name = "donation_date")
	//@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	donationDate;

	@Column(name = "amount")
	@NotNull
	@Min(0)
	private Integer		amount;

	@JoinColumn(name = "client_id")
	@ManyToOne
	@NotNull
	private Owner		client;
	
	@NotNull
	@JoinColumn(name = "cause_id")
	@ManyToOne
	private Cause		cause;
}
