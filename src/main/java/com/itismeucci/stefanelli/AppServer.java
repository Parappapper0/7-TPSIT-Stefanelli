package com.itismeucci.stefanelli;

import java.io.IOException;
import java.net.ServerSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class AppServer 
{
    public static void main( String[] args )
    {
        ServerSocket serverSocket;
        try {serverSocket = new ServerSocket(6789);} catch (IOException e) {e.printStackTrace(); return;}
        System.out.println("[SERVER] Server aperto su porta 6789");

        while(true) {

            Server server = new Server();
            server.accept(serverSocket);
            XML_JSON_Thread thread = new XML_JSON_Thread(server);
            thread.start();
        }
    }

    private static class XML_JSON_Thread extends Thread {

        private Server server;

        public XML_JSON_Thread(Server server) {

            this.server = server;
        }

        @Override
        public void run() {

            server.send("Connessione con il server riuscita, sei il client #" + server.clientID + "\n");
            System.out.println("[SERVER] " + server.receive());

            Persona p = new Persona("Nome", "Cognome", 18);
            Persona po = new Persona("Federico", "Ciaschi", 18);
            XmlMapper xmlMapper = new XmlMapper();
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                server.send(xmlMapper.writeValueAsString(p) + "\n");
                server.send(objectMapper.writeValueAsString(po) + "\n");
            } catch (JsonProcessingException e) { e.printStackTrace(); }
            

            server.send("Connessione terminata, numero indovinato\n");
            System.out.println("[SERVER] Connessione con il Client #" + server.clientID + " terminata");
            server.closeClient();
        }
    }
}
