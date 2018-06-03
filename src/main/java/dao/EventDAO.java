package dao;

import MainView.SingletonConnection;
import models.Event;
import models.OptionSearch;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalField;
import java.util.*;

public class EventDAO extends DAO<Event> {

    public void insertAll(List<Event> listEvents){
        PreparedStatement ps = null;
        try {
            ps = SingletonConnection.getInstance().getConnection().prepareStatement("insert into t_events (event_id," +
                    "start_date," +
                    "duration," +
                    "event_type," +
                    "direction," +
                    "relevancy," +
                    "caller_id," +
                    "caller_imei," +
                    "caller_imsi," +
                    "target_name," +
                    "called_id," +
                    "called_imei," +
                    "called_imsi," +
                    "synopsis," +
                    "sms_content," +
                    "location," +
                    "transcription," +
                    "transcription_is_done," +
                    "ref_id_numero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }




        for(Event model: listEvents) {

            try {

                // 1) on vérifie si l'event id n'est pas déja présent, auquel cas, on teste si le champs transcription est déja remplis, si c'est c'est les cas
                //on ne fait auccune mise à jour.
                // Si l'event id n'existe pas, on insert
                // si l'event id existe et que la transcriptin n'existe pas, on update

                PreparedStatement psc = SingletonConnection.getInstance().getConnection().prepareStatement("select id,transcription from t_events where ref_id_numero = ? " +
                        " AND event_id = ?");
                  psc.setLong(1, model.getRefIdNumero());
                psc.setString(2, model.getEventId());
                ResultSet resultSet = psc.executeQuery();
                if (resultSet.next()) {
                    // il y a bien un eventid identique
                    String transcription = resultSet.getString("transcription");
                    if (transcription == null || transcription.isEmpty()) {
                        // on modifie ici
                        model.setId(resultSet.getLong("id"));
                        updateAddEvent(model);
                    }
                } else {
                    // il n'y a pas d'event id identique
                    // on ajoute ici
                    insert(model);
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }


        }


    }

    /**
     * update l'event qui existe déja si le champ transcription est vide
     * @param model
     */
    private void updateAddEvent(Event model) throws SQLException {
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement("update t_events SET synopsis = ?, transcription = ?, relevancy = ? WHERE id = ?");
        ps.setString(1,model.getSynopsis());
        ps.setString(2,model.getTranscription());
        ps.setString(3,model.getRelevancy());
        ps.setLong(4,model.getId());
        ps.executeUpdate();
        ps.close();


    }

    @Override
    public void insert(Event model) throws SQLException {
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement("insert into t_events (event_id," +
                "start_date," +
                "duration," +
                "event_type," +
                "direction," +
                "relevancy," +
                "caller_id," +
                "caller_imei," +
                "caller_imsi," +
                "target_name," +
                "called_id," +
                "called_imei," +
                "called_imsi," +
                "synopsis," +
                "sms_content," +
                "location," +
                "transcription," +
                "transcription_is_done," +
                "ref_id_numero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        ps.setString(1,model.getEventId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm:ss").withLocale(Locale.ROOT);
        //LocalDate localDate = LocalDate.parse(model.getStartDate() + " " + model.getStartTime(),formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(model.getStartDate() + " " + model.getStartTime(),formatter);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        ps.setTimestamp(2,timestamp);

        ps.setString(3,model.getDuration());
        ps.setString(4,model.getEventType());
        ps.setString(5,model.getDirection());
        ps.setString(6,model.getRelevancy());
        ps.setString(7,model.getCallerId());
        ps.setString(8,model.getCallerImei());
        ps.setString(9,model.getCallerImsi());
        ps.setString(10,model.getTargetName());
        ps.setString(11,model.getCalledId());
        ps.setString(12,model.getCalledImei());
        ps.setString(13,model.getCalledImsi());
        ps.setString(14,model.getSynopsis());
        ps.setString(15,model.getSmsContent());
        ps.setString(16,model.getLocation());
        ps.setString(17,model.getTranscription());
        ps.setBoolean(18,model.isTranscriptionDone());
        ps.setLong(19,model.getRefIdNumero());

        ps.executeUpdate();
        ps.close();

    }



    @Override
    public void delete(long id) throws SQLException {
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement("delete from t_events where id = ?");
        ps.setLong(1,id);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(Event model) throws SQLException {
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement("update t_events SET transcription = ?, transcription_is_done = ? WHERE id = ?");
        ps.setString(1,model.getTranscription());
        ps.setBoolean(2,model.isTranscriptionDone());
        ps.setLong(3,model.getId());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public Event find(long id) throws SQLException {
        Event event = new Event();
        PreparedStatement ps = SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_events where id = ?");
        ps.setLong(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            event.setId(resultSet.getLong("id"));
            event.setEventId(resultSet.getString("event_id"));
            event.setRefIdNumero(resultSet.getLong("ref_id_numero"));
            event.setStartDate(resultSet.getTimestamp("start_date").toString());
            event.setDuration(resultSet.getString("duration"));
            event.setEventType(resultSet.getString("event_type"));
            event.setDirection(resultSet.getString("direction"));
            event.setRelevancy(resultSet.getString("relevancy"));
            event.setCallerId(resultSet.getString("caller_id"));
            event.setCallerImei(resultSet.getString("caller_imei"));
            event.setCallerImsi(resultSet.getString("caller_imsi"));
            event.setTargetName(resultSet.getString("target_name"));
            event.setCalledId(resultSet.getString("called_id"));
            event.setCalledImei(resultSet.getString("called_imei"));
            event.setCalledImsi(resultSet.getString("called_imsi"));
            event.setSynopsis(resultSet.getString("synopsis"));
            event.setSmsContent(resultSet.getString("sms_content"));
            event.setLocation(resultSet.getString("location"));
            event.setTranscription(resultSet.getString("transcription"));
            event.setTranscriptionDone(resultSet.getBoolean("transcription_is_done"));
        }
        ps.close();
        return event;
    }

    @Override
    public List<Event> selectAll() throws SQLException {
        List<Event> list = new ArrayList<>();
        Statement st = SingletonConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = st.executeQuery("select * from t_events");
        while(resultSet.next()){
            Event event = new Event();
            event.setId(resultSet.getLong("id"));
            event.setEventId(resultSet.getString("event_id"));
            event.setRefIdNumero(resultSet.getLong("ref_id_numero"));
            event.setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultSet.getTimestamp("start_date")));
            event.setDuration(resultSet.getString("duration"));
            event.setEventType(resultSet.getString("event_type"));
            event.setDirection(resultSet.getString("direction"));
            event.setRelevancy(resultSet.getString("relevancy"));
            event.setCallerId(resultSet.getString("caller_id"));
            event.setCallerImei(resultSet.getString("caller_imei"));
            event.setCallerImsi(resultSet.getString("caller_imsi"));
            event.setTargetName(resultSet.getString("target_name"));
            event.setCalledId(resultSet.getString("called_id"));
            event.setCalledImei(resultSet.getString("called_imei"));
            event.setCalledImsi(resultSet.getString("called_imsi"));
            event.setSynopsis(resultSet.getString("synopsis"));
            event.setSmsContent(resultSet.getString("sms_content"));
            event.setLocation(resultSet.getString("location"));
            event.setTranscription(resultSet.getString("transcription"));
            event.setTranscriptionDone(resultSet.getBoolean("transcription_is_done"));
            list.add(event);
        }
        st.close();
        return list;

    }

    @Override
    public List<Event> selectFromForeignKey(long id) throws SQLException {
        List<Event> list = new ArrayList<>();
        PreparedStatement ps  = SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_events where ref_id_numero = ?");
        ps.setLong(1,id);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            Event event = new Event();
            event.setId(resultSet.getLong("id"));
            event.setEventId(resultSet.getString("event_id"));
            event.setRefIdNumero(resultSet.getLong("ref_id_numero"));
            event.setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultSet.getTimestamp("start_date")));
            event.setDuration(resultSet.getString("duration"));
            event.setEventType(resultSet.getString("event_type"));
            event.setDirection(resultSet.getString("direction"));
            event.setRelevancy(resultSet.getString("relevancy"));
            event.setCallerId(resultSet.getString("caller_id"));
            event.setCallerImei(resultSet.getString("caller_imei"));
            event.setCallerImsi(resultSet.getString("caller_imsi"));
            event.setTargetName(resultSet.getString("target_name"));
            event.setCalledId(resultSet.getString("called_id"));
            event.setCalledImei(resultSet.getString("called_imei"));
            event.setCalledImsi(resultSet.getString("called_imsi"));
            event.setSynopsis(resultSet.getString("synopsis"));
            event.setSmsContent(resultSet.getString("sms_content"));
            event.setLocation(resultSet.getString("location"));
            event.setTranscription(resultSet.getString("transcription"));
            event.setTranscriptionDone(resultSet.getBoolean("transcription_is_done"));
            list.add(event);
        }
        ps.close();
        return list;
    }

