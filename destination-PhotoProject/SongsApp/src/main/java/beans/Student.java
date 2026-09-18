package beans;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
@Entity
@Table(name="Student")
public class Student {
	@Id 
	private int id;
	private String name;
	private int std;
	public Student() {}
	public Student(int id, String name, int std) {
		this.id=id;
		this.name=name;
		this.std=std;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getStd() {
		return std;
	}
	public void setStd(int std) {
		this.std=std;
	}

}
