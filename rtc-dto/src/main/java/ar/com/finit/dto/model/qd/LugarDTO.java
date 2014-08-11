package ar.com.finit.dto.model.qd;

/**
 * @author leo
 */
public class LugarDTO {

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdDeporte() {
		return idDeporte;
	}

	public void setIdDeporte(int idDeporte) {
		this.idDeporte = idDeporte;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public int getIdBarrio() {
		return idBarrio;
	}

	public void setIdBarrio(int idBarrio) {
		this.idBarrio = idBarrio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getMostrar() {
		return mostrar;
	}

	public void setMostrar(int mostrar) {
		this.mostrar = mostrar;
	}

	public int getDestacada() {
		return destacada;
	}

	public void setDestacada(int destacada) {
		this.destacada = destacada;
	}

	
}
