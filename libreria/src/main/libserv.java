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
       
	ArrayList<Libro> libreria= new ArrayList();
	
    public void leeLibreria() throws IOException{
    	String ruta="ficheros/libreria.txt";
    	String linea="";
    	FileReader k = new FileReader(ruta);
    	BufferedReader j = new BufferedReader(k);
    	
    	while((linea = j.readLine())!=null) {
    		String [] info= linea.split(";");
    		libreria.add(new Libro(info[0], info[1], info[2], info[3]));
    	}
    }
    public libserv() {super();}

    protected void libreria (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        // Comprobar si la petici�n es mediante Ajax
        Boolean esAjax;
        esAjax="XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")); // Cabecera X-Requested-With
        if (esAjax) {
            // Comprobar si el usuario es v�lido
            String isbn=request.getParameter("isbn");
            out.println("<h1>El isbn es"+isbn+"</h1>");
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
