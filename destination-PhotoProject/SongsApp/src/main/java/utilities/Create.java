package utilities;

import utilities.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import beans.Student;

public class Create {
	public static void main(String[]args) {
		SessionFactory sessionFactory=SessionFactoryProvider.provideSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Student s=new Student(101,"Harsha",10);
		session.merge(s);
		tx.commit();
		session.close();
		sessionFactory.close();
		System.out.println("Student record created successfully");
	}
}
