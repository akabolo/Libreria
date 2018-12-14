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
            String nombre=request.getParameter("nombre");
            String autor=request.getParameter("autor");
            String ano=request.getParameter("ano");
            
            
            if(listLibros.size()==0) {
            	leeLibreria();
            }
            if(recuperaLibro!=null) {
            	for (int i = 0; i < listLibros.size(); i++) {
					if (listLibros.get(i).getIsbn().equals(recuperaLibro)){
						out.println("<h2>"+listLibros.get(i).getNombre()+"</h2>");
						out.println("<p>Isbn "+listLibros.get(i).getIsbn()+"</p>");
						out.println("<p>Autor "+listLibros.get(i).getAutor()+"</p>");
						out.println("<p>Año publicación "+listLibros.get(i).getAno()+"</p>");
					}
				}
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
            	for (int i = 0; i < listLibros.size(); i++) {
					if (listLibros.get(i).getIsbn().equals(eliminaLibro)){
						listLibros.remove(i);
						out.println("<p>Se ha eliminado el libro "+listLibros.get(i).getNombre()+" de su biblioteca</p>");
					}
				}
            }
            else if(preModifica!=null) {
            	for (int i = 0; i < listLibros.size(); i++) {
					if (listLibros.get(i).getIsbn().equals(preModifica)){
						out.println("ISBN: <input type='text' name='isbn' id='isbn'/>");
						out.println("Nombre: <input type='text' name='nombre' id='nombre'/>");
						out.println("Autor: <input type='text' name='autor' id='autor'/>");
						out.println("Año: <input type='text' name='ano' id='ano'/>");
						out.println("<input type='button' id='enviar' value='Modificar libro' onclick='modificaLibro();'>");
					}
				}
            }
            else if(modificaLibro!=null) {
            
						out.println(isbn);
						out.println(nombre);
						out.println(autor);
						out.println(ano);
					
				
            }
            else if(preCrea!=null) {
            	
            }
            else if(creaLibro!=null) {
	
            }  
            
        }    
        else {
            out.println("Este servlet solo se puede invocar v�a Ajax");
        }    
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		libreria(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		libreria(request, response);
	}

}
