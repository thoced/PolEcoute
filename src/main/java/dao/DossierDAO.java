package dao;

import MainView.SingletonConnection;
import models.Dossier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DossierDAO extends DAO<Dossier> {


    @Override
    public void insert(Dossier model) throws SQLException {
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement("insert into t_dossiers (nom,commentaire) VALUES (?,?)");
        ps.setString(1,model.getNom());
        ps.setString(2,model.getCommentaire());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(long id) throws SQLException {

        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement("delete from t_dossiers where id = ?");
        ps.setLong(1,id);
        ps.executeUpdate();
        ps.close();

    }

    @Override
    public void update(Dossier model) {

    }

    @Override
    public Dossier find(long id)  {

        Dossier dossier = new Dossier();
        PreparedStatement ps = null;
        try {
            ps = SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_dossiers where id = ?");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                dossier.setId(resultSet.getLong("id"));
                dossier.setNom(resultSet.getString("nom"));
                dossier.setCommentaire(resultSet.getString("commentaire"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return dossier;
        }

    }

    @Override
    public List<Dossier> selectAll(){
        List<Dossier> list = new ArrayList<Dossier>();
        ResultSet resultSet;
        try {
            resultSet = SingletonConnection.getInstance().getConnection().createStatement().executeQuery("select * from t_dossiers");
            while(resultSet.next()){
                Dossier dossier = new Dossier();
                dossier.setId(resultSet.getLong("id"));
                dossier.setNom(resultSet.getString("nom"));
                dossier.setCommentaire(resultSet.getString("commentaire"));
                list.add(dossier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            return list;
     }

    }
    @Override
    public List<Dossier> selectFromForeignKey(long id) {
       return null;
    }
}
