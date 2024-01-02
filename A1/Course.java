public class Course {
	public String code;
	public int capacity;
	public SLinkedList<Student>[] studentTable;
	public int size;
	public SLinkedList<Student> waitlist;

	public Course(String code) {
		this.code = code;
		this.studentTable = new SLinkedList[10];
		this.size = 0;
		this.waitlist = new SLinkedList<Student>();
		this.capacity = 10;
	}

	public Course(String code, int capacity) {
		this.code = code;
		this.studentTable = new SLinkedList[capacity];
		this.size = 0;
		this.waitlist = new SLinkedList<>();
		this.capacity = capacity;
	}

	public void changeArrayLength(int m) {
		// insert your solution here
		SLinkedList<Student>[] ns = new SLinkedList[m]; // new list
		int sID;
		Student sStu;
		for(int i=0; i<studentTable.length;i++) { // go through the x index
			if(studentTable[i]!=null) {
				while(studentTable[i].size()!=0) { // go through the y index
					sID = studentTable[i].getFirst().id; // get id
					sStu = studentTable[i].getFirst();
					// settle down the id and field in the new array
					if(ns[sID%m]==null) {
						ns[sID%m]=new SLinkedList<>();
					}
					ns[sID%m].addFirst(sStu); 
					studentTable[i].removeFirst();
				}
			}
		}
		this.studentTable = ns; // get the new table
	}

	public boolean put(Student s) {
		int waitCapacity = capacity/2;
		int sId = s.id;
		// if s is not in class and s registered <3, than go if/ else, false
		if((s.isRegisteredOrWaitlisted(this.code)!=true) && (s.courseCodes.size() < s.COURSE_CAP)){ 
			// if course is full
			if(size==capacity) {
				if(waitlist.size()==waitCapacity) { // waitlist is also full
					changeArrayLength((int)(capacity*1.5));  // make course bigger
					this.size *= 1.5;
					while(waitlist.size()!=0) { // move waitlist to new bigger course
						Student fws = waitlist.getFirst(); // set new field to contain student(first waited stu) in waitlist
						studentTable[(fws.id)%((int)(capacity*1.5))].addLast(fws); // move one to new class
						waitlist.removeFirst(); // delete one
					} 
					waitlist.addLast(s); // move s to waitlist
					s.courseCodes.addLast(code); // Student register courses code +1
				}else { // waitlist is not full, move s to waitlist directly
					waitlist.addLast(s);
					s.courseCodes.addLast(code);
				}
			}
			else if(size<capacity){ // Course have spaces
				// initialize the SLinkedList<>()
				if (studentTable[sId%studentTable.length] == null){
					studentTable[sId%studentTable.length]=new SLinkedList<>();
				}
				studentTable[sId%studentTable.length].addLast(s);
				this.size +=1;
				s.courseCodes.addLast(code);
			}
			return true; 
			// successfully registered
			// either in the first of new waitlist (after the extension) or last of the old waitlist
			// or the courseTable
		}else {
			return false;
		}
	}

	public Student get(int id) {
		if(studentTable[id%studentTable.length]!=null) { // check whether it is null/ saving time of initialization
			for(int j =0; j<studentTable[id%studentTable.length].size();j++) { // go through y 
				if(studentTable[id%studentTable.length].get(j).id == id) { 
					return studentTable[id%studentTable.length].get(j); // return student field
				}
			}
		}
		for(int i =0; i<waitlist.size();i++) { // go through the waitlist
			if (waitlist.get(i).id == id) {
				return waitlist.get(i); // return student field
			}
		}
		return null;
	}


	public Student remove(int id) {
		if(studentTable[id%studentTable.length]!=null) { // maybe in the stuTable
			for(int j =0; j<studentTable[id%studentTable.length].size();j++) {
				if(studentTable[id%studentTable.length].get(j).id == id) {
					Student rs = studentTable[id%studentTable.length].get(j);
					studentTable[id%studentTable.length].remove(j);
					rs.courseCodes.remove(code);
					if(waitlist.size()!= 0) { // somebody is waiting, moving first waitlist to stuTable
						Student fws = waitlist.getFirst(); //First Waitlist Student
						studentTable[(fws.id)%(studentTable.length)].addLast(fws); // move one to new class
						waitlist.removeFirst();
					}else {
						this.size --;
					}
					return rs;
				}
			}
		}
		for(int i =0; i<waitlist.size();i++) { //maybe in the waitlist
			if (waitlist.get(i).id == id) {
				Student rs =waitlist.get(i);
				waitlist.remove(i);
				rs.courseCodes.remove(code);
				return rs;
			} 
		}
		return null;
	}



	public int getCourseSize() {
		return this.size;
	}


	public int[] getRegisteredIDs() {
		// insert your solution here and modify the return statement
		int[] sRegisIDs = new int [getCourseSize()];
		int n = 0;
		for(int i=0; i<studentTable.length;i++) { // go through the x index
			if(studentTable[i] == null) {
				studentTable[i] = new SLinkedList<Student>();
			}
			for(int j=0; j<studentTable[i].size(); j++) { // go through the y index
				sRegisIDs [n] = studentTable[i].get(j).id;
				n++;
			}
		}
		return sRegisIDs;
	}

	public Student[] getRegisteredStudents() {
		// insert your solution here and modify the return statement
		Student[] sRegisStus = new Student [getCourseSize()];
		int n = 0;
		for(int i=0; i<studentTable.length;i++) { // go through the x index
			if(studentTable[i] != null) {	
				for(int j=0; j<studentTable[i].size(); j++) { // go through the y index
					sRegisStus [n] = studentTable[i].get(j);
					n++;
				}
			}
		}
		return sRegisStus;
	}

	public int[] getWaitlistedIDs() {
		// insert your solution here and modify the return statement
		int[] waitIDs =new int[waitlist.size()];
		for(int i=0; i < waitlist.size(); i++) {
			waitIDs[i] = waitlist.get(i).id; //just use i directly??
		}
		return waitIDs;
	}

	public Student[] getWaitlistedStudents() {
		// insert your solution here and modify the return statement
		Student[] waitStus =new Student[waitlist.size()];
		for(int i=0; i < waitlist.size(); i++) {
			waitStus[i] = waitlist.get(i); //just use i directly??
		}
		return waitStus;
	}

	public String toString() {
		String s = "Course: "+ this.code +"\n";
		s += "--------\n";
		for (int i = 0; i < this.studentTable.length; i++) {
			s += "|"+i+"     |\n";
			s += "|  ------> ";
			SLinkedList<Student> list = this.studentTable[i];
			if (list != null) {
				for (int j = 0; j < list.size(); j++) {
					Student student = list.get(j);
					s +=  student.id + ": "+ student.name +" --> ";
				}
			}
			s += "\n--------\n\n";
		}

		return s;
	}
}
