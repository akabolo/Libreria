package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/libserv")
public class libserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public libserv() {super();}
	ArrayList<Libro> listLibros= new ArrayList();
	
    public void leeLibreria() throws IOException{
    	String ruta="./ficheros/libreria.txt";
    	String linea="";
    	FileReader k = new FileReader(this.getServletContext().getRealPath("/")+ruta);
    	BufferedReader j = new BufferedReader(k);
    	
    	while((linea = j.readLine())!=null) {
    		String [] info= linea.split(";");
    		listLibros.add(new Libro(info[0], info[1], info[2], info[3]));
    	}
    }

    protected void libreria (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        // Comprobar si la petición es mediante Ajax
        Boolean esAjax;
        esAjax="XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")); // Cabecera X-Requested-With
        if (esAjax) {
        	
        	String recuperaLibro=request.getParameter("recuperaLibro");
            String recuperaAllLibro=request.getParameter("recuperaAllLibro");
            String eliminaLibro=request.getParameter("eliminaLibro");
            String preModifica=request.getParameter("preModifica");
            String modificaLibro=request.getParameter("modificaLibro");
            String preCrea=request.getParameter("preCrea");
            String creaLibro=request.getParameter("creaLibro");
            String isbn=request.getParameter("isbn");
            String isbn2=request.getParameter("isbn2");
            String nombre=request.getParameter("nombre");
            String autor=request.getParameter("autor");
            String ano=request.getParameter("ano");
            String isbnAntiguo=request.getParameter("isbnAntiguo");
            
            
            if(listLibros.size()==0) {
            	leeLibreria();
            }
            if(recuperaLibro!=null) {
            	int cont=0;
            	for (int i = 0; i < listLibros.size(); i++) {
					if (listLibros.get(i).getIsbn().equals(recuperaLibro)){
						out.println("</br></br></br></br></br><h2>"+listLibros.get(i).getNombre()+"</h2>");
						out.println("<p>Isbn "+listLibros.get(i).getIsbn()+"</p>");
						out.println("<p>Autor "+listLibros.get(i).getAutor()+"</p>");
						out.println("<p>Año publicación "+listLibros.get(i).getAno()+"</p>");
						cont++;
					}
				}
            	if(cont==0) {out.println("</br></br></br></br></br></br></br></br></br></br>El ISBN introducido es erroneo");}
            }
            else if(recuperaAllLibro!=null) {
            	for (int i = 0; i < listLibros.size(); i++) {
						out.println("<h2>"+listLibros.get(i).getNombre()+"</h2>");
						out.println("<p>Isbn "+listLibros.get(i).getIsbn()+"</p>");
						out.println("<p>Autor "+listLibros.get(i).getAutor()+"</p>");
						out.println("<p>Año publicación "+listLibros.get(i).getAno()+"</p></br>");
				}
            	
            }
            else if(eliminaLibro!=null) {
            	int cont=0;
            	for (int i = 0; i < listLibros.size(); i++) {
					if (listLibros.get(i).getIsbn().equals(eliminaLibro)){
						listLibros.remove(i);
						out.println("</br></br></br></br></br></br></br></br><p>Se ha eliminado el libro '"+listLibros.get(i).getNombre()+"' de su biblioteca</p>");
						cont++;
					}				
				}
            	if(cont==0) {out.println("</br></br></br></br></br></br></br></br></br></br>El ISBN introducido es erroneo");}	
            }
            else if(preModifica!=null) {
            	int cont=0;
            	for (int i = 0; i < listLibros.size(); i++) {
					if (listLibros.get(i).getIsbn().equals(preModifica)){
						out.println("<div id='aka'>");
						out.println("</br></br>Datos a modificar:</br></br></br>");
						out.println("ISBN: <input type='text' name='isbn' id='isbn'/></br></br>");
						out.println("Nombre: <input type='text' name='nombre' id='nombre'/></br></br>");
						out.println("Autor: <input type='text' name='autor' id='autor'/></br></br>");
						out.println("Año: <input type='text' name='ano' id='ano'/></br></br></br>");
						out.println("<input type='button' id='enviar' value='Modificar libro' onclick='modificaLibro();'>");
						out.println("<input type='hidden' id='isbnAntiguo' value='"+preModifica+"'/>");
						out.println("</div>");
						cont++;
					}					
				}
            	if (cont==0) {out.println("</br></br></br></br></br></br></br></br></br></br>El ISBN introducido es erroneo");}
            }
            else if(isbnAntiguo!=null) {
            	if (isbn!=null || isbn!="") {
            	for (int i = 0; i < listLibros.size(); i++) {
            		if (listLibros.get(i).getIsbn().equals(isbnAntiguo)){
						listLibros.get(i).setIsbn(isbn);
						listLibros.get(i).setNombre(nombre);
						listLibros.get(i).setAutor(autor);
						listLibros.get(i).setAno(ano);
						out.println("</br></br>El libro con ISBN: "+isbnAntiguo+" ha sido editado</br>");
						out.println("Los nuevos datos son:</br></br>");
						out.println("<h2>Nombre: "+listLibros.get(i).getNombre()+"</h2></br>");
						out.println("<p>ISBN: "+listLibros.get(i).getIsbn()+"</p></br>");
						out.println("<p>Autor: "+listLibros.get(i).getAutor()+"</p></br>");
						out.println("<p>Año: "+listLibros.get(i).getAno()+"</p>");
					}
				}
            	}else {out.println("</br></br></br></br></br></br></br></br></br></br>El ISBN introducido es erroneo");}
			}
            else if(preCrea!=null) {
            	out.println("<div id='aka'>");
            	out.println("</br></br>Datos del libro a crear:</br></br></br>");
				out.println("ISBN: <input type='text' name='isbn' id='isbn'/></br></br>");
				out.println("Nombre: <input type='text' name='nombre' id='nombre'/></br></br>");
				out.println("Autor: <input type='text' name='autor' id='autor'/></br></br>");
				out.println("Año: <input type='text' name='ano' id='ano'/></br></br></br>");
				out.println("<input type='button' id='enviar' value='Crear Libro' onclick='creaLibro();'>");
				out.println("</div>");
				
            }
            else if(isbn2!=null) {
            	if(isbn2!=null || isbn2!="") {
            	listLibros.add(new Libro(isbn2,nombre,autor,ano));
            	out.println("</br></br>El nuevo libro ha sido añadido</br>");
				out.println("Los datos del nuevo libro son:</br></br>");
				out.println("<h2>Nombre: "+nombre+"</h2></br>");
				out.println("<p>ISBN: "+isbn2+"</p></br>");
				out.println("<p>Autor: "+autor+"</p></br>");
				out.println("<p>Año: "+ano+"</p>");
            	}else {out.println("El ISBN introducido es incorrecto");}
            }  
            
        }    
        else {
            out.println("Este servlet solo se puede invocar via Ajax");
        }    
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		libreria(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		libreria(request, response);
	}

}
