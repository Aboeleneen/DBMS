package eg.edu.alexu.csd.oop.db.cs30;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.FileBuilding;

public class FileBuilder implements FileBuilding {

	private String[] c;
	private String[] h;

	private FileBuilder() {
	}

	private static FileBuilder fileBuilder;

	public static FileBuilder getInstance() {
		if (fileBuilder == null) {
			fileBuilder = new FileBuilder();
		}
		return fileBuilder;
	}

	public Boolean createDB(String name) {
		File theDir = new File(name);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;

			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				return true;
			}

		}
		return false;

	}

	public void createTable(String types[], String[] fields, String TableName, String DBName) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(TableName);
			doc.appendChild(rootElement);
			rootElement.setAttribute("Data", "False");
			// staff elements
			for (int i = 0; i < 1; i++) {
				Element row = doc.createElement("row");
				for (int j = 0; j < fields.length; j++) {
					Element column = doc.createElement(fields[j]);
					column.setAttribute("Type", types[j].toLowerCase());
					row.appendChild(column);
				}
				rootElement.appendChild(row);

			}
			// set attribute to staff element

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(DBName + "\\" + TableName + ".xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
			dtd(new File(DBName + "\\" + TableName + ".dtd"), TableName, fields);
			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
		} catch (TransformerException tfe) {

		}
	}

	public boolean write(Object[][] content, String[] fields, String TableName, String DBName) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(TableName);
			doc.appendChild(rootElement);
			rootElement.setAttribute("Data", "True");
			// staff elements
			for (int i = 0; i < content.length; i++) {
				Element row = doc.createElement("row");
				for (int j = 0; j < fields.length; j++) {
					Element column = doc.createElement(fields[j]);
					if (content[i][j] instanceof Integer) {
						column.setAttribute("Type", "int");
					} else
						column.setAttribute("Type", "varchar");

					column.appendChild(doc.createTextNode(content[i][j].toString()));

					row.appendChild(column);
				}
				rootElement.appendChild(row);

			}
			// set attribute to staff element

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(DBName + "\\" + TableName + ".xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
			dtd(new File(DBName + "\\" + TableName + ".dtd"), TableName, fields);
			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			return false;
		} catch (TransformerException tfe) {
			return false;
		}
		return true;

	}

	@Override
	public Boolean dropDB(String name) {
		File directory = new File(name);
		return deleteDirectory(directory);

	}

	public boolean deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
		return (directory.delete());

	}

	private void dtd(File directory, String TableName, String[] fields) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(directory, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.println("<!DOCTYPE " + TableName + " [");
		writer.println();
		writer.println("<!ELEMENT " + TableName + " (row)+>");
		writer.println();
		writer.print("<!ELEMENT row (");
		for (int i = 0; i < fields.length - 1; i++) {
			writer.print(fields[i] + ",");

		}
		writer.println(fields[fields.length - 1] + ")>");
		writer.println();

		for (int i = 0; i < fields.length; i++) {
			writer.println("<!ELEMENT " + fields[i] + " (#PCDATA)>");
			writer.println();

		}
		writer.print("]>");

		writer.close();

	}

	public ArrayList getTables(String Db) {
		ArrayList b = new ArrayList();
		String[] tables = null;
		File folder = new File(Db);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().contains("xml")) {
				b.add(file.getName().toString().substring(0, (file.getName().toString()).length() - 4));
			}
		}
		tables = new String[b.size()];
		for (int i = 0; i < b.size(); i++) {
			tables[i] = ((String) b.get(i)).substring(0, ((String) b.get(i)).length() - 4);

		}
		return b;

	}

	public String[] getArrayList() {

		return c;

	}

	public String[] getTypesList() {

		return h;

	}

	@Override
	public Object[][] read(String TableName, String DBName) {
		Object[][] data = null;
		File fXmlFile = new File(DBName + "\\" + TableName + ".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document d = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			d = dBuilder.parse(DBName + "\\" + TableName + ".xml");
			
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList rootelements = d.getElementsByTagName(TableName);
		
		Element rootelement = (Element) rootelements.item(0);
				if (rootelement.getAttribute("Data").equals("True")) {
			
			NodeList root = d.getElementsByTagName("row");
			for (int j = 0; j < root.getLength(); j++) {
				Element e = (Element) root.item(j);
				NodeList root2 = e.getChildNodes();
				if (j == 0) {
					data = new Object[root.getLength()][root2.getLength()];
					c = new String[root2.getLength()];
					h = new String[root2.getLength()];

				}

				for (int k = 0; k < root2.getLength(); k++) {
					Element e2 = (Element) root2.item(k);
					if (j == 0) {
						c[k] = (e2.getTagName());
						h[k] = e2.getAttribute("Type");
					}
					String m = e2.getAttribute("Type");
					if (m.equals("int")) {
						data[j][k] = Integer.parseInt(e2.getTextContent());
					} else
						data[j][k] = e2.getTextContent();
				}

			}
		} else {

			NodeList root = d.getElementsByTagName("row");
			for (int j = 0; j < root.getLength(); j++) {
				Element e = (Element) root.item(j);
				NodeList root2 = e.getChildNodes();
				if (j == 0) {
					data = null;
					c = new String[root2.getLength()];
					h = new String[root2.getLength()];

				}

				for (int k = 0; k < root2.getLength(); k++) {
					Element e2 = (Element) root2.item(k);
					if (j == 0) {
						c[k] = (e2.getTagName());
						h[k] = e2.getAttribute("Type");
					}
					String m = e2.getAttribute("Type");

				}

			}

		}

		return data;
	}
}