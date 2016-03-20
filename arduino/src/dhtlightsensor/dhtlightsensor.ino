int oPin = 13;
int iPin = 3;
int val = 0;
char* sensor = "patio";
char* attrib = "light";

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
  char* formattedUrl = getFormattedUrl(sensor, attrib, val);
  Serial.println(formattedUrl);  
}

char* getFormattedUrl(char* s, char* a, int v) {
  char buf[256];
  snprintf(buf, sizeof buf, "/sensor/%s/attribute/%s/value/%d", s,a,v);
  return buf;
}

