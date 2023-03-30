package com.busstation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_province")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Province {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "province_id", updatable = false , length = 11)
	private int provinceId;

	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;
}
