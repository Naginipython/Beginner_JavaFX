package com.example.lottery;

// Prof. A.C. Silvestri
// CSC-112 Intermediate Java Programming
// 04/26/2016

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleClient {

   static void pStr(String p)
   {
      System.out.println(p);
   }

   static void runClient()
   {
      Socket client = null;
      PrintWriter output = null;
      Scanner input = null;

      try (Scanner sc = new Scanner(System.in);) {
         pStr("Creating Client Socket ");
         client = new Socket( "localhost", 5012 );//hw needs cs.stcc.edu
         pStr("SUCCESS!!!");

         input = new Scanner(client.getInputStream());
         output = new PrintWriter(client.getOutputStream());
         
         System.out.print("Enter Operand 1: ");
         double op1 = sc.nextDouble();
         output.println("" + op1);
         output.flush();
         
         System.out.print("Enter Operand 2: ");
         double op2 = sc.nextDouble();
         output.println("" + op2);
         output.flush();
         
         sc.nextLine(); // Clear KeyBoard Scanner!!!
         
         System.out.print("Enter Operation: ");
         String op = sc.nextLine();
         output.println(op);
         output.flush();
         
         String ansStr = input.nextLine();
         System.out.println(ansStr);
      }
      catch ( Exception e ) {
         e.printStackTrace();
      }
      finally {
			try {
				input.close();
			} catch (Exception e) {
			}
			try {
				output.close();
			} catch (Exception e) {
			}
			try {
				client.close();
			} catch (Exception e) {
			}    	  
      }
   }

   public static void main( String args[] )
   {
	   runClient();
   }
}
