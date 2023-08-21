package catalogoempresas.appweb.utils;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import catalogoempresas.entidadesdenegocio.*;

public class SessionUser {
    public static void autenticarUser(HttpServletRequest request, Usuario pUsuario) {
        HttpSession session = (HttpSession) request.getSession();//si ecribo algo entre parentesis y luego algo mas fuera de los parentesis se esta haciendo una conversion
        session.setMaxInactiveInterval(3600);//Cantidad maxima que estara abrierta una sesion
        session.setAttribute("auth", true);
        session.setAttribute("user", pUsuario.getLogin());
        session.setAttribute("rol", pUsuario.getRol().getNombre()); 
    }
    
    public static boolean isAuth(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        boolean auth = session.getAttribute("auth") != null ? (boolean) session.getAttribute("auth") : false;//si el parametro "auth" es diferente de nullo lo tamamos y lo convertimos en booleano y eso es lo que devolvemos de lo cantrario devolvemos una cadena vacia
        return auth;
    }
    
    public static String getUser(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        String user = "";
        if (SessionUser.isAuth(request)) { 
            user = session.getAttribute("user") != null ? (String) session.getAttribute("user") : "";
        }
        return user;
    }
    
    public static String getRol(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        String user = "";
        if (SessionUser.isAuth(request)) {
            user = session.getAttribute("rol") != null ? (String) session.getAttribute("rol") : "";//Operador ternario: es como una candiocion IF pero en una sola linea
        }
        return user;
    }
    
    public static void authorize(HttpServletRequest request, HttpServletResponse response, IAuthorize pIAuthorize) throws ServletException, IOException {
        if (SessionUser.isAuth(request)) {//si el usuario ya inicio sesion
            pIAuthorize.authorize();//JDK
        } else {
            response.sendRedirect("Usuario?accion=login");//si no ha iniciado sesion lo mandamos a que inicie
        }
    }
    
    public static void cerrarSession(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();//del metodo request obtenemos la sesion
        session.invalidate();
    }
}
