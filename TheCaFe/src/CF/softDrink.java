package CF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
public class softDrink extends product {
	static product list_SD[] = new softDrink[50];
	static int len_sd;
	
	public softDrink() {
		super();
	}

	public softDrink(String id, String name, int price, expiry ex, supplier sup) {
		super(id, name, price, ex, sup);
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
		System.out.println("Nhập thông tin hạn sử dụng:");
		ex.Input();
	}
	
	public String toString2() {
		return String.format("%-5s %-15s %-10s %-40s %-15s\n",id,name,price,sup,ex);
	}
	public String toString() {
		return id+","+name+","+price+","+ex.ToFile()+","+sup.ToFile();
	}

	void Display() {
		 System.out.println(toString2());
	 }
	public String toString1() {
		return String.format("%-5s %-15s %-15s",id,name,price);
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
			FileReader fr = new FileReader("softDrink.txt");
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
				int day = Integer.parseInt(txt[3]);
				int month = Integer.parseInt(txt[4]);
				int year = Integer.parseInt(txt[5]);
				expiry ex = new expiry(day,month,year);
				String nameSup = txt[6];
				String addressSup = txt[7];
				supplier sup = new supplier(nameSup,addressSup);
				list_SD[len_sd] = new softDrink(id,name,price,ex,sup);
				len_sd++;
				
			}
			System.out.println("Cập nhật liệu thành công !"); 
		} 
		catch (Exception e) {
	        System.err.println("Không thể lấy dữ liệu 1"); 
	    }
	}
}
