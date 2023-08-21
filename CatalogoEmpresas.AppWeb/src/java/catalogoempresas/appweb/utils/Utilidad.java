package catalogoempresas.appweb.utils;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Utilidad {
    public static String getParameter(HttpServletRequest request, String pKey, String pDefault) {
        String result = "";//declaramos la variable result y la inicializamos entre comillas
        String value = request.getParameter(pKey);//metodo que no0s mostrara en que peticion estamos
        if (value != null && value.trim().length() > 0) {//condicion: valodamos que el valor no sea nullo y que no este vacia la cadena
            result = value;
        } else {
            result = pDefault;
        }
        return result;
    }
    
    public static void enviarError(String pError, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", pError); // peticion que le asignamos lo esta entrando en la linea anterior
        request.getRequestDispatcher("Views/Shared/error.jsp").forward(request, response);//que navege a la jsp
    }
    
    public static String obtenerRuta(HttpServletRequest request, String pStrRuta) {
        return request.getContextPath() + pStrRuta;//getContextPath() me dice cual es la url  de mi servidor
    }
}
