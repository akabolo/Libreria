package main;

public class Libro{
	
	private String isbn,nombre,autor,ano;

	public Libro(String isbn,String nombre,String autor,String ano) {
		this.isbn= isbn;
		this.nombre= nombre;
		this.autor= autor;
		this.ano= ano;
	}
 
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getAutor() {
	return autor;
}
public void setAutor(String autor) {
	this.autor = autor;
}
public String getAno() {
	return ano;
}
public void setAno(String ano) {
	this.ano = ano;
}
}
