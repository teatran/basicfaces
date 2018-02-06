package onthi.myxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



public class ReadXML
{
	private final static String FILENAME = "D:\\test.xml"; 
	
	public static void main(String[] args) 
	{
		ReadXML readXml = new ReadXML();
		readXml.useTag();
	}
	
	public void useTag() 
	{
		try {
			File fXmlFile = new File(FILENAME);
			/*InputStream inputStream = new FileInputStream(fXmlFile);
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			InputSource inputSource = new InputSource(reader);
			inputSource.setEncoding("UTF-8");*/
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.parse(fXmlFile);   // or (inputSource)
			
			doc.getDocumentElement().normalize();   // optional, but recommended
			
			Node root = doc.getDocumentElement();
			System.out.println("Root element: " + root.getNodeName());
			
			// get all the 'staff' node
			NodeList staffs = doc.getElementsByTagName("staff");
			System.out.println("-------------------------------");
			
			for (int i = 0; i < staffs.getLength(); i++) {
				Node node = staffs.item(i);
				System.out.println("\nCurrent Element: " + node.getNodeName());
				// skip the Text element
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element staff = (Element) node;
					System.out.println("Staff id: " + staff.getAttribute("id"));
					System.out.println("First name: " +
							staff.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last name: " + 
							staff.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Salary: " + 
							staff.getElementsByTagName("salary").item(0).getTextContent());
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
