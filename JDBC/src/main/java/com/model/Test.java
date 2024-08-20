package com.model;

import org.hibernate.Session;

import jakarta.transaction.Transaction;


public class Test {

	public static void main(String[]args)
	{
		Student s1=new Student("ABINESH",99,"BTECH");
		Student s2=new Student("ABISHEIK",99,"BE");
		Student s3=new Student("ABISHEK SK",99,"BE");
		Student s4=new Student("AJAY",100,"BE");
		Student s5=new Student("ANANTHAKUMAR",100,"BE");
		Student s6=new Student("AMUTHAN",100,"BE");
		Student s7=new Student("ARULKUMAR",100,"BE");
		
		org.hibernate.Transaction tr=null;
		try(Session ses=HButil.getSesFactory().openSession()){
			tr=ses.beginTransaction();  //start transaction
			
			ses.persist(s1);
			ses.persist(s2);
			ses.persist(s3);
			ses.persist(s4);
			ses.persist(s5);
			ses.persist(s6);
			ses.persist(s7);
			tr.commit();  //edns transaction /saves all changes
		}
		catch(Exception e) {
			if(tr==null) {
				tr.rollback();
				e.printStackTrace();
			}
		}
	
	}
	
}