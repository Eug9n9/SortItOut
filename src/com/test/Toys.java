package com.test;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.*;
// CLASS BEGINS, THIS CLASS IS REQUIRED
class Toys
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public ArrayList<String> popularNToys(int numToys,
                                          int topToys,
                                          List<String> toys,
                                          int numQuotes,
                                          List<String> quotes)
    {
        // WRITE YOUR CODE HERE

        Map<String, Integer> hits = new HashMap<>();
        for (int q = 0; q < numQuotes; q++) {
            String quote = quotes.get(q).toLowerCase();
            for (int t = 0; t < numToys; t++) {
                String toy = toys.get(t);
                if (quote.indexOf(toy.toLowerCase()) > -1) {
                    Integer hit = hits.get(toy);
                    hits.put(toy, hit == null ? 1 : hit + 1);
                }
            }
        }
        System.out.println(hits);

        ArrayList<String> r = new ArrayList<>(toys);
        Collections.sort(r, new Sorter(hits));
        System.out.println(r);
        return new ArrayList<>(r.subList(0, topToys));
    }
    class Sorter implements Comparator<String>
    {
        private Map<String, Integer> h;
        Sorter(Map<String, Integer> h) {
            this.h = h;
        }
        public int compare(String a, String b)
        {
            Integer hit1 = this.h.get(a);
            Integer hit2 = this.h.get(b);
            hit1 = ((hit1 == null) ? 0 : hit1);
            hit2 = ((hit2 == null) ? 0 : hit2);
            return hit2.compareTo(hit1);
        }
    }
    // METHOD SIGNATURE ENDS


    public static void main(String[] args) {
        ArrayList<String> r = new Toys().popularNToys(5, 2,
                Arrays.asList(new String []{"a", "b", "c", "d", "e"}), 5,
                Arrays.asList(new String []{"e d", " e d d", "cv", "ff", "lk"}));
        System.out.println(r);
    }
}
