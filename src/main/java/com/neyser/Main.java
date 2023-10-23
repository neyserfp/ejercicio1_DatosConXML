package com.neyser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        //Variable de Scaner
        Scanner sc1 = new Scanner(System.in);

        // Referencia del archivo
        // File file = new File("./xml\\xmlData1.xml");
        File file1 = new File("src\\main\\java\\com\\neyser\\xml\\xmlData1.xml");

        // DocumentBuilderFactory y DocumentBuilder -> Genera un documento XML vacío en memoria
        DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder db1 = dbf1.newDocumentBuilder();

        // volcar el contenido del fichero xml al objeto document1
        Document document1 = db1.parse(file1);
        document1.getDocumentElement().normalize();

        // Extraer el nombre del nodo
        System.out.println("Elemento raíz: "+document1.getDocumentElement().getNodeName()+"\n");

        // Volcamos los nodos contenidos en el nodo indicado a través de (getElementByTagName)
        NodeList nodeList1 = document1.getElementsByTagName("pelicula");

        // Busqueda del usuario
        System.out.println("Indique la palabra para buscar películas");
        String busqueda = sc1.nextLine().toUpperCase();
        System.out.println();

        int contador = 0;

        // Se recorre la lista de nodos extraida
        for (int i = 0; i < nodeList1.getLength(); i++)
        {
            // Extrae el nodo actual
            Node node1 = nodeList1.item(i);

            // Devolver un número indicando el tipo de nodo que es
            // (Si es un elemento devolverá 1 y si es un documento devolverá 9)
            if (node1.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element1 = (Element) node1;

                String pelicula = element1.getElementsByTagName("titulo").item(0).getTextContent();
                String director = element1.getElementsByTagName("director").item(0).getTextContent();
                String actor = element1.getElementsByTagName("actor").item(0).getTextContent();

                // Filtrar búsqueda
                if(pelicula.toUpperCase().contains(busqueda) || director.toUpperCase().contains(busqueda) || actor.toUpperCase().contains(busqueda))
                {
                    System.out.println("Pelicula: "+pelicula);
                    System.out.println("Director: "+director);
                    System.out.println("Actor: "+actor);
                    contador++;
                }
                System.out.println();

            }
        }

        if (contador==0){
            System.out.println("No se han producido coincidencias con: " + busqueda);
        }

    }
}