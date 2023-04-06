package com.busstation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tbl_city")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id", updatable = false , length = 11)
	private int cityId;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@OneToOne
	@JoinColumn(name = "province_id")
	private Province province;
}
