package edu.eci.Punto1;


import java.io.*;
import java.net.*;


public class EjercicioOne{
    public static void main( String[] args ){
    	try {
    		URL url = new URL("https://github.com:443/CrkJohn/ECIGMA");
    		System.out.println("Protocolo : "+ url.getProtocol());
    		System.out.println("Authority : "+ url.getAuthority());
    		System.out.println("Host : "+ url.getHost());
    		System.out.println("Port : "+ url.getPort());
    		System.out.println("Path : "+ url.getPath());
    		System.out.println("Query : "+ url.getQuery());
    		System.out.println("File : "+ url.getFile());
    		System.out.println("Reference : "+ url.getRef());            
    	}catch (Exception e) {
    		
    	}
    	
    }
}
