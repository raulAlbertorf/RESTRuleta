package com.ibm.academia.apirest.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apuestas")
public class Apuesta implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotEmpty(message = "No puede ser vacio")
//	@NotNull(message = "No pude ser nulo")
	@Column(name = "alias", nullable = false)
	private String alias;
	
//	@Positive(message = "Cantidad debe ser mayor a cero")
	//@Size(min = 1, max = 10000)
	@Column(name = "cantidad_apostada")
	private Integer cantidadApostada;
	
	//@Size(min = 0, max = 36)
	@Column(name = "numero")
	private Integer numero;
	
//	@NotEmpty(message = "No puede ser vacio")
//	@NotNull(message = "No pude ser nulo")
	@Column(name = "color")
	private String color;
	
	@ManyToOne(optional = true , fetch = FetchType.LAZY , cascade = { CascadeType.PERSIST , CascadeType.MERGE })
	@JoinColumn(name = "ruleta_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "apuestas"})
	private Ruleta ruleta;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	public Apuesta(String alias, Integer cantidadApostada, Integer numero, String color, Ruleta ruleta) {
		this.alias = alias;
		this.cantidadApostada = cantidadApostada;
		this.numero = numero;
		this.color = color.toUpperCase();
		this.ruleta = ruleta;
	}
	
	@PrePersist
	private void antesPersistir()
	{
		this.fechaCreacion = new Date();
	}


	private static final long serialVersionUID = -6342231270350966746L;
}