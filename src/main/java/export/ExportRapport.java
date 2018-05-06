package export;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import models.Event;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.jdom.JDOMException;
import org.jopendocument.dom.ODSingleXMLDocument;
import org.jopendocument.dom.OOUtils;

import java.io.*;
import java.util.List;

public class ExportRapport {

    private XWPFDocument document;
    private File file;

    private ODSingleXMLDocument documentOdt;

    public ExportRapport(File file){

        this.file = file;
        document = new XWPFDocument();
    }

    public void export(List<Event> list) throws IOException {

        XWPFParagraph paragraph = document.createParagraph();

        for(Event event : list) {

           XWPFRun run =  paragraph.createRun();
           run.setText("Date et heure: " + event.getStartDate());  run.addTab();  run.setText("Durée: " + event.getDuration());
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



    public void replace(List<Event> list) throws IOException, InvalidFormatException, XDocReportException, JDOMException {
        // chargement du template

        InputStream inputStream = getClass().getResourceAsStream("/template.odt");

        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(inputStream,TemplateEngineKind.Velocity);
        IContext context = report.createContext();

        int cpt = 0;
        int nb = list.size();

        for(Event event : list) {

            if(event.getRelevancy() != null)
                context.put("relevancy", event.getRelevancy());
            else
                context.put("relevancy","");

            if(event.getEventType() != null)
                context.put("type", event.getEventType());
            else
                context.put("type","");


            if(event.getEventId() != null)
                context.put("eventid", event.getEventId());
            else
                context.put("eventid","");

            if(event.getStartDate() != null)
                context.put("date",event.getStartDate());
            else
                context.put("date","");

            if(event.getDuration() != null)
                context.put("duration",event.getDuration());
            else
                context.put("duraction","");

            if(event.getCallerId() != null)
                context.put("callerid",event.getCallerId());
            else
                context.put("callerid","");


            if(event.getCallerImei() != null)
                context.put("callerimei",event.getCallerImei());
            else
                context.put("callerimei","");


            if(event.getCallerImsi() != null)
                context.put("callerimsi",event.getCallerImsi());
            else
                context.put("callerimsi","");


            if(event.getCalledId() != null)
                context.put("calledid",event.getCalledId());
            else
                context.put("calledid","");


            if(event.getCalledImei() != null)
                context.put("calledimei",event.getCalledImei());
            else
                context.put("calledimei","");


            if(event.getCalledImsi() != null)
                context.put("calledimsi",event.getCalledImsi());
            else
                context.put("calledimsi","");


            if(event.getSynopsis() != null)
                context.put("synopsis",event.getSynopsis());
            else
                context.put("synopsis","");


            if(event.getTranscription() != null)
                context.put("transcription",event.getTranscription());
            else
                context.put("transcription","");


            OutputStream out = new FileOutputStream(System.getProperty("java.io.tmpdir") + "/" + cpt + ".odt");
            report.process(context, out);
            out.close();
            cpt++;
        }

        // utilisation de la librairon jopendocument pour pouvoir merger les différents fichier générés
        ODSingleXMLDocument document = ODSingleXMLDocument.createFromPackage(new File(System.getProperty("java.io.tmpdir") + "/" + 0 + ".odt"));
        for(int i=1;i<nb;i++){
            ODSingleXMLDocument appendDocument = ODSingleXMLDocument.createFromPackage(new File(System.getProperty("java.io.tmpdir") + "/" + i + ".odt"));
            document.add(appendDocument);
        }

        saveDocument(document);

    }

    public void saveDocument(ODSingleXMLDocument document) throws IOException {
        document.saveToPackageAs(file);

    }
}
