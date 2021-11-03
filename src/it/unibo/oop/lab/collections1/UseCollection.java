package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {

    private static final int END = 2000;
	private static final int START = 1000;
	private static final int ELEMS = 100_000;
    private static final int TO_MS = 1_000_000;
	
    private UseCollection() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
    	final var integerList = new ArrayList<Integer>();
    	for (int i = START; i < END; i++) {
			integerList.add(i);	
		}
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
    	final var integerLinkedList = new LinkedList<>(integerList);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
    	final var lastItem = integerList.get(integerList.size()-1);
    	integerList.set(integerList.size() - 1, integerList.get(0));
    	integerList.set(0, lastItem);
    	
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
    	for (Integer integer : integerList) {
			System.out.print(integer + " ");
		}
    	System.out.println();
    	System.out.println(integerLinkedList);
    	
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
    	long time = System.nanoTime();
    	for (int i = 0; i < ELEMS; i++) {
			integerList.add(0, i);
		}
        time = System.nanoTime() - time;
        System.out.println("Add " + ELEMS
                + " int to first position in arrayList took " + time
                + "ns (" + time / TO_MS + "ms)");
        
    	time = System.nanoTime();
    	for (int i = 0; i < 100000; i++) {
			integerLinkedList.add(0, integerLinkedList.get(0));
		}
        time = System.nanoTime() - time;
        System.out.println("Add " + ELEMS
                + " int to first position in arrayLinkedList took " + time
                + "ns (" + time / TO_MS + "ms)");
    	
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        
    	time = System.nanoTime();
    	for (int i = 0; i < START; i++) {
			integerList.get(integerList.size() / 2);
		}
        time = System.nanoTime() - time;
        System.out.println("Read " + START
                + " int to first position in arrayList took " + time
                + "ns (" + time / TO_MS + "ms)");
        
    	time = System.nanoTime();
    	for (int i = 0; i < START; i++) {
			integerLinkedList.get(integerLinkedList.size() / 2);
		}
        time = System.nanoTime() - time;
        System.out.println("Read " + START
                + " int to first position in arrayLinkedList took " + time
                + "ns (" + time / TO_MS + "ms)");
        
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         * 
         * Africa -> 1,110,635,000
         * 
         * Americas -> 972,005,000
         * 
         * Antarctica -> 0
         * 
         * Asia -> 4,298,723,000
         * 
         * Europe -> 742,452,000
         * 
         * Oceania -> 38,304,000
         */
        final Map<String, Double> world = new HashMap<>();
        world.put("Africa", 1_110_635_000d);
        world.put("Americas", 972_005_000d);
        world.put("Antartica", 0d);
        world.put("Asia", 4_298_723_000d);
        world.put("Europe", 742_452_000d);
        world.put("Oceania", 38304000d);
        
        /*
         * 8) Compute the population of the world
         */
        double population = 0;
        for (Double pop : world.values()) {
			population += pop;
		}
        System.out.println("World population are: " + population);
			
    }
}
