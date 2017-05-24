package Pathfinder;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class drawPathF1 extends PApplet {

    ArrayList<String> path;
    PImage map;

    public void setup() {
        path = NaviRHB.PathInfo.getPath();
        map = loadImage("@drawable/rhb_f1");
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