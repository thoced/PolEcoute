package dao;

import MainView.SingletonConnection;
import com.mysql.cj.jdbc.PreparedStatement;
import models.Numero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NumeroDAO extends DAO<Numero> {


    @Override
    public void insert(Numero model) throws SQLException {
        PreparedStatement ps = (PreparedStatement) SingletonConnection.getInstance().getConnection().prepareStatement("insert into t_numero (callid,target_name,ref_id_dossier) VALUES (?,?,?)");
        ps.setString(1,model.getCallId());
        ps.setString(2,model.getTargetName());
        ps.setLong(3,model.getRefIdDossier());
        ps.executeUpdate();
        ps.close();

    }

    @Override
    public void delete(long id) throws SQLException {
        PreparedStatement ps = (PreparedStatement) SingletonConnection.getInstance().getConnection().prepareStatement("delete * from t_numero where id = ?");
        ps.setLong(1,id);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(Numero model) {

    }

    @Override
    public Numero find(long id){
        Numero numero = new Numero();
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_numero where id = ?");
            ps.setLong(1,id);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                numero.setId(result.getLong("id"));
                numero.setCallId(result.getString("callid"));
                numero.setTargetName(result.getString("target_name"));
                numero.setRefIdDossier(result.getLong("ref_id_dossier"));

            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return numero;
        }

    }

    @Override
    public List<Numero> selectAll() {
        List<Numero> list = new ArrayList<>();
        Statement st = null;
        try {
            st = SingletonConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("select * from t_numero");
            while(resultSet.next()){
                Numero numero = new Numero();
                numero.setId(resultSet.getLong("id"));
                numero.setCallId(resultSet.getString("callid"));
                numero.setTargetName(resultSet.getString("target_name"));
                numero.setRefIdDossier(resultSet.getLong("ref_id_dossier"));
                list.add(numero);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return list;
        }

    }

    @Override
    public List<Numero> selectFromForeignKey(long id) {
        List<Numero> list = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_numero where ref_id_dossier = ?");
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Numero numero = new Numero();
                numero.setId(resultSet.getLong("id"));
                numero.setCallId(resultSet.getString("callid"));
                numero.setTargetName(resultSet.getString("target_name"));
                numero.setRefIdDossier(resultSet.getLong("ref_id_dossier"));
                list.add(numero);
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return list;
        }

    }
}
