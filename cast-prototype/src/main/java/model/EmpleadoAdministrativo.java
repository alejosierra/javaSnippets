package model;

public class EmpleadoAdministrativo {
	private int codigo;
	private String nombre;
	private int salario;
	private String cargo;
	public EmpleadoAdministrativo() {
		super();
	}
	public EmpleadoAdministrativo(int codigo, String nombre, int salario, String cargo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.salario = salario;
		this.cargo = cargo;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
