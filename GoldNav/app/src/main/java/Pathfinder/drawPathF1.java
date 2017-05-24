package Pathfinder;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class drawPathF1 extends PApplet {

    ArrayList<Vertex> path;
    PImage map;

    public void setup() {
        path = NaviRHB.PathInfo.getVertexPath();
        //map = loadImage("rhb_f1");
        //image(map, 0, 0);
    }
    public void draw(){
        ArrayList<String> path = new ArrayList<>();
    }

    public void settings() {  size(displayWidth, displayHeight); }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "drawPathF1" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}