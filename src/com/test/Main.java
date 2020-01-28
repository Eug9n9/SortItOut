package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    static Integer[] in = {45, 2, 90, 24, 73, 3, 48, 99};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(in));
        long time;

        System.out.print("Bubble: ");
        time = System.nanoTime();
        System.out.print(Arrays.toString(bubblesort(in.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("BubblE: ");
        time = System.nanoTime();
        System.out.print(Arrays.toString(bsort(in.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("Merge:  ");
        time = System.nanoTime();
        System.out.print(Arrays.toString(mergeSort(in.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("MergE:  ");
        time = System.nanoTime();
        System.out.print(Arrays.toString(msort(in.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("Quick:  ");
        time = System.nanoTime();
        System.out.print(quicksort(Arrays.asList(in.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("QuickE: ");
        time = System.nanoTime();
        System.out.print(qsort(Arrays.asList(in.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("Native: ");
        time = System.nanoTime();
        Integer[] cloned = in.clone();
        Arrays.sort(cloned);
        System.out.print(Arrays.asList(cloned));
        System.out.println(" " + (System.nanoTime() - time));

        Integer[] large = new Integer[1000];
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            large[i] = r.nextInt();
        }
        System.out.println(slice(large));

        System.out.print("Bubble: ");
        time = System.nanoTime();
        System.out.print(slice(bubblesort(large.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("Merge:  ");
        time = System.nanoTime();
        System.out.print(slice(mergeSort(large.clone())));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("Quick:  ");
        time = System.nanoTime();
        System.out.print(slice(quicksort(Arrays.asList(large.clone()))));
        System.out.println(" " + (System.nanoTime() - time));

        System.out.print("Native: ");
        time = System.nanoTime();
        cloned = large.clone();
        Arrays.sort(cloned);
        System.out.print(slice(cloned));
        System.out.println(" " + (System.nanoTime() - time));
    }

    static String slice(Integer[] numbers) {
        return Arrays.toString(new Integer[]{numbers[0], numbers[numbers.length - 1]});
    }

    static String slice(List<Integer> numbers) {
        return Arrays.toString(new Integer[]{numbers.get(0), numbers.get(numbers.size() - 1)});
    }

    static Integer[] bubblesort(Integer[] numbers) {
        boolean swapped = true;
        for (int i = numbers.length - 1; i > 0 && swapped; i--) {
            swapped = false;
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    swapped = true;
                }
            }
        }
        return numbers;
    }

    static Integer[] mergeSort(Integer array[])
// pre: array is full, all elements are valid integers (not null)
// post: array is sorted in ascending order (lowest to highest)
    {
        // if the array has more than 1 element, we need to split it and merge the sorted halves
        if (array.length > 1) {
            // number of elements in sub-array 1
            // if odd, sub-array 1 has the smaller half of the elements
            // e.g. if 7 elements total, sub-array 1 will have 3, and sub-array 2 will have 4
            int elementsInA1 = array.length / 2;
            // we initialize the length of sub-array 2 to
            // equal the total length minus the length of sub-array 1
            int elementsInA2 = array.length - elementsInA1;
            // declare and initialize the two arrays once we've determined their sizes
            Integer arr1[] = new Integer[elementsInA1];
            Integer arr2[] = new Integer[elementsInA2];
            // copy the first part of 'array' into 'arr1', causing arr1 to become full
            for (int i = 0; i < elementsInA1; i++)
                arr1[i] = array[i];
            // copy the remaining elements of 'array' into 'arr2', causing arr2 to become full
            for (int i = elementsInA1; i < elementsInA1 + elementsInA2; i++)
                arr2[i - elementsInA1] = array[i];
            // recursively call mergeSort on each of the two sub-arrays that we've just created
            // note: when mergeSort returns, arr1 and arr2 will both be sorted!
            // it's not magic, the merging is done below, that's how mergesort works :)
            arr1 = mergeSort(arr1);
            arr2 = mergeSort(arr2);

            // the three variables below are indexes that we'll need for merging
            // [i] stores the index of the main array. it will be used to let us
            // know where to place the smallest element from the two sub-arrays.
            // [j] stores the index of which element from arr1 is currently being compared
            // [k] stores the index of which element from arr2 is currently being compared
            int i = 0, j = 0, k = 0;
            // the below loop will run until one of the sub-arrays becomes empty
            // in my implementation, it means until the index equals the length of the sub-array
            while (arr1.length != j && arr2.length != k) {
                // if the current element of arr1 is less than current element of arr2
                if (arr1[j] < arr2[k]) {
                    // copy the current element of arr1 into the final array
                    array[i] = arr1[j];
                    // increase the index of the final array to avoid replacing the element
                    // which we've just added
                    i++;
                    // increase the index of arr1 to avoid comparing the element
                    // which we've just added
                    j++;
                }
                // if the current element of arr2 is less than current element of arr1
                else {
                    // copy the current element of arr2 into the final array
                    array[i] = arr2[k];
                    // increase the index of the final array to avoid replacing the element
                    // which we've just added
                    i++;
                    // increase the index of arr2 to avoid comparing the element
                    // which we've just added
                    k++;
                }
            }
            // at this point, one of the sub-arrays has been exhausted and there are no more
            // elements in it to compare. this means that all the elements in the remaining
            // array are the highest (and sorted), so it's safe to copy them all into the
            // final array.
            while (arr1.length != j) {
                array[i] = arr1[j];
                i++;
                j++;
            }
            while (arr2.length != k) {
                array[i] = arr2[k];
                i++;
                k++;
            }
        }
        // return the sorted array to the caller of the function
        return array;
    }

    public static List<Integer> quicksort(List<Integer> numbers) {
        if (numbers.size() <= 1)
            return numbers;
        int pivot = numbers.size() / 2;
        List<Integer> lesser = new ArrayList<Integer>();
        List<Integer> greater = new ArrayList<Integer>();
        int sameAsPivot = 0;
        for (int number : numbers) {
            if (number > numbers.get(pivot))
                greater.add(number);
            else if (number < numbers.get(pivot))
                lesser.add(number);
            else
                sameAsPivot++;
        }
        lesser = quicksort(lesser);
        for (int i = 0; i < sameAsPivot; i++)
            lesser.add(numbers.get(pivot));
        greater = quicksort(greater);
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        for (int number : lesser)
            sorted.add(number);
        for (int number: greater)
            sorted.add(number);
        return sorted;
    }

    public static Integer[] bsort(Integer[] a) {
        boolean stop = false;
        for (int i = a.length - 1; i > 0 && !stop; i--) {
            stop = true;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j+1]) {
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                    stop = false;
                }
            }
        }
        return a;
    }

    public static Integer[] msort(Integer[] a) {
        if (a.length > 1) {
            int l1 = a.length / 2;
            int l2 = a.length - l1;
            Integer[] a1 = new Integer[l1];
            Integer[] a2 = new Integer[l2];
            for (int i = 0; i < l1; i++) {
                a1[i] = a[i];
            }
            for (int i = 0; i < l2; i++) {
                a2[i] = a[i + l1];
            }
            a1 = msort(a1);
            a2 = msort(a2);
            int i = 0, i1 = 0, i2 = 0;
            while (i1 < a1.length && i2 < a2.length) {
                if (a1[i1] < a2[i2]) {
                    a[i++] = a1[i1++];
                } else {
                    a[i++] = a2[i2++];
                }
            }
            while (i1 < a1.length) {
                a[i++] = a1[i1++];
            }
            while (i2 < a2.length) {
                a[i++] = a2[i2++];
            }
        }
        return a;
    }

    public static List<Integer> qsort(List<Integer> a) {
        if (a.size() <= 1) {
            return a;
        }
        int pivot = a.get(a.size() / 2);
        List<Integer> l = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        int same = 0;
        for (int v : a) {
            if (v < pivot) {
                l.add(v);
            } else if (v == pivot) {
                same++;
            } else {
                r.add(v);
            }
        }
        l = qsort(l);
        r = qsort(r);
        for (int i = 0; i < same; i++) {
            l.add(pivot);
        }
        l.addAll(r);
        return l;
    }
}
