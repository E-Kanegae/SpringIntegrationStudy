package org.ek.sample.domain;

import lombok.Data;

@Data
public class User {

	private String username;
	
	private String email;
	
	private String password;
	
	public User() {
		super();
		this.username = "Foo";
	}
	
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=").append(this.username).append(", password=").append(this.password)
				.append(", email=").append(this.email).append("]");
		return builder.toString();
	}

}
