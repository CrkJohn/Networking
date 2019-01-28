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
        String funcion = "cos";
        while ((inputLine = in .readLine()) != null) {


            double trigonometria = 0.0;
            if(!inputLine.startsWith("fun")){
                try{
                    double number = Double.parseDouble(inputLine);
                }catch(Exception e){
                }
            }else if(inputLine.equals("fun:sin")){
                funcion = "sin";
            }else if(inputLine.equals("fun:cos")){
                funcion = "cos";
            }else if(inputLine.equals("fun:tan"){
                funcion = "tan";
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

    static double operacion(double number,String op){
        if(op.equals("sin")){
            return Math.sin(number);
        }else if (op.equals("cos")){
            return Math.cos(number);
        }else{
            return Math.tan(number);
        }
    }

}
