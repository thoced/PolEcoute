package export;

import models.Event;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportRapport {

    private XWPFDocument document;
    private File file;

    public ExportRapport(File file){

        this.file = file;
        document = new XWPFDocument();
    }

    public void export(List<Event> list) throws IOException {

        XWPFParagraph paragraph = document.createParagraph();

        for(Event event : list) {

           XWPFRun run =  paragraph.createRun();
           run.setText("Date et temps: " + event.getStartDate());  run.addTab();  run.setText("Durée: " + event.getDuration());
           run.addCarriageReturn();
           run.setText("Event Type: " + event.getEventType());
           run.addCarriageReturn();
           run.addCarriageReturn();
           run.setText("Numéro appelant: " + event.getCallerId()); run.addTab(); run.setText("Numéro appelé: " + event.getCalledId());
           run.addCarriageReturn();
           run.setText("Imei appelant: " + event.getCallerImei()); run.addTab(); run.setText("Imei appelé: " + event.getCalledImei());
           run.addCarriageReturn();
           run.setText("Imsi appelant: " + event.getCallerImsi()); run.addTab(); run.setText("Imsi appelé: " + event.getCalledImsi());
           run.addCarriageReturn();
           run.addCarriageReturn();
           run.setText("Synopsis:");
           run.addCarriageReturn();
           run.setText(event.getSynopsis());
           run.addCarriageReturn();
           run.addCarriageReturn();
           run.setText("Transcription:");
           run.addCarriageReturn();
           run.setText(event.getTranscription());
           run.addCarriageReturn();

           run.addBreak(BreakType.PAGE);
           run.addBreak(BreakType.TEXT_WRAPPING);


        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        document.write(fileOutputStream);
        document.close();
        fileOutputStream.close();



    }
}
