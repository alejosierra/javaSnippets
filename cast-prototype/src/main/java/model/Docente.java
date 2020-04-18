package model;

public class Docente {
	private int codigo;
	private String nombre;
	private int salario;
	private String departamento;
	public Docente() {
		super();
	}
	public Docente(int codigo, String nombre, int salario, String departamento) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.salario = salario;
		this.departamento = departamento;
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
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}
