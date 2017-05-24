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
color orange = color(255, 128, 0);
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
  stroke(lime, 128);
  line(percentageX(265), 0, percentageX(265), height);
  line(percentageY(1625), 0, percentageY(1625), height);
  stroke(lime, 128);
  line(0, percentageX(30), width, percentageX(30));
  line(0, percentageY(990), width, percentageY(1010));
  //rangeTest(185.0, 165.0, /*for top left entrance - FE1*/

  /*ADJUSTED POSITIONS*/
  //left exits
  rangeTest(265.0, 153.0, red, "adjusted topleft");
  rangeTest(266.0, 484.0, red, "adjusted bottomleft");
  //right
  rangeTest(1625.0, 191.0, red, "adjusted topright");
  rangeTest(1610.0, 826.0, red, "adjusted bottomright");


  /* ORIGINAL POSITIONS*/
  //left exits
  rangeTest(10, 213, purple, "original topleft");
  rangeTest(9, 1038, purple, "original bottomleft");
  //right exits
  rangeTest(3398, 305, purple, "original topright");
  rangeTest(3345, 1884, purple, "original bottomright");
  println();
  int athx = 265;
  int athy = 132;
  float geox = 2590, geoy = 0;
  /*offsetted positions*/
  rangeTest(
    percentageX(10)+athx, 
    percentageY(213)+athy, 
    orange, "offsetted topleft"
    );
  println();

  rangeTest(
    (percentageX(10)*geox) +athx, 
    (percentageY(213)*geoy) +athy, 
    orange, "offsetted topright"
    );


  //rangeTest(117, 480);

  mousePrint();
}

float percentageX(float x) {
  return  x/ (width/100);
}
float percentageY(float y) {
  return  y/ (height/100);
}

void rangeTest(float x, float y, color c, String name) {

  //int tempx = (int)x+(int)offsetX;
  //int tempy = (int)y+(int)offsetY;
  println(name+":\t"+percentageX(x)+"%\t", percentageY(y)+"%");
  strokeWeight(3);
  noFill(); 
  stroke(c);
  ellipse(x, y, 12, 12);
}



void mousePrint() {
  fill(255);
  noStroke();
  rect(mouseX+30, mouseY-50, 80, 80);
  fill(96, 96, 255);
  textSize(30);

  text(mouseX, mouseX+30, mouseY);
  text(mouseY, mouseX+30, mouseY+30);
}