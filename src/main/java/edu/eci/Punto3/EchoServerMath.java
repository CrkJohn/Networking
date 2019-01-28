package edu.eci.Punto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class EchoServerMath {
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
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in .readLine()) != null) {
            System.out.println("Mensaje: "+inputLine);
            StringTokenizer stk = new StringTokenizer(inputLine);
            int tokens = stk.countTokens();
            String fun = stk.nextToken();
            double val = Double.parseDouble(stk.nextToken());
            boolean funBool = false;
            double trigonometria = 0.0;
            if(fun.equals("fun:sin")){
                trigonometria = Math.sin(val);
            }else if(fun.equals("fun:cos")){
                trigonometria = Math.cos(val);
            }else{
                trigonometria =  Math.tan(val);
            }
            inputLine = Double.toString(trigonometria);
            System.err.println(trigonometria);
            outputLine = "Respuesta " + inputLine;
            out.println(outputLine);

        }
        out.close(); in .close();
        clientSocket.close();
        serverSocket.close();
    }
}
