package model;

public class Estudiante {
	private int codigo;
	private String nombre;
	private boolean beca;
	public Estudiante() {
		super();
	}
	public Estudiante(int codigo, String nombre, boolean beca) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.beca = beca;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isBeca() {
		return beca;
	}
	public void setBeca(boolean beca) {
		this.beca = beca;
	} 
}
