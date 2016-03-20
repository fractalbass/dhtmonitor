int oPin = 13;
int iPin = 3;
int val = 0;
String sensor = "patio";
String attrib = "light";

void setup()
{
  pinMode(oPin, OUTPUT);
  Serial.begin(9600);
}


void dot(int val)
{
  digitalWrite(oPin, HIGH);
  delay(val);
  digitalWrite(oPin, LOW);
  delay(val);
}

void loop()
{
  val = analogRead(iPin);
  dot(val);
  String formattedUrl = getFormattedUrl(sensor, attrib, val);
  Serial.println(formattedUrl);  
}

String getFormattedUrl(String s, String a, int v) {
  return "/sensor/" + s + "/attribute/" + a + "/value/" + v;
}

