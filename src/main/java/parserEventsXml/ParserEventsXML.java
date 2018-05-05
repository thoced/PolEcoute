package parserEventsXml;


import models.Dossier;
import models.Event;
import models.Numero;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserEventsXML extends DefaultHandler {

    private File fileXml;

    private Numero numero;

    private List<Event> listEvents;

    private Event event = null;

    private String text  = null;

    public ParserEventsXML(File fileXml, Numero numero) {
        this.fileXml = fileXml;
        this.numero= numero;

        this.listEvents = new ArrayList<>();

        try {
            listEvents = parseEvents();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (qName){
            case "Event": {
                event = new Event();
                event.setEventId(attributes.getValue("ID"));
                break;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch (qName){
            case "Event":{
                event.setRefIdNumero(numero.getId());
                listEvents.add(event);
                break;
            }
            case "Event_ID":{
                event.setEventId(text);
                break;
            }

            case "Start-Date":{
                event.setStartDate(text);
                break;
            }

            case "Start-Time":{
                event.setStartTime(text);
                break;
            }

            case "Duration": {
                event.setDuration(text);
                break;
            }

            case "Event-Type":{
                event.setEventType(text);
                break;
            }

            case "Direction":{
                event.setDirection(text);
                break;
            }

            case "Caller-ID":{
                event.setCalledId(text);
                break;
            }

            case "Target-Name":{
                event.setTargetName(text);
                break;
            }

            case "Caller-IMEI":{
                event.setCallerImei(text);
                break;
            }

            case "Caller-IMSI":{
                event.setCallerImsi(text);
                break;
            }

            case "Called-Number":{
                event.setCalledId(text);
                break;
            }

            case "Called-IMEI":{
                event.setCalledImei(text);
                break;
            }

            case "Called-IMSI":{
                event.setCalledImsi(text);
                break;
            }

            case "Synopsis":{
                event.setSynopsis(text);
                break;
            }

            case "Relevancy":{
                event.setRelevancy(text);
                break;
            }

            case "Location-GlobalCellId-Address":{
                event.setLocation(text);
                break;
            }

            case "SMS-Content":{
                event.setSmsContent(text);
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = String.copyValueOf(ch,start,length);
    }

    private List<Event> parseEvents() throws ParserConfigurationException, IOException, SAXException {

        SAXParserFactory parserFactor = SAXParserFactory.newInstance();

        SAXParser parser = parserFactor.newSAXParser();

        parser.parse(this.fileXml,this);

        return listEvents;
    }

    public List<Event> getListEvents() {
        return listEvents;
    }
}
