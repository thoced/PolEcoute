package export;

import models.Event;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
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

    public void replace(List<Event> list) throws IOException, InvalidFormatException {
        FileInputStream fileInputStream = new FileInputStream("/home/thonon/IdeaProjects/PolEcoute/src/main/resources/template.docx");
        XWPFDocument doc = new XWPFDocument(fileInputStream);

        List<XWPFParagraph> paragraphs = doc.getParagraphs();

        for(XWPFParagraph paragraph : paragraphs){
            List<XWPFRun> runs = paragraph.getRuns();

            for(XWPFRun run : runs){

                String text = run.getText(0);
                if(text != null &&  text.contains("%EVENTID%")){
                   text = text.replace("%EVENTID%",list.get(0).getEventId());

                }

                if(text != null &&  text.contains("%DATE%")){

                    text = text.replace("%DATE%",list.get(0).getStartDate());

                }

                run.setText(text,0);
            }

        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        doc.write(fileOutputStream);
        doc.close();
        fileOutputStream.close();
        fileInputStream.close();
    }
}
