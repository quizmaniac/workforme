package tk.services.main.model;

import java.util.Date;

public class User {
	private String username;
    private String name;
    private String email;
    private String password;
    private String number;
    private String dob;
    private String address;

    public User() {
		super();
		this.username = "";
		this.name = "";
		this.email = "";
		this.password = "";
		this.number = "";
		this.dob = "";
		this.address = "";
	}
    
    public User(String username, String name, String email, String password, String number, String dob,
			String address) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.number = number;
		this.dob = dob;
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

}