package net.mateusgabi.webcrawler.db;

import net.mateusgabi.webcrawler.craw.WebPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus Gabi Moreira
 * @email mateusgabimoreira@gmail.com
 */
public class WebPageDAO implements DAO<WebPage> {

    private static Logger LOGGER = Logger.getLogger(WebPageDAO.class.getName());

    private static String ID = "id";
    private static String URL = "url";
    private static String TITULO = "title";
    private static String AUTHOR = "author";
    private static String CRAW_VISITS = "craw_visits";
    private static String DATA_INSERCAO = "indexed_at";
    private static String ULTIMA_VISITA = "last_visit_at";
    private static String KEYWORDS = "keywords";
    private static String STATUS = "status";

    public Response adicionar(WebPage webPage) {

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO public.webpage ");
            sql.append("("+ URL +", "+ TITULO +", "+ DATA_INSERCAO +", "+ ULTIMA_VISITA +", "+ STATUS +") ");
            sql.append("VALUES (?, ?, ?, ?, ?)");

            Connection conexao = ConnectionFactory.connect();

            PreparedStatement comando = conexao.prepareStatement(sql.toString());
            comando.setString(1, "" + webPage.getURL());
            comando.setString(2, "" + webPage.getTitle());
            comando.setString(3, "" + webPage.getIndexed_at());
            comando.setString(4, "" + webPage.getLast_visit_at());
            comando.setString(5, "" + webPage.getStatus().toString());

            comando.executeUpdate();


            return new Response()
                    .setStatus(Response.Status.OK)
                    .setMessage("Ae manée");

        } catch (SQLException ex) {

            LOGGER.log(Level.SEVERE, ex.getMessage());

            return new Response()
                    .setStatus(Response.Status.ERROR)
                    .setMessage(ex.getMessage());
        }

    }
}
