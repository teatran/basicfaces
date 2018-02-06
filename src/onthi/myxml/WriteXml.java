package onthi.myxml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class WriteXml
{

	public static void main(String[] args)
	{
		WriteXml writeXml = new WriteXml();
		writeXml.createXml();

	}
	
	public void createXml()
	{
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
			
			Document document = docBuilder.newDocument();
			
			// Define root element "school"
			Element root = document.createElement("school");
			document.appendChild(root);
			
			// add attribute to school
			Attr attr = document.createAttribute("id");
			attr.setValue("1");
			root.setAttributeNode(attr);
			
			// student element
			Element student01 = document.createElement("student");
			root.appendChild(student01);
			
			// firstname element
			Element firstname = document.createElement("firstname");
			firstname.appendChild(document.createTextNode("Trần Việt"));
			student01.appendChild(firstname);
			
			// lastname element
			Element lastname = document.createElement("lastname");
			lastname.appendChild(document.createTextNode("Thế Anh"));
			student01.appendChild(lastname);
			
			// create and write to XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// pretty write
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File("D:\\output.xml"));
			
			transformer.transform(domSource, streamResult);
			System.out.println("Write XML file successfully.");
		}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		catch (TransformerException te) {
			// TODO Auto-generated catch block
			te.printStackTrace();
		}
		
	}

}
