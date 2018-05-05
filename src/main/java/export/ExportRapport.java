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

        for(Event event : list) {
            XWPFParagraph paragraph = document.createParagraph();
           XWPFRun run =  paragraph.createRun();

           run.setText("Date et temps: " + event.getStartDate());
           run.addCarriageReturn();
           run.setText("Event Type: " + event.getEventType());


           run.addBreak(BreakType.PAGE);

        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        document.write(fileOutputStream);
        document.close();



    }
}
