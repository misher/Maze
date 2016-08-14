import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * Created by A.V.Tsaplin on 14.08.2016.
 */


public class ReadXMLFile {

    public static void main(String argv[]) {


        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                private String pName;
                private String listName;

                private boolean managedObjectPlaceFlag = false;
                private boolean listPlaceFlag = false;
                private boolean itemPlaceFlag = false;
                private boolean pPlaceFlag = false;


                ManagedObjects mo;
                List<ManagedObjects> listManagedObjects = new ArrayList<>();

                List<Object> pItemList = new ArrayList<>();
                Map<String, Object> pItem = new HashMap<>();


                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("managedObject")) {
                        System.out.println("Start Element :" + qName);
                        managedObjectPlaceFlag = true;
                        mo = new ManagedObjects(attributes.getValue("class"));
                    }

                    if (managedObjectPlaceFlag) {

                        if (qName.equalsIgnoreCase("p")) {
                            System.out.println("Start Element :" + qName + ". Field name = " + attributes.getValue("name") + ".");
                            pPlaceFlag = true;
                            pName = attributes.getValue("name");
                        }

                        if (qName.equalsIgnoreCase("list")) {
                            System.out.println("Start Element :" + qName);
                            listPlaceFlag = true;
                            listName = attributes.getValue("name");
                        }

                        if (qName.equalsIgnoreCase("item") && listPlaceFlag) {
                            System.out.println("Start Element :" + qName);
                            itemPlaceFlag = true;
                        }

                    }

                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                    if (qName.equalsIgnoreCase("managedObject")) {
                        System.out.println("End Element :" + qName);
                        managedObjectPlaceFlag = false;
                        listManagedObjects.add(mo);
                        mo = null;
                    }

                    if (qName.equalsIgnoreCase("p")) {
                        pPlaceFlag = false;
                        System.out.println("End Element :" + qName);
                    }

                    if (qName.equalsIgnoreCase("list")) {
                        listPlaceFlag = false;
                        List<Object> curList = new ArrayList<>();
                        for (Object aPList : pItemList) {
                            curList.add(aPList);
                        }
                        mo.addFields(listName, curList);
                        pItemList.clear();
                        System.out.println("End Element :" + qName);
                    }

                    if (qName.equalsIgnoreCase("item")) {
                        itemPlaceFlag = false;
                        Map<String, Object> curItem = new HashMap<>();
                        curItem.putAll(pItem);
                        pItemList.add(curItem);
                        pItem.clear();
                        System.out.println("End Element :" + qName);
                    }

                    if (qName.equalsIgnoreCase("raml")) {
                        System.out.println("End of file :" + qName);
                        ParseResultInList.setParseResultList(listManagedObjects);
                    }

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    String value;

                    if (pPlaceFlag && !itemPlaceFlag && !listPlaceFlag) {
                        value = new String(ch, start, length);
                        System.out.println(value);
                        mo.addFields(pName, value);
                    }

                    if (pPlaceFlag && itemPlaceFlag && listPlaceFlag) {
                        value = new String(ch, start, length);
                        System.out.println(value);
                        pItem.put(pName, value);
                    }

                    if (pPlaceFlag && !itemPlaceFlag && listPlaceFlag) {
                        value = new String(ch, start, length);
                        System.out.println(value);
                        pItemList.add(value);
                    }

                }

            };

            saxParser.parse("src\\main\\resources\\example.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
