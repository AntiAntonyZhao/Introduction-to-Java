
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BVH implements Iterable<Circle>{
	Box boundingBox;

	BVH child1;
	BVH child2;

	Circle containedCircle;

	// todo for students
	public BVH(ArrayList<Circle> circles) {
		this.boundingBox=buildTightBoundingBox(circles);
		if(circles.size()>1) {
			ArrayList<Circle>[] A = split (circles, boundingBox);
			this.child1= new BVH(A[0]);
			this.child2= new BVH(A[1]);
		}
		if(circles.size()==1) {
			this.containedCircle=circles.get(0);
		}

	}

	public void draw(Graphics2D g2) {
		this.boundingBox.draw(g2);
		if (this.child1 != null) {
			this.child1.draw(g2);
		}
		if (this.child2 != null) {
			this.child2.draw(g2);
		}
	}

	// todo for students
	public static ArrayList<Circle>[] split(ArrayList<Circle> circles, Box boundingBox) {
		ArrayList<Circle>[] A = new ArrayList[2];
		A[0] = new ArrayList<Circle>();
		A[1] = new ArrayList<Circle>();
		double bx = boundingBox.getWidth();
		double bmx=boundingBox.getMidX();
		double by = boundingBox.getHeight();
		double bmy = boundingBox.getMidY();
		if(circles.size()>1) {
			if(bx>=by) { // 更宽，分Width
				for(int i=0;i<circles.size();i++) {
					if(circles.get(i).getPosition().x <= bmx) {
						A[0].add(circles.get(i));
					}
					if(circles.get(i).getPosition().x > bmx) {
						A[1].add(circles.get(i));
					}
				}
			}
			if(bx<by) { // 更高，分Height
				for(int i=0;i<circles.size();i++) {
					if(circles.get(i).getPosition().y <= bmy) {
						A[0].add(circles.get(i));
					}
					if(circles.get(i).getPosition().y > bmy) {
						A[1].add(circles.get(i));
					}
				}
			}
		}
		return A;
	}

	// returns the smallest possible box which fully encloses every circle in circles
	public static Box buildTightBoundingBox(ArrayList<Circle> circles) {
		Vector2 bottomLeft = new Vector2(Float.POSITIVE_INFINITY);
		Vector2 topRight = new Vector2(Float.NEGATIVE_INFINITY);

		for (Circle c : circles) {
			bottomLeft = Vector2.min(bottomLeft, c.getBoundingBox().bottomLeft);
			topRight = Vector2.max(topRight, c.getBoundingBox().topRight);
		}

		return new Box(bottomLeft, topRight);
	}


	// METHODS BELOW RELATED TO ITERATOR

	// todo for students
	@Override
	public Iterator<Circle> iterator() {
		return new BVHIterator(this);
	}
	public class BVHIterator implements Iterator<Circle> {
		public List<Circle> l; // List
		public int ll; // List size
		public int li; // List index
		// todo for students
		public BVHIterator(BVH bvh) {
			this.li = 0;
			this.l = s(bvh);
			this.ll = l.size();
		}
		public static List<Circle> s (BVH bvh) {
			List<Circle> cl = new ArrayList<Circle>();
			BVH b1 = bvh.child1;
			BVH b2 = bvh.child2;
			if(bvh.containedCircle != null) {
				cl.add(bvh.containedCircle);
			}else {
				List<Circle> cl1 = s(b1); 
				List<Circle> cl2 = s(b2);
				for(int i =0; i<cl1.size();i++) {
					cl.add(cl1.get(i));
				}
				for(int i =0; i<cl2.size();i++) {
					cl.add(cl2.get(i));
				}
			}			
			return cl;
		}
		// todo for students
		@Override
		public boolean hasNext() {
			if(li<ll && li>=0) {
				return true;
			}
			return false;
		}
		// todo for students
		@Override
		public Circle next() {
			Circle c = l.get(li);
			li ++; 
			return c;
		}
	}
}
