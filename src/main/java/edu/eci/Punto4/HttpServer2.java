package edu.eci.Punto4;

import java.net.*;
import java.io.*;

public class HttpServer2 {

    /*
        Lo entendido del punto 4

    */
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
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine="";
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("\r\n");
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if(inputLine.contains("html")) {
                String urlInputLine = "";
                int i = inputLine.indexOf('h');
                System.out.println(i);
                while(!urlInputLine.endsWith(".html") && i<inputLine.length()){
                    urlInputLine+=(inputLine.charAt(i++));
                }
                System.out.println("Url Creada : " + urlInputLine);
                URL url = new URL(urlInputLine);
                outputLine = saveHTML(url);
                System.out.println("Termino instance URL");
                break;
            }

            if (!in.ready()) {
                break;
            }
        }

        /*outputLine = "<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n" + "</head>" + "<body>" + "My Web Site" + "</body>"
                + "</html>" + inputLine;

    	BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"//restulado1.html"));
    	bw.write(outputLine);
    	bw.close();

        */
        out.println(outputLine);

        out.close();
        in.close();
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