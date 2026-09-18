package com.normal.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role Role1;

    private String role;
    public enum Role {
        ADMIN,
        TEAM_MEMBER
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		// TODO Auto-generated method stub
		
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getRole1() {
		return role;
	}
	public void setRole1(String role1) {
		// TODO Auto-generated method stub
		
	}
	


}
