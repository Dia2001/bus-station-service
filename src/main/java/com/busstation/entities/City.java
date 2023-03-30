package com.busstation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_city")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "city_id", updatable = false , length = 11)
	private int cityId;

	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@OneToOne(mappedBy = "city")
	private Province province;
}
