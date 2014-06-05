package com.tunus.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact {
	
	@Id
    @GeneratedValue
    @Column
    private Integer id;
	
	@Column(length = 100, nullable = false)
	    private String name;
    
    @Column(length = 200, nullable = false)
    private String address;
    
    @Column(length = 100, nullable = false)
    private String phonenumber;
    
    public Contact() {
        // for hibernate.
    }
    
    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address=address;
        this.phonenumber=phone;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
