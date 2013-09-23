package org.platform.util;

import java.io.File;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Util {

	
	public static void clientHelp(){
		System.out.println("-----------------------------");
		System.out.println("---------SNS PLATFORM--------");
		System.out.println("-----------------------------");
		System.out.println("---Welcome to SNS Platform---");
		System.out.println("In order to test all features provided by SNPS, select services"
						+  " from the following menu: \n");
		
		System.out.println("1) SayHello");
		System.out.println("2) SendCommand");
		System.out.println("3) SetSamplingPlan");
		System.out.println("4) BuildVirtualSensor");
		System.out.println("5) DiscoverySensorsAndMeasures");
		System.out.println("6) getDataFromSensor");
		System.out.println("0) quit");
	}
	
	public static void discoveryHelp(){
		System.out.println("-----------------------------");
		System.out.println("---------SNS PLATFORM--------");
		System.out.println("-----------------------------");
		System.out.println("---Discovery Commands---");
		System.out.println("In order to test all query provided by SNPS, select a command"
						+  " from the following menu: \n");
		
		System.out.println("---------SENSORS--------");
		System.out.println("------------------------");
		System.out.println("1) getSensor");
		System.out.println("2) getSDesc");
		System.out.println("3) getAllSensors");
		System.out.println("4) sensType");
		System.out.println("5) getSensInfo");
		System.out.println("6) getbsinfo");
		System.out.println("7) getzoneinfo");
		System.out.println("8) getSensorbyzone");
		System.out.println("9) getSensorbynode");
		
		System.out.println("\n-------DETECTIONS-----");
		System.out.println("------------------------");
		System.out.println("10) history");
		System.out.println("11) allhistory");
		System.out.println("12) detection");
		System.out.println("13) detectbydate");
		System.out.println("14) detectbytime");
		System.out.println("15) detectbydateandtime");
		System.out.println("16) detectbyzone");
	}
	
	public static String IdGenerator() {
		UUID idOne = UUID.randomUUID();
		// UUID idTwo = UUID.randomUUID();
		// System.out.println("[GENERATOR: Info] -> Generate id: "+idOne);
		return String.valueOf(idOne);
	}
	
	public static String loadConfiguration(String path){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//description = builder.parse(new File("sensorDescription.xml"));
			Document description = builder.parse(new File(path));
			XPath xpath = XPathFactory.newInstance().newXPath();
			String ip = getIP(xpath, description);
			int port = Integer.parseInt(getPort(xpath, description));
			return "http://"+ip+":"+port+"/wii?wsdl";
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	public static String getIP(XPath xpath, Document document){
		String ip="";
		try{
			XPathExpression expr = xpath.compile("//config/ip/text()");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
			 		//System.out.println(nodes.item(i).getNodeValue()); 
			 		ip = nodes.item(i).getNodeValue();
			}
			return ip;
		}catch(Exception e){
			e.printStackTrace();
			return ip;
		}
	}
	
	public static String getPort(XPath xpath, Document document){
		String port="";
		try{
			XPathExpression expr = xpath.compile("//config/port/text()");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
			 		//System.out.println(nodes.item(i).getNodeValue()); 
			 		port = nodes.item(i).getNodeValue();
			}
			return port;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

}
