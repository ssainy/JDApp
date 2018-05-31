/*
 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */

import java.io.*;

import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class MyXml {
    private static String temp;

    public static String XmlToString(File xmlurl) throws Exception {
        SAXReader rd = new SAXReader();
        //Document doc=rd.read(new File(xmlurl));
        Document doc = rd.read(xmlurl);
        String s = doc.asXML();
        return s;
    }

    /*
        public static void StringToXmlFile(String s,File xmlurl) throws Exception{
            
            Document doc = DocumentHelper.parseText(s);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("GB2312");
            XMLWriter writer = new XMLWriter(new FileWriter(xmlurl),format);
            writer.write(doc);
            writer.close();
       
        }
    */
    public static void StringToXml(String s, String xmlurl) throws Exception {
        Document doc = DocumentHelper.parseText(s);
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GB2312");
        XMLWriter writer = new XMLWriter(new FileWriter(new File(xmlurl)), format);
        writer.write(doc);
        writer.close();
    }

    public static void Read(String Xmlurl) throws Exception {
        SAXReader rd = new SAXReader();
        Document doc = rd.read(new File(Xmlurl));
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        bl(it);
    }

    public static void Read(File f) throws Exception {
        SAXReader rd = new SAXReader();
        Document doc = rd.read(f);
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        bl(it);
    }

    public static void bl(Iterator it) {
        while (it.hasNext()) {
            Object o = it.next();
            Element el_row = (Element) o;
            String name = el_row.getName();
            String str = el_row.getText();
            Iterator it_row = el_row.elementIterator();
            if (it_row.hasNext()) bl(it_row);
            else System.out.println(name + ":" + str);
        }
    }

    public static String Read(String Xmlurl, String Nodename) throws Exception {
        SAXReader rd = new SAXReader();
        Document doc = rd.read(new File(Xmlurl));
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        return bl(it, Nodename);
    }

    public static String Read(File f, String Nodename) throws Exception {
        SAXReader rd = new SAXReader();
        Document doc = rd.read(f);
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        return bl(it, Nodename);
    }

    public static String ReadNt(String xmlstr, String Nodename) throws Exception {
        temp = "";
        Document doc = DocumentHelper.parseText(xmlstr);
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        return bl(it, Nodename);
    }

    public static String bl(Iterator it, String Nodename) {
        while (it.hasNext()) {
            Object o = it.next();
            Element el_row = (Element) o;
            String name = el_row.getName();
            String str = el_row.getText();
            Iterator it_row = el_row.elementIterator();
            if (it_row.hasNext())
                temp = bl(it_row, Nodename);
            else {
                if (name.equals(Nodename))
                    temp = str;
            }
        }
        return temp;
    }


    public static void bl(Iterator it, String Nodename, String Nodevalue, Document doc, File f) throws Exception {
        while (it.hasNext()) {
            Object o = it.next();
            Element el_row = (Element) o;
            String name = el_row.getName();
            //String str = el_row.getText();
            Iterator it_row = el_row.elementIterator();
            if (it_row.hasNext())
                bl(it_row, Nodename, Nodevalue, doc, f);
            else {
                if (name.equals(Nodename)) {
                    el_row.setText(Nodevalue);
                    OutputFormat format = OutputFormat.createPrettyPrint();
                    format.setEncoding("GB2312");
                    XMLWriter writer = new XMLWriter(new FileWriter(f), format);
                    writer.write(doc);
                    writer.close();
                    return;
                }
            }
        }
    }

    public static File Set(File f, String Nodename, String Nodevalue) throws Exception {
        SAXReader rd = new SAXReader();
        Document doc = rd.read(f);
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        bl(it, Nodename, Nodevalue, doc, f);
        return f;
    }

    public static String Set(String s, String Nodename, String Nodevalue) throws Exception {
        System.out.println("IN MyXml.Set....\n");
        Document doc = DocumentHelper.parseText(s);
        System.out.println("Here.....");
        Element root = doc.getRootElement();
        Iterator it = root.elementIterator();
        bl(it, Nodename, Nodevalue);
        root.addAttribute("xmlns","");
        String str = doc.getRootElement().asXML();
        return str;
    }

    public static void bl(Iterator it, String Nodename, String Nodevalue) throws Exception {
        while (it.hasNext()) {
            Object o = it.next();
            Element el_row = (Element) o;
            String name = el_row.getName();
            String str = el_row.getText();
            Iterator it_row = el_row.elementIterator();
            if (it_row.hasNext())
                bl(it_row, Nodename, Nodevalue);
            else {
                if (name.equals(Nodename)) {
                    el_row.setText(Nodevalue);
                    return;
                }
            }
        }
    }
    public static String ReadJsonFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

}
