package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Event {

    private long id;

    private String eventId;

    private StringProperty startDate = new SimpleStringProperty();

    private String startTime;

    private String duration;

    private StringProperty eventType = new SimpleStringProperty();

    private String direction;

    private String relevancy;

    private StringProperty callerId = new SimpleStringProperty();

    private String callerImei;

    private String callerImsi;

    private String targetName;

    private StringProperty calledId = new SimpleStringProperty();

    private String calledImei;

    private String calledImsi;

    private StringProperty synopsis = new SimpleStringProperty();

    private String location;

    private String transcription;

    private String smsContent;

    private BooleanProperty transcriptionDone = new SimpleBooleanProperty();

    private long refIdNumero;

    public Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStartDate() {
        return startDate.get();
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEventType() {
        return eventType.get();
    }

    public StringProperty eventTypeProperty() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType.set(eventType);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRelevancy() {
        return relevancy;
    }

    public void setRelevancy(String relevancy) {
        this.relevancy = relevancy;
    }

    public String getCallerId() {
        return callerId.get();
    }

    public StringProperty callerIdProperty() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId.set(callerId);
    }

    public String getCalledId() {
        return calledId.get();
    }

    public StringProperty calledIdProperty() {
        return calledId;
    }

    public void setCalledId(String calledId) {
        this.calledId.set(calledId);
    }

    public String getCallerImei() {
        return callerImei;
    }

    public void setCallerImei(String callerImei) {
        this.callerImei = callerImei;
    }

    public String getCallerImsi() {
        return callerImsi;
    }

    public void setCallerImsi(String callerImsi) {
        this.callerImsi = callerImsi;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }



    public String getCalledImei() {
        return calledImei;
    }

    public void setCalledImei(String calledImei) {
        this.calledImei = calledImei;
    }

    public String getCalledImsi() {
        return calledImsi;
    }

    public void setCalledImsi(String calledImsi) {
        this.calledImsi = calledImsi;
    }

    public String getSynopsis() {
        return synopsis.get();
    }

    public StringProperty synopsisProperty() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis.set(synopsis);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public boolean isTranscriptionDone() {
        return transcriptionDone.get();
    }

    public BooleanProperty transcriptionDoneProperty() {
        return transcriptionDone;
    }

    public void setTranscriptionDone(boolean transcriptionDone) {
        this.transcriptionDone.set(transcriptionDone);
    }

    public long getRefIdNumero() {
        return refIdNumero;
    }

    public void setRefIdNumero(long refIdNumero) {
        this.refIdNumero = refIdNumero;
    }


    @Override
    public String toString(){

        return   startDate + " " + direction + " " + callerId + " -> " + calledId + " : " + synopsis;
    }
}
