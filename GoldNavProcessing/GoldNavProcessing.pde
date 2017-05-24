/**
 Front door node basic position = (9.2, 625)
 corrected position = (415.9, 818.1) 
 415.9-9.2, 818.1 - 625
 **/

PImage RHB1 = new PImage(), RHB2 = new PImage(), RHB3 = new PImage();

//double offsetX= 415.9-9.2, offsetY = 818.1;
double offsetX = 170, offsetY = -49; 
double FEx = 10.043096655581408, FEy = 213.84364379203896;
double F2x = 1586.3161349017862, F2y = 895.249169053422; 
color red = color(255, 0, 0);
color green = color(0, 128, 0);
color orange = color(128, 128, 0);
color blue = color(0, 0, 255);
color purple = color(128, 0, 255);
color lime = color(0, 255, 0);
void setup() {
  size(1920, 1080);


  RHB1 = loadImage("rhb_f1.png");
  RHB2 = loadImage("rhb_f2.png");
  RHB3 = loadImage("rhb_f3.png");
}

public void draw() {
  imageMode(CORNER);

  float scaling= 1.0; // used to adjust position and scaling of images
  //offset at 80%
  image(RHB1, 0, 0, width*scaling, height*scaling);
  //guides F1
  stroke(lime);
  line(265, 0, 265, height);
  line(1625, 0, 1625, height);
  //rangeTest(185.0, 165.0, /*for top left entrance - FE1*/

  //topleft exit
  rangeTest(265, 153, blue);
  //bottomleft exit
  //rangeTest()
  //topright exit
  rangeTest(1586.0, 895.0, blue);
  //bottomright exit

  //rangeTest((float)FEx, (float)FEy, color(255, 0, 0));
  //rangeTest((float)F2x, (float)F2y, color(255, 128, 0));
  //rangeTest(1537, 200, color(255, 0, 128));

  //rangeTest((float)180.0- (float)offsetX, 480.0-(float)offsetY, color(128, 128, 0));

  //rangeTest(3345.0, 1884.0, 

  rangeTest(1625.0, 189.0, blue);
  mousePrint();

  //rangeTest(117, 480);
}

void rangeTest(float x, float y, color c) {

  //int tempx = (int)x+(int)offsetX;
  int tempx = (int)x;
  //int tempy = (int)y+(int)offsetY;
  int tempy = (int)y;
  println(tempx, tempy);
  strokeWeight(3);
  noFill(); 
  stroke(c);
  ellipse(tempx, tempy, 12, 12);
}

void mousePrint() {
  fill(255);
  noStroke();
  rect(mouseX, mouseY-50, 80, 80);
  fill(96, 96, 255);
  textSize(30);

  text(mouseX, mouseX, mouseY);
  text(mouseY, mouseX, mouseY+30);
}