package dao;

import MainView.SingletonConnection;
import models.Event;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                    "ref_id_numero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }




        for(Event model: listEvents) {

            try {
                ps.setString(1, model.getEventId());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                //LocalDate localDate = LocalDate.parse(model.getStartDate() + " " + model.getStartTime(),formatter);
                LocalDateTime localDateTime = LocalDateTime.parse(model.getStartDate() + " " + model.getStartTime(), formatter);
                Timestamp timestamp = Timestamp.valueOf(localDateTime);
                ps.setTimestamp(2, timestamp, Calendar.getInstance(TimeZone.getTimeZone(SingletonConnection.TIME_ZONE)));

                ps.setString(3, model.getDuration());
                ps.setString(4, model.getEventType());
                ps.setString(5, model.getDirection());
                ps.setString(6, model.getRelevancy());
                ps.setString(7, model.getCallerId());
                ps.setString(8, model.getCallerImei());
                ps.setString(9, model.getCallerImsi());
                ps.setString(10, model.getTargetName());
                ps.setString(11, model.getCalledId());
                ps.setString(12, model.getCalledImei());
                ps.setString(13, model.getCalledImsi());
                ps.setString(14, model.getSynopsis());
                ps.setString(15, model.getSmsContent());
                ps.setString(16, model.getLocation());
                ps.setString(17, model.getTranscription());
                ps.setLong(18, model.getRefIdNumero());

                ps.executeUpdate();
            }catch(SQLException sqe){

            }
        }

        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                "ref_id_numero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        ps.setString(1,model.getEventId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withLocale(Locale.ROOT);
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
        ps.setLong(18,model.getRefIdNumero());

        ps.executeUpdate();
        ps.close();

    }



    @Override
    public void delete(long id) throws SQLException {

    }

    @Override
    public void update(Event model) {

    }

    @Override
    public Event find(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Event> selectAll() throws SQLException {
        return null;
    }

    @Override
    public List<Event> selectFromForeignKey(long id) throws SQLException {
        return null;
    }
}
