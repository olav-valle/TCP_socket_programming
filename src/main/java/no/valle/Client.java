package no.valle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
  public static void main(String[] args) {
    Client c = new Client();
      c.run();
  }

  /**
   * run method for Client class
   */
  private void run() {
    System.out.println("Client started...");

    try {
      // Establish connection to server
      Socket socket = new Socket("ntnu.no", 80);

      System.out.println("Connection successful: \n" + socket.toString());

      // Do data transfer here
      String commandToSend = "GET / HTTP/1.0\r\n\r\n";
      System.out.println("Sending command: \n" + commandToSend);
      OutputStream out = socket.getOutputStream();
      out.write(commandToSend.getBytes());

      // Data recieve
      byte[] buffer = new byte[1000];

      int bytesReceived = socket.getInputStream().read(buffer); // read() returns number of bytes read
      System.out.println("Response received: " + bytesReceived + " bytes.");
      String responseString = new String(buffer); // Create string from bytes received
      System.out.println("\nResponse message: \n\n" + responseString);

      // Close connection
      socket.close();

    } catch (IOException e) {
      System.out.println("Client start exception: " + e.getMessage());

    }

  }
}
