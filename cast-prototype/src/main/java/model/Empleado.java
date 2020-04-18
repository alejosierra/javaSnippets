package model;

public class Empleado {
	private int codigo;
	private String nombre;
	private int salario;
	public Empleado() {
		super();
	}
	public Empleado(int codigo, String nombre, int salario) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.salario = salario;
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
}
