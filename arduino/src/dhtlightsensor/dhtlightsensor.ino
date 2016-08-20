#include <SoftwareSerial.h>

int oPin = 13;
int iPin = 3;
int val = 0;
char* sensor = "patio";
char* attrib = "light";

#define DEBUG TRUE //comment out to remove debug msgs

//*-- Hardware Serial
#define _baudrate 9600

//*-- Software Serial
//
#define _rxpin      2
#define _txpin      3
SoftwareSerial debug( _rxpin, _txpin ); // RX, TX

String POST = "POST ";

//*-- IoT Information
#define SSID "rileytoed"
#define PASS "11111111"
#define IP "137.135.90.212" // ThingSpeak IP Address: 184.106.153.149


void setup()
{
  Serial.begin(115200);
  debug.begin(9600);
  sendDebug("AT");
  delay(5000);
  if(Serial.find("OK"))
  {
    debug.println("RECEIVED: OK\nData ready to sent!");
    connectWiFi();
  } else {
    debug.println("No OK found.");
    
  }

  delay(10000);
  pinMode(oPin, OUTPUT);
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
  for(int i=0;i<50+(60*14);i++) {
    dot(500);
  }
  for (int i=0;i<100;i++) {
    dot(50);
  }
  val = analogRead(iPin);
  char* formattedUrl = getFormattedUrl(sensor, attrib, val);
  sendMessage(formattedUrl);  
  debug.println("Message sent...");
}

char* getFormattedUrl(char* s, char* a, int v) {
  char buf[256];
  snprintf(buf, sizeof buf, "/dhtmonitor/sensor/%s/attribute/%s/value/%d", s,a,v);
  return buf;
}

void sendMessage(char* formattedUrl) {
  // ESP8266 Client
  String cmd = "AT+CIPSTART=\"TCP\",\"";// Setup TCP connection
  cmd += IP;
  cmd += "\",8888";
  sendDebug(cmd);
  delay(2000);
  if( Serial.find( "Error" ) )
  {
    debug.print( "RECEIVED: Error\nExit1" );
    return;
  }

  cmd = POST + formattedUrl +"\r\n";
  Serial.print( "AT+CIPSEND=" );
  Serial.println( cmd.length() );
  if(Serial.find( ">" ) )
  {
    debug.print(">");
    debug.print(cmd);
    Serial.print(cmd);
  }
  else
  {
    sendDebug( "AT+CIPCLOSE" );//close TCP connection
  }
  if( Serial.find("OK") )
  {
    debug.println( "RECEIVED: OK" );
  }
  else
  {
    debug.println( "RECEIVED: Error\nExit2" );
  }
}

void sendDebug(String cmd)
{
  debug.print("SEND: ");
  debug.println(cmd);
  Serial.println(cmd);
}

boolean connectWiFi()
{
  Serial.println("AT+CWMODE=1");//WiFi STA mode - if '3' it is both client and AP
  delay(2000);
  //Connect to Router with AT+CWJAP="SSID","Password";
  // Check if connected with AT+CWJAP?
  String cmd="AT+CWJAP=\""; // Join accespoint
  cmd+=SSID;
  cmd+="\",\"";
  cmd+=PASS;
  cmd+="\"";
  sendDebug(cmd);
  delay(5000);
  if(Serial.find("OK"))
  {
    debug.println("RECEIVED: OK");
    return true;
  }
  else
  {
    debug.println("RECEIVED: Error");
    return false;
  }

  cmd = "AT+CIPMUX=0";// Set Single connection
  sendDebug( cmd );
  if( Serial.find( "Error") )
  {
    debug.print( "RECEIVED: Error" );
    return false;
  }
}



