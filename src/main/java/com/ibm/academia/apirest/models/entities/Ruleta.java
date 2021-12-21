package com.ibm.academia.apirest.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyClass;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.apirest.models.enums.Estatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ruletas")
public class Ruleta implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "estatus")
	@Enumerated(EnumType.STRING)
	private Estatus estatus;
	
	@ElementCollection(targetClass = String.class)
	@MapKeyClass(Integer.class)
	@Column(name = "tablero")
	private Map<Integer, String> tablero;
		
	@OneToMany(mappedBy = "ruleta", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","ruleta"})
	private List<Apuesta> apuestas;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	@PrePersist
	private void antesPersistir()
	{
		this.fechaCreacion = new Date();
		this.estatus = Estatus.CERRADA;
		this.tablero = new HashMap<Integer, String>(){
		private static final long serialVersionUID = 3470230728458613739L;
		{
			put(0, "SIN_COLOR");
			put(1, "NEGRO");
			put(2, "ROJO");
			put(3, "NEGRO");
			put(4, "ROJO");
			put(5, "NEGRO");
			put(6, "ROJO");
			put(7, "NEGRO");
			put(8, "ROJO");
			put(9, "NEGRO");
			put(10, "ROJO");
			put(11, "NEGRO");
			put(12, "ROJO");
			put(13, "NEGRO");
			put(14, "ROJO");
			put(15, "NEGRO");
			put(16, "ROJO");
			put(17, "NEGRO");
			put(18, "ROJO");
			put(19, "NEGRO");
			put(20, "ROJO");
			put(21, "NEGRO");
			put(22, "ROJO");
			put(23, "NEGRO");
			put(24, "ROJO");
			put(25, "NEGRO");
			put(26, "ROJO");
			put(27, "NEGRO");
			put(28, "ROJO");
			put(29, "NEGRO");
			put(30, "ROJO");
			put(31, "NEGRO");
			put(32, "ROJO");
			put(33, "NEGRO");
			put(34, "ROJO");
			put(35, "NEGRO");
			put(36, "ROJO");
		}};
	}
	

	private static final long serialVersionUID = -5025194167815941941L;
	

}
