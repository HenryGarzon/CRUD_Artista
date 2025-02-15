/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.*;

// DAO - ArtistaDAO.java
public class ArtistaDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String url = "jdbc:mysql://localhost:3306/ejemplo_seguridad";
    private String user = "root";
    private String password = "henry";

    public ArtistaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Artista> listar() {
        List<Artista> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM artista");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Artista(rs.getInt("ID_Artista"), rs.getString("Nombre"), rs.getString("Genero_Musical")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    public void insertar(Artista artista) {
        try {
            ps = con.prepareStatement("INSERT INTO artista (Nombre, Genero_Musical) VALUES (?, ?)");
            ps.setString(1, artista.getNombre());
            ps.setString(2, artista.getGeneroMusical());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    public Artista obtenerPorId(int id) {
        Artista artista = null;
        try {
            ps = con.prepareStatement("SELECT * FROM artista WHERE ID_Artista = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                artista = new Artista(rs.getInt("ID_Artista"), rs.getString("Nombre"), rs.getString("Genero_Musical"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return artista;
    }

    public void actualizar(Artista artista) {
        try {
            ps = con.prepareStatement("UPDATE artista SET Nombre = ?, Genero_Musical = ? WHERE ID_Artista = ?");
            ps.setString(1, artista.getNombre());
            ps.setString(2, artista.getGeneroMusical());
            ps.setInt(3, artista.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    public void eliminar(int id) {
        try {
            ps = con.prepareStatement("DELETE FROM artista WHERE ID_Artista = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
