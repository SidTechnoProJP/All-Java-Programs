interface DrawableRect{
    public default void drawRect(){
        System.out.println("Area of rectangle : " + shape.getArea());
    };
}
abstract class Figure {
    private double height;
    private double width;

    public void setValues(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public abstract double getArea();
}

class Rectangle extends Figure {
    public double getArea() {
        return getHeight() * getWidth();
    }
}

class Square extends Figure {
    @Override
    public double getArea() {
        return getHeight() * getHeight();
    }
}

class Triangle extends Figure {
    @Override
    public double getArea() {
        return (getHeight() * getWidth()) / 2;
    }
}

class LambdaRectSqrTri {
    public static void main(String[] args) {
        Figure shape;
        // assign subclass reference to subclass variable
        Rectangle rect = new Rectangle();

        // figure points to the object rect.
        shape = rect;

        // Set data in Rectangle's object
        shape.setValues(78, 5);

        //Display the area of rectangle
        System.out.println("Area of rectangle : " + shape.getArea());

        //assign subclass reference to subclass variable
        Square sqr = new Square();

        //figure points to the object square
        shape = sqr;

        // set data in squares object
        shape.setValues(50, 50);


        // Display the area of square
        System.out.println("Area of square: " + shape.getArea());


        // assign subclass reference to subclass variable
        Triangle tri = new Triangle();

        // figure points to the object rect.
        shape = tri;

        // Set data in Triangle's object
        shape.setValues(34, 3);

        //Display the area of triangle
        System.out.println("Area of triangle : " + shape.getArea());
    }
}
