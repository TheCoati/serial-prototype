import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort port : ports) {
            try(SerialConnection connection = new SerialConnection(port)) {
                connection.open();
                connection.sendMessage("a");

                while (true)
                {
                    while (port.bytesAvailable() > 0) {
                        byte[] readBuffer = new byte[port.bytesAvailable()];
                        int numRead = port.readBytes(readBuffer, readBuffer.length);

                        for (int i = 0; i < readBuffer.length; i++)
                            System.out.print((char)readBuffer[i]);

                    }
                }


            } catch (IOException e) {
                System.err.println("Failed to send message to " + port.getDescriptivePortName());
                System.err.println(e.getLocalizedMessage());
            }
        }

    }
}
