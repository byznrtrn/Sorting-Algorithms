import java.util.*;

public class SortingAlgortithms {

    /*  implement sorting algorithms below
    •	Bubble Sort
    •	Selection Sort
    •	Insertion Sort
    •	Merge Sort
    •	Quick Sort
    •	Heap Sort
    •	Counting Sort
    •	Radix Sort

       Fill in the method.
       Implement sorting algorithms.
       You can use extra method.

    */
    private long totalRuntime1, totalRuntime2, totalRuntime3, totalRuntime4, totalRuntime5, totalRuntime6, totalRuntime7, totalRuntime8;

    public void BubbleSort(int A[]) {
        long startTime = System.currentTimeMillis();

        int n = A.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (A[j] > A[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
        long endTime = System.currentTimeMillis();
        totalRuntime1 = (endTime - startTime);

    }

    public void SelectionSort(int A[]) {
        long startTime = System.currentTimeMillis();

        int n = A.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (A[j] < A[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = A[min_idx];
            A[min_idx] = A[i];
            A[i] = temp;
        }
        long endTime = System.currentTimeMillis();
        totalRuntime2 = (endTime - startTime);
    }

    public void InsertionSort(int A[]) {
        long startTime = System.currentTimeMillis();

        int n = A.length;
        for (int i = 1; i < n; ++i) {
            int key = A[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
        long endTime = System.currentTimeMillis();
        totalRuntime3 = (endTime - startTime);
    }

    public void MergeSort(int A[]) {


        long startTime = System.currentTimeMillis();

        int[] helper = new int[A.length];

        sort(A, 0, A.length - 1, helper);
        Map map = new HashMap<>();

        long endTime = System.currentTimeMillis();
        totalRuntime4 = (endTime - startTime);

    }


    private static void sort(int[] a, int lo, int hi, int[] helper) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, helper);
        sort(a, mid + 1, hi, helper);
        merge(a, lo, mid, hi, helper);
    }

    private static void merge(int[] a, int lo, int mid, int hi, int[] helper) {

        for (int i = lo; i <= hi; i++) helper[i] = a[i];
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = helper[j++];
            else if (j > hi)
                a[k] = helper[i++];
            else if (helper[i] <= helper[j])
                a[k] = helper[i++];
            else
                a[k] = helper[j++];
        }

    }


    public void QuickSort(int A[]) {

        long startTime = System.currentTimeMillis();

        qs(A, 0, A.length - 1);

        long endTime = System.currentTimeMillis();
        totalRuntime5 = (endTime - startTime);
    }


    private static void qs(int items[], int left, int right)

    {


        int i, j;
        int pivot, temp;
        i = left;
        j = right;
        pivot = items[(left + right) / 2];

        do {
            while ((items[i] < pivot) && (i < right)) {
                i++;
            }

            while ((pivot < items[j]) && (j > left)) {
                j--;
            }

            if (i <= j) {
                temp = items[i];
                items[i] = items[j];
                items[j] = temp;
                i++;
                j--;
            }
        }
        while (i <= j);

        if (left < j) {
            qs(items, left, j);
        }

        if (i < right) {
            qs(items, i, right);


        }

    }


    public void HeapSort(int A[]) {

        long startTime = System.currentTimeMillis();
        int n = A.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(A, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;


            // call max heapify on the reduced heap
            heapify(A, i, 0);

        }
        long endTime = System.currentTimeMillis();
        totalRuntime6 = (endTime - startTime);
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public void CountingSort(int A[]) {
        long startTime = System.currentTimeMillis();

        int max = Arrays.stream(A).max().getAsInt();
        int min = Arrays.stream(A).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            count[A[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = A.length - 1; i >= 0; i--) {
            output[count[A[i] - min] - 1] = A[i];
            count[A[i] - min]--;
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = output[i];
        }
        long endTime = System.currentTimeMillis();
        totalRuntime7 = (endTime - startTime);
    }


    public void RadixSort(int A[]) {
        long startTime = System.currentTimeMillis();


        final int RADIX = 10;
        // declare and initialize bucket[]
        List<Integer>[] bucket = new ArrayList[RADIX];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }

        // sort
        boolean maxLength = false;
        int tmp = -1, placement = 1;
        while (!maxLength) {
            maxLength = true;
            // split input between lists
            for (Integer i : A) {
                tmp = i / placement;
                bucket[tmp % RADIX].add(i);
                if (maxLength && tmp > 0) {
                    maxLength = false;
                }
            }
            // empty lists into input array
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                for (Integer i : bucket[b]) {
                    A[a++] = i;
                }
                bucket[b].clear();
            }
            // move to next digit
            placement *= RADIX;
        }
        long endTime = System.currentTimeMillis();
        totalRuntime8 = (endTime - startTime);
    }

    public void summaryofSortingAlgorithms() {
        System.out.println("*****Summary of Sorting Algorithms*****");
        System.out.println(" ");
        System.out.println("Using Bubble Sort It took-> " + totalRuntime1 + " milliseconds");
        System.out.println("Using Selection Sort It took-> " + totalRuntime2 + " milliseconds");
        System.out.println("Using Insertion Sort It took-> " + totalRuntime3 + " milliseconds");
        System.out.println("Using Merge Sort It took-> " + totalRuntime4 + " milliseconds");
        System.out.println("Using Quick Sort It took-> " + totalRuntime5 + " milliseconds");
        System.out.println("Using Heap Sort It took-> " + totalRuntime6 + " milliseconds");
        System.out.println("Using Counting Sort It took-> " + totalRuntime7 + " milliseconds");
        System.out.println("Using Radix Sort It took-> " + totalRuntime8 + " milliseconds");
        System.out.println(" ");

        long[] arr = {totalRuntime1, totalRuntime2, totalRuntime3, totalRuntime4, totalRuntime5, totalRuntime6, totalRuntime7, totalRuntime8};
        String[] str = {"Bubble", "Selection", "Insertion", "Merge", "Quick", "Heap", "Counting", "Radix"};
        long min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

                if (min == arr[0]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[0] + " Sort ");

                } else if (min == arr[1]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[1] + " Sort ");

                } else if (min == arr[2]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[2] + " Sort ");

                } else if (min == arr[3]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[3] + " Sort ");

                } else if (min == arr[4]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[4] + " Sort ");

                } else if (min == arr[5]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[5] + " Sort ");

                } else if (min == arr[6]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[6] + " Sort ");

                } else if (min == arr[7]) {

                    System.out.println("Acording to the given input the most successful algorithm is " + str[7] + " Sort ");
                }


            }

        }








