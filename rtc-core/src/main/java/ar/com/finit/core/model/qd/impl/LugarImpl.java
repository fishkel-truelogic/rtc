package ar.com.finit.core.model.qd.impl;

import ar.com.finit.core.model.qd.Lugar;

/**
 * @author leo
 */
public class LugarImpl implements Lugar {

	
	private int id;
	
	private int idDeporte;
	
	private int idProvincia;
	
	private int idBarrio;
	
	private String descripcion;
	
	private int tamano;
	
	private String domicilio;
	
	private String telefono;
	
	private String comentario;
	
	private int mostrar;
	
	private int destacada;

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getIdDeporte() {
		return idDeporte;
	}

	public void setIdDeporte(int idDeporte) {
		this.idDeporte = idDeporte;
	}

	@Override
	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	@Override
	public int getIdBarrio() {
		return idBarrio;
	}

	public void setIdBarrio(int idBarrio) {
		this.idBarrio = idBarrio;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	@Override
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public int getMostrar() {
		return mostrar;
	}

	public void setMostrar(int mostrar) {
		this.mostrar = mostrar;
	}

	@Override
	public int getDestacada() {
		return destacada;
	}

	public void setDestacada(int destacada) {
		this.destacada = destacada;
	}
	
	
}
