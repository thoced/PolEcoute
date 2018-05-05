package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OptionSearch {

    private String relevancy;
    private LocalDateTime dateBasse;
    private LocalDateTime dateHaute;

    private static OptionSearch instance = null;

    private OptionSearch(){

        dateBasse = LocalDateTime.now();
        dateHaute = LocalDateTime.now();
    }

    public static OptionSearch getInstance(){
        if(instance == null)
            instance = new OptionSearch();
        return instance;
    }

    public String getRelevancy() {
        return relevancy;
    }

    public void setRelevancy(String relevancy) {
        this.relevancy = relevancy;
    }

    public LocalDateTime getDateBasse() {
        return dateBasse;
    }

    public void setDateBasse(LocalDateTime dateBasse) {
        this.dateBasse = dateBasse;
    }

    public LocalDateTime getDateHaute() {
        return dateHaute;
    }

    public void setDateHaute(LocalDateTime dateHaute) {
        this.dateHaute = dateHaute;
    }
}
