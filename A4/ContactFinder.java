
import java.util.ArrayList;
import java.util.HashSet;

public class ContactFinder {
    // todo for students
    // Returns a HashSet of ContactResult objects representing all the contacts between circles in the scene.
    // The runtime of this method should be O(n^2) where n is the number of circles.
    public static HashSet<ContactResult> getContactsNaive(ArrayList<Circle> circles) {
    	HashSet<ContactResult> h = new HashSet<ContactResult>();
        for(int i =0;i<circles.size();i++) {
          Circle c = circles.get(i);
        	for(int j=circles.size()-1;j>-1;j--) {
        		Circle compare = circles.get(j);
        		if(c.id!= compare.id) {
        			if(c.isContacting(compare) != null) {
        				ContactResult result = c.isContacting(compare); 
        				h.add(result);
        			}
        		}
        	}
        }
		return h;
    }

    // todo for students
    // Returns a HashSet of ContactResult objects representing all the contacts between circles in the scene.
    // The runtime of this method should be O(n*log(n)) where n is the number of circles.
    public static HashSet<ContactResult> getContactsBVH(ArrayList<Circle> circles, BVH bvh) {
    	HashSet<ContactResult> h = new HashSet<ContactResult>();
    	for(int i=0; i<circles.size();i++) {
    		HashSet<ContactResult> h1 = getContactBVH(circles.get(i), bvh);
    		for(ContactResult e : h1) {
    			h.add(e);
    		}
    	}
    	
        return h;
        
    }

    // todo for students
    // Takes a single circle c and a BVH bvh.
    // Returns a HashSet of ContactResult objects representing contacts between c
    // and the circles contained in the leaves of the bvh.
    public static HashSet<ContactResult> getContactBVH(Circle c, BVH bvh) {
    	HashSet<ContactResult> h = new HashSet<ContactResult>();
    	Box cbx = c.getBoundingBox();
    	Box bx = bvh.boundingBox;
    	BVH b1 = bvh.child1;
    	BVH b2 = bvh.child2;
    	if(cbx.intersectBox(bx)!=true) {
    		return h;
    	}
    	if(bvh.containedCircle!=null) {
    		if(c.isContacting(bvh.containedCircle)!=null && c.id != bvh.containedCircle.id) {
    			h.add(c.isContacting(bvh.containedCircle));
    		}
    		//return h;
    	}
    	if(bvh.containedCircle == null) {
    		HashSet<ContactResult> h1 = getContactBVH(c, b1);
    		HashSet<ContactResult> h2 = getContactBVH(c, b2);
    		for(ContactResult e : h1) {
    			h.add(e);
    		}
    		for(ContactResult f : h2) {
    			h.add(f);
    		}
    		//return h;
    	}
        return h;
        //return null;
    }
}

