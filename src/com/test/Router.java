package com.test;

import java.util.*;

public class Router {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<Integer> criticalRouters(int numRouters, int numLinks,
                                  ArrayList<List<Integer>> links) {
        // WRITE YOUR CODE HERE
        Set<Integer> rr = new HashSet<>();
        for (int i = 0; i < numLinks; i++) {
            List<Integer> l = links.get(i);
            rr.addAll(l);
        }
        System.out.println(rr);
        List<Integer> res = new ArrayList<>();
        rr.iterator().forEachRemaining(r -> {
            Set<Integer> fr = new HashSet<>();
            ArrayList<List<Integer>> fl = new ArrayList<>();
            links.forEach(l -> {
                if (l.get(0) != r && l.get(1) != r) {
                    fr.addAll(l);
                    fl.add(l);
                }
            });
            boolean can = fr.size() == numRouters - 1 && fl.size() >= fr.size() - 1;
            System.out.print(r);
            System.out.print(" -> ");
            System.out.print(fr);
            System.out.print(" -> ");
            System.out.print(fl);
            System.out.print(" -> ");
            System.out.println(can);
            if (can) {
                res.add(r);
            }
        });
        return res;
    }
    // METHOD SIGNATURE ENDS


    public static void main(String[] args) {
        // 1  2  3  4  5  6  7
        // ----
        // -------
        //    -------
        //       ----
        //       ----------
        //                ----
        //          ----
        ArrayList<List<Integer>> links = new ArrayList<>();
        links.add(Arrays.asList(new Integer[]{1, 2}));
        links.add(Arrays.asList(new Integer[]{1, 3}));
        links.add(Arrays.asList(new Integer[]{2, 4}));
        links.add(Arrays.asList(new Integer[]{3, 4}));
        links.add(Arrays.asList(new Integer[]{3, 6}));
        links.add(Arrays.asList(new Integer[]{6, 7}));
        links.add(Arrays.asList(new Integer[]{4, 5}));
        List<Integer> r = new Router().criticalRouters(7, 7, links);
        System.out.println(r); // 1,2,5,7 => 3,4,6 are critical
        System.out.println();

        links = new ArrayList<>();
        links.add(Arrays.asList(new Integer[]{1, 2}));
        links.add(Arrays.asList(new Integer[]{2, 3}));
        links.add(Arrays.asList(new Integer[]{3, 4}));
        links.add(Arrays.asList(new Integer[]{4, 5}));
        links.add(Arrays.asList(new Integer[]{6, 3}));
        r = new Router().criticalRouters(6, 5, links);
        System.out.println(r); // 1,5,6 => 2,3,4 are critical
        System.out.println();

        links = new ArrayList<>();
        links.add(Arrays.asList(new Integer[]{1, 2}));
        links.add(Arrays.asList(new Integer[]{1, 3}));
        links.add(Arrays.asList(new Integer[]{2, 3}));
        links.add(Arrays.asList(new Integer[]{3, 4}));
        links.add(Arrays.asList(new Integer[]{4, 5}));
        links.add(Arrays.asList(new Integer[]{4, 6}));
        links.add(Arrays.asList(new Integer[]{5, 6}));
        links.add(Arrays.asList(new Integer[]{5, 7}));
        links.add(Arrays.asList(new Integer[]{6, 7}));
        links.add(Arrays.asList(new Integer[]{7, 8}));
        links.add(Arrays.asList(new Integer[]{8, 9}));
        links.add(Arrays.asList(new Integer[]{8, 10}));
        links.add(Arrays.asList(new Integer[]{9, 10}));
        r = new Router().criticalRouters(10, 13, links);
        System.out.println(r); // 1,2,5,6,9,10 => 3,4,7,8 are critical
        System.out.println();
    }
}
