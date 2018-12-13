package main;

import java.io.IOException;
import java.io.PrintWriter;
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

    protected void procesaSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Array asociativo de usuarios y claves
        LinkedHashMap<String, String> credenciales = new LinkedHashMap();
        credenciales.put ("buenos", "d�as");
        credenciales.put ("as�", "sea");
        credenciales.put ("compa�eros", "adi�s");
        credenciales.put ("felices", "vacaciones");
        
        // Comprobar si la petici�n es mediante Ajax
        Boolean esAjax;
        esAjax="XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")); // Cabecera X-Requested-With
        if (esAjax) {
            // Comprobar si el usuario es v�lido
            String usuario=request.getParameter("usuario");
            String clave="";
            Boolean usuarioValido=false;
            for (String key : credenciales.keySet() ) {
                if (usuario.equals(key)) {
                        usuarioValido=true;
                        clave=credenciales.get(key);
                }
            }
            if (usuarioValido==false)
                System.out.println("Usuario no v�lido");
            out.println(clave);
        }    
        else {
            out.println("Este servlet solo se puede invocar v�a Ajax");
        }    
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
