package it.padova.sanita.restbackend.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Contact", schema="davide")
@SequenceGenerator(name="IdContactSequenceGen",sequenceName="SEQ_CONTACT_ID",allocationSize=1,initialValue=1)
public class Contact
{
  private Long id;
  
  private String email;
  
  private String name;
  
  private String phoneNumber;

  @Id
  @Column(name="ID", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdContactSequenceGen")
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }
  
  @Column(name="EMAIL", nullable = false, length = 100)
  public String getEmail()
  {
    return email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }

  @Column(name="NAME", nullable = false, length = 255)
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Column(name="PHONENUMBER", nullable = true, length = 2000)
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }
}
