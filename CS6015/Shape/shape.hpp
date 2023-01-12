class Shape {
public:
    virtual float getarea()=0;
};

class Circle: public Shape {
public:
    float radius;
    Circle(float radius);
    float getarea();
};

class Rectangle: public Shape {
    public:
        float width;
        float height;
        Rectangle(float width, float height);
        float getarea() ;
};

void printArea(Shape& shape);
