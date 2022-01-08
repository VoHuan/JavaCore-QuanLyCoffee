package CF;

import java.util.Scanner;

public class supplier {
	 String nameSup;
	 String addressSup;
	
	
	public supplier() {
	}


	public supplier(String nameSup, String addressSup) {
		this.nameSup = nameSup;
		this.addressSup = addressSup;
	}


	public String getnameSup() {
		return nameSup;
	}


	public void setnameSup(String nameSup) {
		this.nameSup = nameSup;
	}


	public String getaddstressSup() {
		return addressSup;
	}


	public void setaddstressSup(String addressSup) {
		this.addressSup = addressSup;
	}
	
	public void Input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập tên nhà cung cấp sản phẩm: ");
		nameSup = sc.nextLine();
		System.out.print("Nhập địa chỉ nhà cung cấp sản phẩm: ");
		addressSup = sc.nextLine();
	}
	
	@Override
	public String toString() {
		return " Tên: " +nameSup + ", Địa chỉ: " +addressSup ;
	}
	public String ToFile() {
		return nameSup + "," +addressSup ;
	}

	public void Display() {
		System.out.println(toString());
	}
}
