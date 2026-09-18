package utilities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import beans.Student;
import utilities.SessionFactoryProvider;
public class Delete {
	public static void main(String[]args) {
		SessionFactory sessionFactory=SessionFactoryProvider.provideSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Student s=session.get(Student.class, 101);
		if(s!=null) {
			session.remove(s);
			System.out.println("Student record is successfully deleted");
		}else {
			System.out.println("Student is not found");
		}
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
