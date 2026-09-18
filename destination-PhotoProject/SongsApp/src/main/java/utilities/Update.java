package utilities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import beans.Student;
import utilities.SessionFactoryProvider;
public class Update {
	public static void main(String[]args) {
		SessionFactory sessionFactory=SessionFactoryProvider.provideSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Student s=session.get(Student.class, 101);
		s.setStd(11);
		tx.commit();
		session.close();
		sessionFactory.close();
		System.out.println("Student record updated successfully");
	}
}
