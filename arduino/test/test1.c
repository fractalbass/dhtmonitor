#include<stdio.h>

int pin = 13;
int HIGH=255;
int LOW=0;
int OUTPUT=0;

void delay(int delay) {
  printf("Mocked call to delay.");
}

void digitalWrite(int p, int v) {
  printf("Mocked call to digital write.");
}

void pinMode(int p, int o) {
  printf("Mocked pinMode call");
}

void setup()
{
  pinMode(pin, OUTPUT);
}

main() {
  for(int i=0;i<1000;i++) {
      loop();
  }
  printf("Complete.");
}

void dot()
{
  digitalWrite(pin, HIGH);
  delay(250);
  digitalWrite(pin, LOW);
  delay(250);
}

void dash()
{
  digitalWrite(pin, HIGH);
  delay(1000);
  digitalWrite(pin, LOW);
  delay(250);
}

void loop()
{
  dot(); dot(); dot();
  dash(); dash(); dash();
  dot(); dot(); dot();
  delay(3000);
}