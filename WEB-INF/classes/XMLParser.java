import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParser {
	public Document loadXMLFile(String filename) throws 
	ParserConfigurationException, SAXException, IOException {
		Document document = null;
		
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		File file = new File(filename);
		
		document = parser.parse(file);
		
		return document;
	}
	
	public Document loadXMLSTring(String xml) throws 
	ParserConfigurationException, SAXException, IOException {
		Document document = null;
		
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		InputSource inputSource = new InputSource(new StringReader(xml));
		
		document = parser.parse(inputSource);
		
		return document;
	}
	
	public void showStations(Document stationsList)  {
		NodeList nodeList = stationsList.getElementsByTagName("marker");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			for (int j =0; j < nodeList.item(i).getAttributes().getLength(); 
					j++) {
				System.out.print(nodeList.item(i).getAttributes().
						item(j).getNodeName() + ": "  + nodeList.item(i).
						getAttributes().
						item(j).getNodeValue() + "; ");
			}
			System.out.println();
		}
	}
}
