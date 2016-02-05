/**
 * This class performs timing experiments on our BinarySearchSet's contains() and add() methods
 * 
 * Authors: Ryan Sargent, Kent Allen & Mohit Chaudhary
 * Last Modified: Feb. 4, 2016
 */
package assignment03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BinaryTimingExperiment {

	private static final int ITER_COUNT = 1000;

	public static void main(String[] args) {
		 containsTimeTester();
		 addAverageTimeTester();
	}
	
	public static void containsTimeTester(){
		// you spin me round baby, right round
				long startTime = System.nanoTime();
				System.out.println();
				while (System.nanoTime() - startTime < 1000000000);
				
				try(FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { //open up a file writer so we can write to file.
					for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
						int size = (int) Math.pow(2, exp); // or ..  
						size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
						
						
						// Do the experiment multiple times, and average out the results
						long totalTime = 0;
						for (int iter = 0; iter < ITER_COUNT; iter++) {
							// SET UP!
							BinarySearchSet<Integer> set = new BinarySearchSet<>();
							for(int i = 0; i < size; i++) {
								set.add(i);
							}
							int findElement = new Random().nextInt(size); // This gets me a random in between 0 and size;
							
							// TIME IT!
							long start = System.nanoTime();
							set.contains(findElement);
							long stop = System.nanoTime();
							totalTime += stop - start;
						}
						double averageTime = totalTime / (double)ITER_COUNT;
						System.out.println(size + "\t" + averageTime); // print to console
						fw.write(size + "\t" + averageTime + "\n"); // write to file.
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	public static void addAverageTimeTester(){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);
		
		try(FileWriter fw = new FileWriter(new File("add_average_experiment.tsv"))) { //open up a file writer so we can write to file.
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
				
				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					BinarySearchSet<Integer> set = new BinarySearchSet<>(); // Adding only odd numbers to the set
					for(int i = 1; i < size * 2 - 1; i+=2) {
						set.add(i);
					}
					int evenElement = new Random().nextInt(size / 2) * 2; // This gets me a random even int between 0 and size;
					
					// TIME IT!
					long start = System.nanoTime();
					set.add(evenElement);
					long stop = System.nanoTime();
					totalTime += stop - start;
					set.remove(evenElement); // Not testing remove time!
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}