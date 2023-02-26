package dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@SequenceGenerator(initialValue = 12132001, allocationSize = 1, name = "custid", sequenceName = "custid")
	@GeneratedValue(generator = "custid")
	int custid;
	String name;
	String email;
	long mobile;
	Date dob;
	String gender;
	String password;
	String address;
	@Lob
	byte[] pan;
	boolean status;
}
