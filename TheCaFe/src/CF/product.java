package CF;

import java.util.Scanner;

public abstract class product {
	public Scanner sc = new Scanner(System.in);
	protected String id;
	protected String name;
	protected int price;
	protected expiry ex = new expiry();
	protected supplier sup = new supplier();
	
	public product() {
		super();
	}
	
	public product(String id, String name, int price, expiry ex, supplier sup) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.ex = ex;
		this.sup = sup;
	}

	public product(String id, String name, int price, supplier sup) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.sup = sup;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		while(true) {			
			if(price > 0 ) 
				break;
				System.err.print("Nhap sai, nhap lai:");
				price=Integer.parseInt(sc.nextLine());
			this.price = price;
		}		
	}
	
	public expiry getEx() {
		return ex;
	}

	public void setEx(expiry ex) {
		this.ex = ex;
	}

	public supplier getSup() {
		return sup;
	}

	public void setSup(supplier sup) {
		this.sup = sup;

	}

	abstract void Input();
	abstract void Display();
	abstract void Display1();
	abstract void ReadList(); 
}
