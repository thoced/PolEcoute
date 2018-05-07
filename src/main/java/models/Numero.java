package models;

public class Numero {

    private long id;

    private String callId;

    private String targetName;

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

    public String getTargetName(){
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public long getRefIdDossier() {
        return refIdDossier;
    }

    public void setRefIdDossier(long refIdDossier) {
        this.refIdDossier = refIdDossier;
    }

    @Override
    public String toString() {
        return callId + " - " + targetName;
    }
}
