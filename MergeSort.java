import java.lang.reflect.Array;

/* Project : Homework #6
 * Programmer : Spencer Crawford
 * Date : 10/19/17
 * Description : tests practical applications of Merge Sort.
 */
public class MergeSort {
    // this method merges the two halves iteratively after sorting
    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i] <= b[j])  c[k] = a[i++];
            else                    c[k] = b[j++];
        } // end for
        return c; } // end merge

    //this method divides the input into halves iteratively then sorts
    public int[] mergesort(int[] input) {
        int N = input.length;
        if (N <= 1) return input;
        int[] a = new int[N/2];
        int[] b = new int[N - N/2];
        for (int i = 0; i < a.length; i++)
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)
            b[i] = input[i + N/2];
        return merge(mergesort(a), mergesort(b)); } // end mergesort

    // this method merges the two halves iteratively after sorting
    private static double[] mergeDoubles(double[] a, double[] b) {
        double[] c = new double[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i] <= b[j])  c[k] = a[i++];
            else                    c[k] = b[j++];
        } // end for
        return c; } // end mergeDoubles

    //this method divides the input into halves iteratively then sorts
    public double[] mergesortDoubles(double[] input) {
        int N = input.length;
        if (N <= 1) return input;
        double[] a = new double[N/2];
        double[] b = new double[N - N/2];
        for (int i = 0; i < a.length; i++)
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)
            b[i] = input[i + N/2];
        return mergeDoubles(mergesortDoubles(a), mergesortDoubles(b)); } // end mergesortDoubles

    // this method merges the two halves iteratively after sorting
    private static String[] mergeStrings(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i].compareTo(b[j]) <= 0)  c[k] = a[i++];
            else                    c[k] = b[j++];
        } // end for
        return c; } // end mergeStrings

    //this method divides the input into halves iteratively then sorts
    public String[] mergesortStrings(String[] input) {
        int N = input.length;
        if (N <= 1) return input;
        String[] a = new String[N/2];
        String[] b = new String[N - N/2];
        for (int i = 0; i < a.length; i++)
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)
            b[i] = input[i + N/2];
        return mergeStrings(mergesortStrings(a), mergesortStrings(b)); } // end mergesortStrings

    /** this was my attempt at making a generic merge sort. haven't finished it yet **/
    /*
    // this method merges the two halves iteratively after sorting
    private static <E extends Comparable<E>> E[] mergeE(E[] a, E[] b, Class<E> newE) throws Exception {
        int s = (a.length + b.length);
        @SuppressWarnings("unchecked")
        E[] c = (E[]) Array.newInstance(newE, s);
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i].compareTo(b[j]) <= 0)  c[k] = a[i++];
            else                    c[k] = b[j++];
        } // end for
        return c; } // end merge

    //this method divides the input into halves iteratively then sorts
    public <E extends Comparable<E>> E[] mergesortE(E[] input, Class<E> newE) throws Exception {
        int N = input.length;
        int s = (N/2);
        int s2 = (N-N/2);
        if (N <= 1) return input;
        @SuppressWarnings("unchecked")
        E[] a = (E[]) Array.newInstance(newE, s);
        @SuppressWarnings("unchecked")
        E[] b = (E[]) Array.newInstance(newE, s2);
        for (int i = 0; i < a.length; i++)
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)
            b[i] = input[i + N/2];
        return mergeE(mergesortE(a, newE), mergesortE(b, newE), newE); } // end mergesort
    */
} // end MergeSort