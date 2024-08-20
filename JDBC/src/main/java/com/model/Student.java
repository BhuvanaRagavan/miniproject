package com.model;
import jakarta.persistence.*;


	@Entity
	@Table(name="Record")
	public class Student {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		int Id;
		String Name;
		float Marks;
		String Course;
		public Student(String Name,float Marks,String Course) {
			super();
			this.Name=Name;
			this.Marks=Marks;
			//this.id=id;
			this.Course=Course;
		}
		public int getId() {
			return Id;
		}
		public void setId(int Id) { 
			this.Id = Id;
		}
		public String getName() {
			return Name;
		}
		public void setName(String Name) {
			this.Name = Name;
		}
		public float getMarks() {
			return Marks;
		}
		public void setMarks(float Marks) {
			this.Marks = Marks;
		}
		public String getCourse() {
			return Course;
		}
		public void setCourse(String Course) {
			this.Course = Course;
		}

	}


