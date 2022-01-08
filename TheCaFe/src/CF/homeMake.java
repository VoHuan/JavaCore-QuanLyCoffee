package CF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class homeMake extends product{
	static product list_HM[] = new homeMake[50];
	static int len_hm;
	String size ;
	
	public homeMake() {
		super();
	}

	public homeMake(String id, String name, int price, supplier sup) {
		super(id, name, price, sup);
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	void Input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập mã sản phẩm:");
		 id = sc.nextLine();
		 while(checkID(id)) {
				System.err.println("Sản phẩm đã tồn tại !");
				System.out.print("Nhập lai mã sản phẩm: ");
				id = sc.nextLine();
			}
		System.out.println("Nhập tên sản phẩm:");
		 name = sc.nextLine();
		System.out.println("Nhập giá sản phẩm:");
		setPrice(price = Digital());
		System.out.println("Nhập thông tin nhà cung cấp:");
		sup.Input();	
	}

	public String toString() {
		return id+","+name+","+price+","+sup.ToFile();
	}
	public String toString1() {
		return String.format("%-5s %-15s %-15s",id,name,price);
	}
	public String toString2() {
		return String.format("%-5s %-15s %-10s %-35s\n",id,name,price,sup);
	}
	@Override
	void Display() {
		System.out.println(toString2());
		
	}

	@Override
	void Display1() {
		System.out.print(toString1());		
	}
	
	public boolean checkID(String s) {
		try {
			for(int i = 0 ; i < ManagerProduct.pr.length ; i++) {
				if(ManagerProduct.pr[i].getId().equals(s)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static int Digital() {
        boolean flag = false;
        String inp;
        do {
            String pattern = "\\d*";
            inp = new Scanner(System.in).next();
            flag = inp.matches(pattern);
            if (!flag) {
                System.err.println("Không hợp lệ! Nhập lại!!!");
            }
        } while (!flag);
        return Integer.parseInt(inp);
    }

	@Override
	void ReadList() {
		try {
			FileReader fr = new FileReader("homeMake.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while(true) {
				line = br.readLine();
				if(line == null)
					break;
				String txt[] = line.split(",");
				String id = txt[0];
				String name = txt[1];
				int price = Integer.parseInt(txt[2]);
				String nameSup = txt[3];
				String addressSup = txt[4];
				supplier sup = new supplier(nameSup,addressSup);
				list_HM[len_hm] = new homeMake(id,name,price,sup);
				len_hm++;
			}
			System.out.println("Cập nhật liệu thành công !");
		} 
		catch (Exception e) {
	        System.err.println("Không thể lấy dữ liệu 1"); 
	    }
	}
}
