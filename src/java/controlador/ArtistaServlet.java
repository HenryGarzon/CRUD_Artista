package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Artista;
import modelo.ArtistaDAO;

@WebServlet("/ArtistaServlet")
public class ArtistaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArtistaDAO artistaDAO = new ArtistaDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Artista> lista = artistaDAO.listar();
            request.setAttribute("listaArtistas", lista);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listar.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirigir a una página de error
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nombre = request.getParameter("nombre");
        String generoMusical = request.getParameter("generoMusical");

        if (nombre == null || nombre.trim().isEmpty() || generoMusical == null || generoMusical.trim().isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("insertar.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Artista artista = new Artista(0, nombre, generoMusical);
        artistaDAO.insertar(artista);
        response.sendRedirect("ArtistaServlet");
    }
}
