import java.util.Arrays;

public class Tesr {
	public static void main(String[] args) {
		int[] a = new int[] {2,3,1,5,4,9,0};
		int start = 0;
		int end = a.length - 1;
//		sort(a,start,end);
		BinaryInsertSort(a,start,end);
		for(int i = 0; i<a.length; i++){
            System.out.println(a[i]);
        }
	}
	
	public static void sort(int[] a, int low, int high) {
		int start = low;
		int end = high;
		int key = a[low];
		while(end > start) {
			// 从后往前比较
			while(end > start && a[end] >= key) 
				{end--;}
				if(a[end] <= key) {
					int tmp = a[end];
					a[end] = a[start];
					a[start] = tmp;
				}
				while(end>start && a[start]<=key) 
					{start++;}
					if(a[start]>=key) {
						int tmp = a[start];
						a[start] = a[end];
						a[end] = tmp;
					}
				}
		if(start>low) sort(a,low,start-1);
		if(end<high) sort(a,end+1,high);
	}
	public static void BinaryInsertSort(int[] a, int left, int right) {
	    int low, middle, high;
	        int temp;
	    for (int i = left + 1; i <= right; i++) {
	            temp = a[i];
	            low = left;
	            high = i - 1;
	            while (low <= high) {
	                middle = (low + high) / 2;
	                if (a[i] < a[middle])
	                    high = middle - 1;
	                else
	                    low = middle + 1;
	        }

	            for (int j = i - 1; j >= low; j--)
	                    a[j + 1] = a[j];

	            a[low] = temp;
	        }
	}
}
