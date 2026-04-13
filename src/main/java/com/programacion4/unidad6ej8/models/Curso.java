package com.programacion4.unidad6ej8.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Curso impartido por un {@link Profesor}. Los {@link Tema} pertenecen al curso; la lista es el
 * lado propietario lógico de la colección (mapeada inversamente desde {@code Tema.curso}).
 * <p>
 * La relación muchos-a-muchos con {@link Estudiante} está definida solo en {@code Estudiante}
 * ({@code @JoinTable inscripciones}); desde {@code Curso} no hay campo de navegación para evitar
 * duplicar el mapeo de la misma tabla de unión.
 */
// Entidad JPA para la tabla curso.
@Entity
@Table(name = "curso")
// Lombok: accesores, equals, hashCode y toString.
@Data
// Constructor vacío exigido por JPA.
@NoArgsConstructor
// Constructor con todos los campos.
@AllArgsConstructor
// Builder de Lombok; usar @Builder.Default en colecciones para defaults no nulos.
@Builder
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column
	private String descripcion;

	// Cardinalidad N:1 hacia Profesor; LAZY no carga el profesor hasta que se accede al campo.
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	// Nombre exacto de la columna FK profesor_id en tabla curso.
	@JoinColumn(name = "profesor_id", nullable = false)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Profesor profesor;

	// Lado inverso: la FK está en tema.curso_id; mappedBy debe coincidir con el nombre del campo en Tema.
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	// cascade ALL: operaciones de persistencia/remove se propagan a cada Tema de la colección.
	// orphanRemoval true: al sacar un tema de la lista, se elimina su fila si ya no está referenciada.
	// fetch LAZY: los temas no se cargan hasta invocar el getter de la colección.
	@Builder.Default
	private List<Tema> temas = new ArrayList<>();

	/**
	 * Mantiene ambos lados de la asociación bidireccional Curso ↔ Tema al agregar un tema.
	 */
	public void addTema(Tema tema) {
		temas.add(tema);
		tema.setCurso(this);
	}

	/**
	 * Rompe la asociación en ambos lados; con orphanRemoval, el tema deja de persistirse con el curso.
	 */
	public void removeTema(Tema tema) {
		temas.remove(tema);
		tema.setCurso(null);
	}
}
