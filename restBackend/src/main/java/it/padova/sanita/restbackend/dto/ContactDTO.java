package it.padova.sanita.restbackend.dto;


public class ContactDTO
{
  Long id;
  String email;
  String name;
  String phoneNumber;
  
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public ContactDTO()
  {
  }

}
