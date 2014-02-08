package utilities;

import java.util.regex.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ParseSvg {
    //
    // "M 82.8864,56.6103 A 32.5,32.5 0 0 0 83.6939,85.0485"
    public static Pattern arcPattern = 
        // Pattern.compile("([Mm]\\s*([0-9.+-]+),([0-9.+-]+))|([Aa]\\s*([0-9.+-]+),([0-9.+-]+)(\\s*0)*\\s*([0-9.+-]+),([0-9.+-]+))");
        Pattern.compile("([mM]([ 0-9+-.,]+)[aA]([ 0-9+-.,]+))");

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
                    
                    if (qName.equalsIgnoreCase("PATH")) {
                        String data = attributes.getValue("d");
                        System.out.println("***" + data);
                        Matcher m = arcPattern.matcher(data);
                        if (m.matches()) {
                            System.out.print("*** MATCHES *** ");
                            for (int i = 0; i <= m.groupCount(); i++) {
                                System.out.printf("\"%s\" ", m.group(i));
                            }
                            System.out.println();
                        }
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

            saxParser.parse("sdl/SmartDrive.svg", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}