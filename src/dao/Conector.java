package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    private Connection connector;
    
    public Conector() throws SQLException {
        
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String urlDatabase = "jdbc:mysql://localhost:3306/libros?useTimezone=true&serverTimezone=CET";
        this.connector = DriverManager.getConnection(urlDatabase, "root", "elrincon1920");
        
    }

    public Connection getConexion() {
        return this.connector;
    }
}
