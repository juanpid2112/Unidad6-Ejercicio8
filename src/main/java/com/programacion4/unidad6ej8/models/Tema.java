package com.programacion4.unidad6ej8.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Unidad temática dentro de un {@link Curso}. El nombre del campo {@code curso} coincide con
 * {@code mappedBy = "curso"} en {@link Curso#temas}.
 */
// Entidad JPA; tabla física "tema".
@Entity
@Table(name = "tema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	// int primitivo mapeado a columna NOT NULL salvo @Column distinto.
	@Column(nullable = false)
	private int duracionHoras;

	// Lado propietario de la asociación Curso–Tema a nivel de columna FK (curso_id en tabla tema).
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "curso_id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Curso curso;
}
