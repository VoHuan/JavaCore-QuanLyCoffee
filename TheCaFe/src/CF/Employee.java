package CF;

import java.util.Calendar;
import java.util.Scanner;

public abstract class Employee {
	Scanner sc= new Scanner(System.in);
	protected String id;
	protected String name;
	protected int year;
	protected String address;
	protected String phone;
	protected String mail;
	protected String shift;

	public Employee() {
		
	}
	
	public Employee(String id, String name, int year, String address, String phone, String mail) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.address = address;
		this.phone = phone;
		this.mail = mail;
	}
	
	
	public Employee(String id, String name, int year, String address, String phone, String mail, String shift) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.address = address;
		this.phone = phone;
		this.mail = mail;
		this.shift = shift;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		while(true) {			
			if(2021-year>=18 && 2021-year<=50 ) 
				break;
				System.err.print("Nhap sai, nhap lai:");
				year=Integer.parseInt(sc.nextLine());
			this.year = year;
		}		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		while(true) {			
			if(phone.length()==10) 
				break;
				System.err.print("Nhap sai, nhap lai:");
				phone=sc.nextLine();
			this.phone = phone;
		}		
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public abstract void Input();
	public abstract void Display();
	public abstract void Display1();
	public abstract void ReadList();	
	//Hàm tìm tuổi
	public int getAge() {
			Calendar now = Calendar.getInstance();
			return now.get(Calendar.YEAR)- this.getYear();
		}
	
}
