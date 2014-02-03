package utilities;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ParseSvg {

    public static void main(String argv[]) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean started = false;

                public void startElement(String uri, String localName,
                    String qName, Attributes attributes) throws SAXException {

                    System.out.println("start: " + qName + ":" + uri);

                    if (qName.equalsIgnoreCase("G")) {
                        started = true;
                    }

                    for (int i = 0; i < attributes.getLength(); i++) {
                        System.out.printf(" attr: %s=\"%s\"\n", attributes.getQName(i), attributes.getValue(i));
                    }
                }

                public void endElement(String uri, String localName,
                    String qName) throws SAXException {

                    System.out.println("  end: " + qName);

                    if (qName.equalsIgnoreCase("G")) {
                        started = false;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    System.out.printf("chars: %s\n", new String(ch, start, length).trim());
                }
            };

            saxParser.parse("/Users/dcp/temp/test.svg", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}