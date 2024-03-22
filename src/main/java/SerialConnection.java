import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SerialConnection implements Closeable {
    private final SerialPort port;

    public SerialConnection(SerialPort port) {
        this.port = port;
    }

    public void open() {
        this.port.openPort();

        this.port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                System.out.println(serialPortEvent.getReceivedData());
            }
        });
    }

    public void sendMessage(String message) throws IOException {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);

        this.port.writeBytes(bytes, bytes.length);

//        try(OutputStream outputStream = this.port.getOutputStream()) {
//            outputStream.write(bytes);
//            outputStream.flush();
//        }
    }

    @Override
    public void close() {
        this.port.closePort();
    }
}
