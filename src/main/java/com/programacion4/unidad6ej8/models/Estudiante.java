package com.programacion4.unidad6ej8.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Alumno inscrito en cursos. Lado <strong>propietario</strong> del many-to-many con {@link Curso}:
 * la tabla intermedia y sus columnas se declaran aquí con {@code @JoinTable}.
 */
// Entidad JPA mapeada a la tabla estudiante.
@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	// unique: índice/constraint de unicidad; nullable false: email obligatorio.
	@Column(unique = true, nullable = false)
	private String email;

	// Relación muchos a muchos sin columna dedicada en estudiante: Hibernate usa tabla inscripciones.
	@ManyToMany(fetch = FetchType.LAZY)
	// joinColumns define la FK desde inscripciones hacia estudiante; inverseJoinColumns hacia curso.
	@JoinTable(
			name = "inscripciones",
			joinColumns = @JoinColumn(name = "estudiante_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"))
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Curso> cursos = new HashSet<>();
}
