package com.itismeucci.stefanelli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class AppClient {
    public static void main(String[] args) {

        Client client = new Client();
        XmlMapper xmlMapper = new XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        
        String serverInput;

        client.start("localhost", 6789);

        serverInput = client.receive();
        System.out.println(serverInput);

        client.send("Connessione riuscita\n");


        
        try {
            System.out.println(xmlMapper.readValue(client.receive(), Persona.class));
            System.out.println(objectMapper.readValue(client.receive(), Persona.class));
        } catch (Exception e) {
            e.printStackTrace();
        }


        client.close();
    }
}
