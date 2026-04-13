package com.programacion4.unidad6ej8.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Docente del sistema. Es el <strong>dueño</strong> de la relación 1:1 con {@link PerfilDetalle}
 * (posee la columna foránea mediante {@code @JoinColumn}).
 */
// Entidad JPA mapeada a la tabla indicada.
@Entity
// Nombre de tabla física en la base de datos.
@Table(name = "profesor")
// Lombok: getters, setters, equals, hashCode y toString.
@Data
// Constructor sin argumentos requerido por JPA.
@NoArgsConstructor
// Constructor con todos los atributos (útil con @Builder y tests).
@AllArgsConstructor
// Patrón builder generado por Lombok.
@Builder
public class Profesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// nullable = false exige valor en INSERT/UPDATE a nivel de esquema.
	@Column(nullable = false)
	private String nombre;

	// unique + nullable aseguran legajo obligatorio y no duplicado.
	@Column(unique = true, nullable = false)
	private String legajo;

	// Asociación uno a uno; LAZY retrasa la carga del perfil; cascade ALL propaga persistencia al detalle.
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	// Columna FK en tabla profesor hacia perfil_detalle; unique refuerza cardinalidad 1:1.
	@JoinColumn(name = "perfil_detalle_id", nullable = false, unique = true)
	// Evita recursión en toString entre Profesor y PerfilDetalle.
	@ToString.Exclude
	// Evita incluir el detalle en equals/hashCode (lazy y ciclos).
	@EqualsAndHashCode.Exclude
	private PerfilDetalle perfilDetalle;
}
