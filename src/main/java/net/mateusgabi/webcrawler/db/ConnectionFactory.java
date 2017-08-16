package net.mateusgabi.webcrawler.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String USUARIO = "postgres";
    private static final String SENHA = "@BDsTPSk";
    private static final String URL = "jdbc:postgresql://localhost:5432/crawler";

    public static Connection connect() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

}
