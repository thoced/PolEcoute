package models;

public class Numero {

    private long id;

    private String callId;

    private long refIdDossier;

    public Numero() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public long getRefIdDossier() {
        return refIdDossier;
    }

    public void setRefIdDossier(long refIdDossier) {
        this.refIdDossier = refIdDossier;
    }

    @Override
    public String toString() {
        return callId;
    }
}
