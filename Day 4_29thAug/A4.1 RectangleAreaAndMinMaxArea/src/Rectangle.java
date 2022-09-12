import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rectangle {
    static Scanner sc = new Scanner(System.in);
    double width;
    double height;
    double area;

    Rectangle(double newWidth, double newHeight){
        width = newWidth;
        height = newHeight;
    }

    double getArea(){
        return area = width*height;
    }

    static Rectangle showInput(){
        System.out.println("\nEnter the width for the rectangle");
        double inputWidth = sc.nextDouble();
        System.out.println("Enter the height for the rectangle");
        double inputHeight = sc.nextDouble();
        Rectangle r = new Rectangle(inputWidth, inputHeight);
        System.out.println("The area is: "+r.getArea());
        return r;
    }

    public static void main(String[] args) {
        List<Rectangle> rectangles = new ArrayList<>();


        System.out.println
                ("Please enter the total number of rectangles that you want to create: ");
        int noOfRectangles = sc.nextInt();

        for (int i = 0; i < noOfRectangles; i++){
            rectangles.add(showInput());
        }

        double minArea = rectangles.get(0).getArea();
        double maxArea = rectangles.get(0).getArea();
        Rectangle minAreaRect = rectangles.get(0);
        Rectangle maxAreaRect = rectangles.get(0);

        for(int i = 1; i< noOfRectangles; i++)
        {
            if(rectangles.get(i).getArea()<minArea){
                minArea = rectangles.get(i).getArea();
                minAreaRect = rectangles.get(i);
            }
            if(rectangles.get(i).getArea()>maxArea){
                maxArea = rectangles.get(i).getArea();
                maxAreaRect = rectangles.get(i);
            }
        }
        System.out.println("\nThe minimum area of rectangle with width " + minAreaRect.width
                + " and height " + minAreaRect.height + " is: "+minArea);
        System.out.println("The maximum area of rectangle with width " + maxAreaRect.width
                + " and height " + maxAreaRect.height + " is: "+maxArea);
    }
}

/*
OUTPUT:
Please enter the total number of rectangles that you want to create:
3

Enter the width for the rectangle
5
Enter the height for the rectangle
6
The area is: 30.0

Enter the width for the rectangle
4
Enter the height for the rectangle
5
The area is: 20.0

Enter the width for the rectangle
9
Enter the height for the rectangle
6
The area is: 54.0

The minimum area of rectangle with width 4.0 and height 5.0 is: 20.0
The maximum area of rectangle with width 9.0 and height 6.0 is: 54.0
*/