import java.util.Arrays;

public class ClosestAntennaPair {

	private double closestDistance = Double.POSITIVE_INFINITY;
	private long counter = 0;

	public ClosestAntennaPair(Point2D[] aPoints, Point2D[] bPoints) {
		// Insert your solution here.
		/**
		 * aPoints
		 */
		int an = aPoints.length;
		if (an <= 0) return;
		Point2D[] aPointsSortedByX = new Point2D[an];
		for (int i = 0; i < an; i++)
			aPointsSortedByX[i] = aPoints[i];
		Arrays.sort(aPointsSortedByX, Point2D.Y_ORDER);
		Arrays.sort(aPointsSortedByX, Point2D.X_ORDER);

		/**
		 * bPoints
		 */
		int bn = bPoints.length;
		if (bn <= 0) return;
		Point2D[] bPointsSortedByX = new Point2D[bn];
		for (int i = 0; i < bn; i++)
			bPointsSortedByX[i] = bPoints[i];
		Arrays.sort(bPointsSortedByX, Point2D.Y_ORDER);
		Arrays.sort(bPointsSortedByX, Point2D.X_ORDER);

		/**
		 * // set up the array that eventually will hold the points sorted by y-coordinate
		 */
		Point2D[] aPointsSortedByY = new Point2D[an];
		for (int i = 0; i < an; i++)
			aPointsSortedByY[i] = aPointsSortedByX[i];

		Point2D[] bPointsSortedByY = new Point2D[bn];
		for (int i = 0; i < bn; i++)
			bPointsSortedByY[i] = bPointsSortedByX[i];

		// auxiliary array
		Point2D[] auxA = new Point2D[an];
		Point2D[] auxB = new Point2D[bn];

		closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, 0, 0, an-1, bn-1);
	}

	public double closest(Point2D[] aPointsSortedByX, Point2D[] bPointsSortedByX, // a/b sorted by X
			Point2D[] aPointsSortedByY, Point2D[] bPointsSortedByY, // a/b sorted by y
			Point2D[] auxA, Point2D[] auxB,  // a/b auxiliary array
			int lowA, int lowB, int highA, int highB) { // a/b's lowest/highest indices
		// high/low means the indices of the array list
		// please do not delete/modify the next line!
		counter++;
		if(highA<lowA || highB<lowB) { //悲剧结局，没点
			closestDistance = Double.POSITIVE_INFINITY;
			return closestDistance;
		}

		if(highA==lowA && highB==lowB) { //轻松结局，都一个
			double distance = aPointsSortedByX[0].distanceTo(bPointsSortedByX[0]);
			if(distance<closestDistance) {
				closestDistance = distance;
				return closestDistance;
			}else {
				return distance;
			}
		}

		if(highA==lowA) { //一个A，
			for(int i = lowB; i<=highB;i++) {
				double distance = bPointsSortedByX[i].distanceTo(aPointsSortedByX[0]);
				if(distance<closestDistance) {
					closestDistance =distance;
				}
			}
			return closestDistance;

		}
		if(highB==lowB) { //一个B，
			for(int i = lowA; i<=highA;i++) {
				double distance = aPointsSortedByX[i].distanceTo(bPointsSortedByX[lowB]);
				if(distance<closestDistance) {
					closestDistance =distance;
				}
			}
			return closestDistance;

		}
		if(highA>lowA) { //一群A，先干A，B不止一个
			int midA = lowA + (highA - lowA) / 2;            // if low==high then mid==low
			Point2D median = aPointsSortedByX[midA];

			double delta1 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, lowA, lowB, midA, highB);     
			double delta2 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, midA+1, lowB, highA, highB); 
			double delta = Math.min(delta1, delta2);

			merge(aPointsSortedByY, auxA, lowA, midA, highA);
			int midB = lowB + (highB - lowB) / 2;
			merge(bPointsSortedByY,auxB,lowB,midB,highB);

			int m = 0;
			int m2 = 0;
			for (int i = lowA; i <= highA; i++) {
				if (Math.abs(aPointsSortedByY[i].x() - median.x()) < delta) {
					auxA[m] = aPointsSortedByY[i];
					m++;
				}
			}//如果点的 x 在median附近小于delta的范围，则写入array aux----之后会从上到下走一编y value to check the new min
			for (int i = lowB; i <= highB; i++) {
				if (Math.abs(bPointsSortedByY[i].x() - median.x()) < delta) {
					auxB[m2] = bPointsSortedByY[i];
					m2++;
				}
			}//如果点的 x 在median附近小于delta的范围，则写入array aux----之后会从上到下走一编y value to check the new min

			for (int i = 0; i <
					m; i++) {
				for (int j = 0; (j < m2); j++) {
					double distance = auxA[i].distanceTo(auxB[j]);
					if (distance < delta) {
						delta = distance;
						if (distance < closestDistance) {
							closestDistance = delta;
							return closestDistance;
						}else {
							return delta;
						}
					}
				}
			}
		}
		return closestDistance;
	}



	public double distance() {
		return closestDistance;
	}

	public long getCounter() {
		return counter;
	}

	// stably merge a[low .. mid] with a[mid+1 ..high] using aux[low .. high]
	// precondition: a[low .. mid] and a[mid+1 .. high] are sorted subarrays, namely sorted by y coordinate
	// this is the same as in ClosestPair
	private static void merge(Point2D[] a, Point2D[] aux, int low, int mid, int high) {
		// copy to aux[]
		// note this wipes out any values that were previously in aux in the [low,high] range we're currently using

		for (int k = low; k <= high; k++) {
			aux[k] = a[k];
		}

		int i = low, j = mid + 1;
		for (int k = low; k <= high; k++) {
			if (i > mid) a[k] = aux[j++];   // already finished with the low list ?  then dump the rest of high list
			else if (j > high) a[k] = aux[i++];   // already finished with the high list ?  then dump the rest of low list
			else if (aux[i].compareByY(aux[j]) < 0)
				a[k] = aux[i++]; // aux[i] should be in front of aux[j] ? position and increment the pointer
			else a[k] = aux[j++];
		}
	}
}

