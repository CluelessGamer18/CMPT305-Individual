package Demo_Files;
/* This code is provided for use by students in CMPT 305 at MacEwan University.
 * Do not distribute or share this code outside the course. */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {

        // CREATING AN ArrayList

        // Don't use raw types
        List myList1 = new ArrayList();  // declaring a raw type of List
        List<Integer> myList2 = new ArrayList();  // missing <>, ArrayList is a raw type

        // Do this:
        // Create an empty ArrayList and add some elements
        List<Integer> myList3 = new ArrayList<>();
        myList3.add(9);
        myList3.add(3);

        // Create an ArrayList from some given elements
        List<Integer> myList4 = new ArrayList<>(Arrays.asList(5, 8, 2, 7));

        // Create a new ArrayList from the contents of an existing collection
        List<Integer> myList5 = new ArrayList<>(myList4);

        // The ArrayList class implements the List interface.
        System.out.println(myList3 instanceof List);  // true
        System.out.println(myList3 instanceof ArrayList);  // true
        System.out.println(myList3 instanceof LinkedList);  // false

        // Looping over an ArrayList
        for (int i : myList4) {
            System.out.println(i);
        }

        // Passing an ArrayList as an argument
        System.out.println(getAverageRight(myList5));

    }

    // Program to interface, not implementation. Make the parameter type an Interface.
    // Don't do this:
    public static double getAverageWrong(ArrayList<Integer> studentList) {
        double result = 0;
        if (!studentList.isEmpty()) {
            for (double grade : studentList) {
                result += grade;
            }
        }
        return result / studentList.size();
    }

    // Do this:
    public static double getAverageRight(List<Integer> studentList) {
        double result = 0;
        if (!studentList.isEmpty()) {
            for (double grade : studentList) {
                result += grade;
            }
        }
        return result / studentList.size();
    }
}