    public List<Event> selectFromForeignKeyANDSearchNumero(long id,OptionSearch optionSearch) throws SQLException {
        List<Event> list = new ArrayList<>();
        PreparedStatement ps  = SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_events where ref_id_numero = ? AND (caller_id LIKE ? OR called_id LIKE ?)");
        ps.setLong(1,id);
        String keySearch = "%" + optionSearch.getKeySearch() + "%";
        ps.setString(2,keySearch); //  recherche sur base d'un numéro
        ps.setString(3,keySearch); //  recherche sur base d'un numéro
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            Event event = new Event();
            event.setId(resultSet.getLong("id"));
            event.setEventId(resultSet.getString("event_id"));
            event.setRefIdNumero(resultSet.getLong("ref_id_numero"));
            event.setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultSet.getTimestamp("start_date")));
            event.setDuration(resultSet.getString("duration"));
            event.setEventType(resultSet.getString("event_type"));
            event.setDirection(resultSet.getString("direction"));
            event.setRelevancy(resultSet.getString("relevancy"));
            event.setCallerId(resultSet.getString("caller_id"));
            event.setCallerImei(resultSet.getString("caller_imei"));
            event.setCallerImsi(resultSet.getString("caller_imsi"));
            event.setTargetName(resultSet.getString("target_name"));
            event.setCalledId(resultSet.getString("called_id"));
            event.setCalledImei(resultSet.getString("called_imei"));
            event.setCalledImsi(resultSet.getString("called_imsi"));
            event.setSynopsis(resultSet.getString("synopsis"));
            event.setSmsContent(resultSet.getString("sms_content"));
            event.setLocation(resultSet.getString("location"));
            event.setTranscription(resultSet.getString("transcription"));
            event.setTranscriptionDone(resultSet.getBoolean("transcription_is_done"));
            list.add(event);
        }
        ps.close();
        return list;

    }

    public List<Event> selectFromForeignKeyANDSearch(long id,OptionSearch optionSearch) throws SQLException {
        List<Event> list = new ArrayList<>();
        PreparedStatement ps  = SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_events where ref_id_numero = ? AND synopsis LIKE ?");
        ps.setLong(1,id);
        String keySearch = "%" + optionSearch.getKeySearch() + "%";
        ps.setString(2,keySearch);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            Event event = new Event();
            event.setId(resultSet.getLong("id"));
            event.setEventId(resultSet.getString("event_id"));
            event.setRefIdNumero(resultSet.getLong("ref_id_numero"));
            event.setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultSet.getTimestamp("start_date")));
            event.setDuration(resultSet.getString("duration"));
            event.setEventType(resultSet.getString("event_type"));
            event.setDirection(resultSet.getString("direction"));
            event.setRelevancy(resultSet.getString("relevancy"));
            event.setCallerId(resultSet.getString("caller_id"));
            event.setCallerImei(resultSet.getString("caller_imei"));
            event.setCallerImsi(resultSet.getString("caller_imsi"));
            event.setTargetName(resultSet.getString("target_name"));
            event.setCalledId(resultSet.getString("called_id"));
            event.setCalledImei(resultSet.getString("called_imei"));
            event.setCalledImsi(resultSet.getString("called_imsi"));
            event.setSynopsis(resultSet.getString("synopsis"));
            event.setSmsContent(resultSet.getString("sms_content"));
            event.setLocation(resultSet.getString("location"));
            event.setTranscription(resultSet.getString("transcription"));
            event.setTranscriptionDone(resultSet.getBoolean("transcription_is_done"));
            list.add(event);
        }
        ps.close();
        return list;

    }

    public List<Event> selectFromForeignKeyAndOption(long id,OptionSearch optionSearch) throws SQLException {
        List<Event> list = new ArrayList<>();
        PreparedStatement ps  = SingletonConnection.getInstance().getConnection().prepareStatement("select * from t_events where ref_id_numero = ? AND relevancy = ? AND start_date BETWEEN ? AND ?");
        ps.setLong(1,id);
        ps.setString(2,optionSearch.getRelevancy());
        ps.setTimestamp(3,Timestamp.valueOf(optionSearch.getDateBasse()));
        ps.setTimestamp(4,Timestamp.valueOf(optionSearch.getDateHaute()));
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            Event event = new Event();
            event.setId(resultSet.getLong("id"));
            event.setEventId(resultSet.getString("event_id"));
            event.setRefIdNumero(resultSet.getLong("ref_id_numero"));
            event.setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultSet.getTimestamp("start_date")));
            event.setDuration(resultSet.getString("duration"));
            event.setEventType(resultSet.getString("event_type"));
            event.setDirection(resultSet.getString("direction"));
            event.setRelevancy(resultSet.getString("relevancy"));
            event.setCallerId(resultSet.getString("caller_id"));
            event.setCallerImei(resultSet.getString("caller_imei"));
            event.setCallerImsi(resultSet.getString("caller_imsi"));
            event.setTargetName(resultSet.getString("target_name"));
            event.setCalledId(resultSet.getString("called_id"));
            event.setCalledImei(resultSet.getString("called_imei"));
            event.setCalledImsi(resultSet.getString("called_imsi"));
            event.setSynopsis(resultSet.getString("synopsis"));
            event.setSmsContent(resultSet.getString("sms_content"));
            event.setLocation(resultSet.getString("location"));
            event.setTranscription(resultSet.getString("transcription"));
            event.setTranscriptionDone(resultSet.getBoolean("transcription_is_done"));
            list.add(event);
        }
        ps.close();
        return list;


    }
}
