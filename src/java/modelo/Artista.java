package modelo;

import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Modelo - Artista.java
public class Artista {
    private int id;
    private String nombre;
    private String generoMusical;

    public Artista() {}
    
    public Artista(int id, String nombre, String generoMusical) {
        this.id = id;
        this.nombre = nombre;
        this.generoMusical = generoMusical;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGeneroMusical() { return generoMusical; }
    public void setGeneroMusical(String generoMusical) { this.generoMusical = generoMusical; }
}