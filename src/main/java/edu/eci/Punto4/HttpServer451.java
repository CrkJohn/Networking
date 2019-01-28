package edu.eci.Punto4;

import java.io.*;
import java.net.*;

public class HttpServer451 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        while(!clientSocket.isClosed()) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (inputLine.contains("html")) {
                    String urlInputLine = "";
                    int i = inputLine.indexOf('/') + 1;
                    System.out.println(i);
                    while (!urlInputLine.endsWith(".html") && i < inputLine.length()) {
                        urlInputLine += (inputLine.charAt(i++));
                    }
                    String urlDirectoryServer = System.getProperty("user.dir") + "\\html\\" + urlInputLine;
                    System.out.println("Directory server " + urlDirectoryServer);
                    try {
                        BufferedReader readerFile = new BufferedReader(new InputStreamReader(new FileInputStream(urlDirectoryServer), "UTF8"));
                        out.println("HTTP/2.0 200 OK");
                        out.println("Content-Type: text/html");
                        out.println("\r\n");
                        while(readerFile.ready()){
                            out.println(readerFile.readLine());
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("Entre : ");
                        out.println("HTTP/2.0 404 Not found.");
                        out.println("Content-Type: text/html");
                        out.println("\r\n");

                    }
                }
                if (!in.ready()) {
                    break;
                }
                out.close();
            }
            in.close();
        }
        clientSocket.close();
        serverSocket.close();
    }


    static String saveHTML(URL urlInput)throws  IOException{
        String htmlPage ="";
        try {
            //URL url = new URL("https://norfipc.com/web/editar-usar-plantillas.html");
            PrintWriter writer = new PrintWriter("resultado4.html");
            URLConnection con = urlInput.openConnection();
            BufferedReader readUrl = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            while ((input = readUrl.readLine()) != null) {
                htmlPage +=input;
                writer.println(input);
            }
            writer.close();
        }catch(MalformedURLException e){
            System.out.println("URL ERRONEA");
        }
        return htmlPage;
    }
}
