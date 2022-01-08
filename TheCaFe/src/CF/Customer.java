package CF;

import java.util.Scanner;

public  class Customer {
	public Scanner sc = new Scanner(System.in);
	private String id;
	protected String rank;
	private String name;
	private String phone;
	
	public Customer() {
	
	}
	public Customer(String id, String rank, String name, String phone) {
		this.id = id;
		this.rank = rank;
		this.name = name;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public void Input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã số Khách hàng:");
		id = sc.nextLine();
		while(checkID(id)) {
			System.err.println("Mã khách hàng đã tồn tại !");
			System.out.print("Nhập lai mã sản khách hàng: ");
			id = sc.nextLine();
		}
		System.out.println("Nhập họ tên Khách hàng:");
		name = sc.nextLine();
		System.out.println("Nhập độ thân thiết Khách hàng:");
		rank = sc.nextLine();		
		System.out.println("Nhập số điện thoại Khách hàng:");
		setPhone(phone =sc.nextLine());
	}
	public String toString2() {
		return id+","+rank.toUpperCase()+","+name+","+phone;
	}
	@Override
	public String toString() {
		return String.format("%-5s %-5s %-15s %-15s\n",id,rank.toUpperCase(),name,phone);
	}
	public void Display() {
		System.out.println(toString());
	}
	public String toString1() {
		return String.format("%-5s %-5s %-15s %-15s\n","KHÁCH HÀNG : ",id,name,phone);
	}
	public void Display1() {
		System.out.println(toString1());
	}
	public boolean checkID(String s) {
		try {
			for(int i = 0 ; i < ManagerCustomer.cus.length ; i++) {
				if(ManagerCustomer.cus[i].getId().equals(s)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
