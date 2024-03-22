void setup() {
  Serial.begin(9600);
}

int incomingByte = 0; // for incoming serial data

void loop() {
  Serial.write(2);
  
  delay(1000);

  if (Serial.available() > 0) {
    incomingByte = Serial.read();

    Serial.write(incomingByte);
  }
}
