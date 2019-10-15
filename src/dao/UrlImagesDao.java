
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UrlImagesDao {
    
    private Conector connector;

    public UrlImagesDao(Conector connector) {
        this.connector = connector;
    }
    
    public String getUrlById(int id) throws SQLException {
        Statement st = this.connector.getConexion().createStatement();
        ResultSet rs = st.executeQuery("select ruta_img from url_image where id_Img = '" + id +"'");
        
        rs.next();
        
        return rs.getString(1);
    }
}
