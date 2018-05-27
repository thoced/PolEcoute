package parserEventsXml;

import models.Event;
import models.Numero;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserEventDomXml {

    private File file;

    private Numero numero;

    private List<Event> listEvents;

    public ParserEventDomXml(File file,Numero numero) throws IOException, SAXException, ParserConfigurationException {
        this.file = file;
        this.numero = numero;
        this.listEvents = new ArrayList<>();

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        final DocumentBuilder builder = factory.newDocumentBuilder();

        final Document document = builder.parse(file);

        final Element racine = document.getDocumentElement();


        // parse des Events
        final NodeList racineNodes = racine.getChildNodes();
         for(int i=0;i<racineNodes.getLength();i++){

             if(racineNodes.item(i).getNodeName().equals("Event"))
                parseEvent(racineNodes.item(i));
        }

    }

    private void parseEvent(Node item) {

        Event event = new Event();
        event.setRefIdNumero(numero.getId());

        final NodeList racineNodes = item.getChildNodes();

        for (int i = 0; i < racineNodes.getLength(); i++) {

            Node n = racineNodes.item(i);

            switch (n.getNodeName()) {

                case "Event_ID": {
                    event.setEventId(n.getTextContent());
                    break;
                }

                case "Start-Date": {
                    event.setStartDate(n.getTextContent());
                    break;
                }

                case "Start-Time": {
                    event.setStartTime(n.getTextContent());
                    break;
                }

                case "Duration": {
                    event.setDuration(n.getTextContent());
                    break;
                }

                case "Event-Type": {
                    event.setEventType(n.getTextContent());
                    break;
                }

                case "Direction": {
                    event.setDirection(n.getTextContent());
                    break;
                }

                case "Caller-ID": {
                    event.setCallerId(n.getTextContent());
                    break;
                }

                case "Target-Name": {
                    event.setTargetName(n.getTextContent());
                    break;
                }

                case "Caller-IMEI": {
                    event.setCallerImei(n.getTextContent());
                    break;
                }

                case "Caller-IMSI": {
                    event.setCallerImsi(n.getTextContent());
                    break;
                }

                case "Called-Number": {
                    event.setCalledId(n.getTextContent());
                    break;
                }

                case "Called-IMEI": {
                    event.setCalledImei(n.getTextContent());
                    break;
                }

                case "Called-IMSI": {
                    event.setCalledImsi(n.getTextContent());
                    break;
                }

                case "Synopsis": {
                    event.setSynopsis(n.getTextContent());
                    break;
                }

                case "Relevancy": {
                    event.setRelevancy(n.getTextContent());
                    break;
                }

                case "Locations": {
                      // appel récursif pour rechercher la bonne location
                      recursiveLocation(n.getChildNodes(),event);
                    break;
                }

                case "SMS-Content": {
                    event.setSmsContent(n.getTextContent());
                    break;
                }

                case "Transcription": {
                    event.setTranscription(n.getTextContent());
                    break;
                }
            }


        }

        listEvents.add(event);
    }

    /**
     * fonction recurcive pour rechercher le ChildName Location-GlobalCellId-Address
     * @param childNodes
     * @param event
     */
    private void recursiveLocation(NodeList childNodes,Event event) {
        if(childNodes != null) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (childNodes.item(i).getNodeName().equals("Location-GlobalCellId-Address")) {
                    event.setLocation(childNodes.item(i).getTextContent());
                    return;
                } else {
                    recursiveLocation(childNodes.item(i).getChildNodes(),event);
                }
            }
        }
    }

    public List<Event> getListEvents() {
        return listEvents;
    }

}
