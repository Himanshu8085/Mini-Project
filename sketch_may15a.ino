// Digital I/O Pins of Arduno
int led1=4,led2=8,led3=6,led4=2,state=0; 

void setup() {
//Standard Baud Rate
  Serial.begin(9600);
  
//Initialize as output
  pinMode(led1,OUTPUT);
  pinMode(led2,OUTPUT);
  pinMode(led3,OUTPUT);
  pinMode(led4,OUTPUT);
}
void loop() {
  if(Serial.available() > 0){
    
// Reading From Serial
      state = Serial.read();
//5ms Buffer
      delay(5);
      
// LED1     
      if(state == '1'){
          digitalWrite(led1,HIGH);
        }

      if(state == '5'){
          digitalWrite(led1,LOW);
        }
// LED2
      if(state == '2'){
          digitalWrite(led2,HIGH);
        }

      if(state == '6'){
          digitalWrite(led2,LOW);
        }
// LED3
      if(state == '3'){
          digitalWrite(led3,HIGH);
        }

      if(state == '7'){
          digitalWrite(led3,LOW);
        }
// LED4
      if(state == '4'){
          digitalWrite(led4,HIGH);
        }

      if(state == '8'){
          digitalWrite(led4,LOW);
        }


    }
}
