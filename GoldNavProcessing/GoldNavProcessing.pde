PImage RHB1 = new PImage(), RHB2 = new PImage(), RHB3 = new PImage();

void setup() {
  size(1920, 1080);
  RHB1 = loadImage("rhb_f1.png");
  RHB2 = loadImage("rhb_f2.png");
  RHB3 = loadImage("rhb_f3.png");
  
}

public void draw() {
  background(255);
  imageMode(CORNER);
  float offset= 1.0; // used to adjust position and scaling of images
  //offset at 80%
  image(RHB1, 0, 0, width*offset, height*offset);
  rangeTest(185,165,/*for top left entrance - FE1*/
  width-590,height-438 ); //to bottom right exit - E1
}

void rangeTest(int x, int y, int w, int h) {
  strokeWeight(3);
  stroke(225, 0, 0); 
  noFill();
  rect(x, y, w, h);
  stroke(255, 0, 0);
  ellipse(x, y, 20, 20);
  ellipse(x+w, y+h, 20, 20);
}