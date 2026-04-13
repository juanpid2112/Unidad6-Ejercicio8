package com.programacion4.unidad6ej8.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Detalle extendido de un {@link Profesor}. El lado <strong>inverso</strong> de la relación 1:1
 * vive aquí: la clave foránea y el {@code @JoinColumn} están en {@code Profesor}.
 */
// Marca la clase como entidad persistente gestionada por JPA/Hibernate.
@Entity
// Nombre explícito de la tabla en la base de datos (opcional pero clarifica el esquema).
@Table(name = "perfil_detalle")
// Genera getters, setters, equals, hashCode y toString (Lombok).
@Data
// Constructor sin argumentos exigido por el proveedor JPA para instanciar entidades.
@NoArgsConstructor
// Constructor con todos los campos; útil junto con @Builder y para pruebas.
@AllArgsConstructor
// Patrón builder generado por Lombok para construir instancias de forma fluida.
@Builder
public class PerfilDetalle {

	// Clave primaria de la fila.
	@Id
	// La base de datos autogenera el valor (IDENTITY / AUTO_INCREMENT según motor).
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Mapea el atributo a una columna; opciones por defecto (nullable, longitud) dependen del motor.
	@Column
	// Texto biográfico del profesor.
	private String bio;

	@Column
	// Enlace o texto del perfil de LinkedIn.
	private String linkLinkedin;

	// Asociación 1:1 inversa: el mapeo físico (columna FK) está en la tabla del otro lado (Profesor).
	@OneToOne(mappedBy = "perfilDetalle", fetch = FetchType.LAZY)
	// Evita que toString() siga la referencia y provoque recursión infinita con Profesor.
	@ToString.Exclude
	// Evita que equals/hashCode incluyan la entidad padre (ciclos y semántica incorrecta).
	@EqualsAndHashCode.Exclude
	private Profesor profesor;
}
