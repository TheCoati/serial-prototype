import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort port : ports) {
            try(SerialConnection connection = new SerialConnection(port)) {
                connection.open();

                while (true) {
                    connection.sendMessage(new byte[] {1});
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                System.err.println("Failed to send message to " + port.getDescriptivePortName());
                System.err.println(e.getLocalizedMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
