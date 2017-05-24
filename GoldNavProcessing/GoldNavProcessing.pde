/**
 Front door node basic position = (9.2, 625)
 corrected position = (415.9, 818.1) 
 415.9-9.2, 818.1 - 625
 **/

PImage RHB1 = new PImage(), RHB2 = new PImage(), RHB3 = new PImage();
NodeLine node;
//double offsetX= 415.9-9.2, offsetY = 818.1;
double offsetX = 170, offsetY = -49; 
double FEx = 10.043096655581408, FEy = 213.84364379203896;
double F2x = 1586.3161349017862 , F2y = 895.249169053422; 
void setup() {
  size(1920, 1080);
  node = new NodeLine(400, 200, 900, 200);
  RHB1 = loadImage("rhb_f1.png");
  RHB2 = loadImage("rhb_f2.png");
  RHB3 = loadImage("rhb_f3.png");
}

public void draw() {
  imageMode(CORNER);

  float scaling= 1.0; // used to adjust position and scaling of images
  //offset at 80%
  image(RHB1, 0, 0, width*scaling, height*scaling);
  //rangeTest(185.0, 165.0, /*for top left entrance - FE1*/

   
  rangeTest((float)FEx, (float)FEy);
  rangeTest((float)F2x, (float)F2y);
  rangeTest(1537, 200);
  //node.display();
  rangeTest((float)180.0- (float)offsetX, 480.0-(float)offsetY);
  
  //rangeTest(3345.0, 1884.0, 
  rangeTest(1586.0, 895.0);
  mousePrint();
  
  //rangeTest(117, 480);
  
}

void rangeTest(float x, float y) {
  int tempx = (int)x+(int)offsetX;
  
  int tempy = (int)y+(int)offsetY;
  println(tempx, tempy);
  strokeWeight(3);
  stroke(225, 0, 0); 
  noFill(); 
  //rect((float)x,(float) y, (float)w, (float)h);
  stroke(255, 0, 0);
  ellipse(tempx, tempy, 12, 12);
}

void mousePrint(){
 println((int)mouseX, (int)mouseY);
 text(mouseX, mouseY, width*0.8, height*0.2);
 
}