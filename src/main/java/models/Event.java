package models;

public class Event {

    private long id;

    private String eventId;

    private String startDate;

    private String startTime;

    private String duration;

    private String eventType;

    private String direction;

    private String relevancy;

    private String callerId;

    private String callerImei;

    private String callerImsi;

    private String targetName;

    private String calledId;

    private String calledImei;

    private String calledImsi;

    private String synopsis;

    private String location;

    private String transcription;

    private String smsContent;

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
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
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

    public String getCalledId() {
        return calledId;
    }

    public void setCalledId(String calledId) {
        this.calledId = calledId;
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
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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

    public long getRefIdNumero() {
        return refIdNumero;
    }

    public void setRefIdNumero(long refIdNumero) {
        this.refIdNumero = refIdNumero;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventId='" + eventId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", duration='" + duration + '\'' +
                ", eventType='" + eventType + '\'' +
                ", direction='" + direction + '\'' +
                ", relevancy='" + relevancy + '\'' +
                ", callerId='" + callerId + '\'' +
                ", callerImei='" + callerImei + '\'' +
                ", callerImsi='" + callerImsi + '\'' +
                ", targetName='" + targetName + '\'' +
                ", calledId='" + calledId + '\'' +
                ", calledImei='" + calledImei + '\'' +
                ", calledImsi='" + calledImsi + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", location='" + location + '\'' +
                ", transcription='" + transcription + '\'' +
                ", smsContent='" + smsContent + '\'' +
                ", refIdNumero=" + refIdNumero +
                '}';
    }
}
