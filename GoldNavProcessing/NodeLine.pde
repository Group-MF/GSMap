class NodeLine {
  int x1, y1, x2, y2; //coordinates
  int ox = 0, oy = 0, ow = 1, oh = 1; //offset values for x, y, width and height

  NodeLine(
  int _x1, int _y1, int _x2, int _y2
  ) {
    x1 = _x1; 
    y1 = _y1;
    x2 = _x2; 
    y2 = _y2;
  }
  NodeLine(
    int _x1, int _y1, int _x2, int _y2, 
    int _ox, int _oy) {

    x1 = _x1; 
    y1 = _y1;
    x2 = _x2; 
    y2 = _y2;
    ox = _ox;
    oy = _oy;
  }
  NodeLine(
    int _x1, int _y1, int _x2, int _y2, 
    int _ox, int _oy, int _ow, int _oh
    ) {

    x1 = _x1; 
    y1 = _y1;
    x2 = _x2; 
    y2 = _y2;
    ox = _ox;
    oy = _oy;
    ow = _ow;
    oh = _oh;
  }

  void calculate() {
    x1=+ox/ow;
    y2=+oy/oh;
  }
  
  float calc(int value, int offset, int divisor ) {
    return (value / divisor) +offset;
  }
  void display() {
    stroke(0,192,0);
    line(
      calc(x1, ox, ow), calc(y1, oy, oh), /*first line coordinate*/
      calc(x2, ox, ow), calc(y2, oy, oh)); /*second line coordinate*/
  }
}