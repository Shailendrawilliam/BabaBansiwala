package com.bababansiwalanew.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLParser {

    /**
     * Parses the given XML string and extracts the errCode and errInfo values.
     *
     * @param xmlString the XML string to parse
     * @return a Map containing "errCode" and "errInfo" keys with their respective values
     * @throws Exception if parsing fails or attributes are missing
     */
    public static Map<String, String> parseErrorResponse(String xmlString) throws Exception {
        Map<String, String> responseMap = new HashMap<>();

        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML string
        Document document = builder.parse(new java.io.ByteArrayInputStream(xmlString.getBytes()));

        // Get the <Resp> element
        NodeList respNodes = document.getElementsByTagName("Resp");
        if (respNodes.getLength() > 0) {
            Element respElement = (Element) respNodes.item(0);

            // Extract errCode and errInfo attributes
            String errCode = respElement.getAttribute("errCode");
            String errInfo = respElement.getAttribute("errInfo");

            // Put values into the map
            responseMap.put("errCode", errCode);
            responseMap.put("errInfo", errInfo);
        } else {
            throw new Exception("No <Resp> element found in the XML.");
        }

        return responseMap;
    }

    public static String addOrUpdateTags(String xmlString) throws Exception {
        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML string
        Document document = builder.parse(new java.io.ByteArrayInputStream(xmlString.getBytes()));

        // Get the <Resp> element
        NodeList respNodes = document.getElementsByTagName("Resp");
        if (respNodes.getLength() > 0) {
            Element respElement = (Element) respNodes.item(0);

            // Add or update the "wadh" attribute
            respElement.setAttribute("wadh", "18f4CEiXeXcfGXvgWA/blxD+w2pw7hfQPY45JMytkPw=");

            // Add or update the "fType" attribute
            respElement.setAttribute("fType", "2");
        } else {
            throw new Exception("No <Resp> element found in the XML.");
        }

        // Convert the updated Document back to a string
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        java.io.StringWriter writer = new java.io.StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);

        return writer.toString();
    }
    public static void main(String[] args) {
        String xmlResponse = "<PidData>\n" +
                "    <Resp errCode=\"700\" errInfo=\"Capture timed out\" fCount=\"0\" fType=\"0\" iCount=\"0\" iType=\"0\" nmPoints=\"0\" pCount=\"0\" pType=\"0\" qScore=\"0\"/>\n" +
                "</PidData>";

        try {
            // Call the method and get the result
            Map<String, String> result = parseErrorResponse(xmlResponse);

            // Print the results
            System.out.println("Error Code: " + result.get("errCode"));
            System.out.println("Error Info: " + result.get("errInfo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
