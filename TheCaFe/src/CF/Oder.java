package CF;

import java.util.Scanner;

public class Oder   {
	public static Scanner sc = new Scanner(System.in);
	protected static product pr_sell[] = new product[20]; //danh sách sp đã mua
	protected static int Quan[] = new int[20];
	protected static int total;
	protected static int s,k;
	protected static int quanity;
	protected static String id_cus,text;
	
	static void Sell() {
		boolean check = false;
		total = 0;
		quanity = 0 ;
		// Lấy rank từ mã khách hàng
		do
		{	
			System.out.print("Nhập mã khách hàng: ");
			id_cus = sc.nextLine();
			for(int i =0;i<ManagerCustomer.len_cus;i++) {
				if(ManagerCustomer.cus[i].getId().compareTo(id_cus) == 0) {
					text = ManagerCustomer.cus[i].rank;
					check = true;
					break;
				}
			}
			if(check==false) {
				System.err.println("Sai thông tin mã khách hàng ! ");
				App.wait(100);
			}
		}while(check == false);
		
		System.out.print("Nhập số lượng mặt hàng muốn mua: ");
		s = Integer.parseInt(sc.nextLine());
		String text;
		String[] info = new String[10] ;
		//Lưu lại thông tin mã sản phẩm và số lượng khi nhập
		for(int i=0;i<s;i++) {
			System.out.print("Nhập mã đồ uống hoặc tên đồ uống muốn mua: ");
			text=sc.nextLine();
			 while(!checkID(text)) {
					System.err.println("Sản phẩm không tồn tại !");
					System.out.print("Nhập lai mã sản phẩm: ");
					text = sc.nextLine();
				}
			System.out.print("Nhập số lượng : ");
			quanity = Integer.parseInt(sc.nextLine());
			while(true) {			
				if(quanity > 0) 
					break;
					System.err.print("Nhập sai, nhập lại: ");
					quanity=Integer.parseInt(sc.nextLine());
			}		
			Quan[i] = quanity;
			info[i] = text;
		}
		//Lấy thông tin sản phẩm từ mã sp nhập vào
		for(int i=0;i<s;i++) {
			for(int j=0;j<ManagerProduct.len_pr;j++) {
				if(ManagerProduct.pr[j].getId().equalsIgnoreCase(info[i])||ManagerProduct.pr[j].getName().equalsIgnoreCase(info[i])) {
					pr_sell[i] = ManagerProduct.pr[j];
				}
			}
		}
		//Tính tổng tiền từng sản với số lượng
		for(int i=0;i<s;i++) {
			total += pr_sell[i].price*Quan[i];
		}
		//Sản phẩm trùng thì cộng dồn
		for(int i=0;i<s-1;i++) {
			for(int j=i+1;j<s;j++) {
				if(pr_sell[i].id.equals(pr_sell[j].id)) {
					Quan[i] = Quan[i]+Quan[j];
					Quan[j] = Quan[j+1];
					pr_sell[j] = pr_sell[j+1];
					s--;
					j--;
				}					
			}
		}
		//Xuất ra màn hình
		System.out.println("\n\n");
		System.out.println("-------------Sản phẩm đã chọn--------------");
		System.out.printf("%-5s %-15s %-10s %-15s\n","Mã","Tên","Giá","Số lượng");
		for(int i=0;i<s;i++) {
			pr_sell[i].Display1();
			System.out.println(Quan[i]);
		}
		System.out.println("--------------------------------------------");
		System.out.printf("%-21s %-10s %-5s\n","Tổng:",total,"VND");
		System.out.println("--------------------------------------------");
		System.out.println("\n\n");
	}
	static void addOder() {
		total = 0;
		int sub_Quan[] = new int[10],sub_quanity;
		System.out.print("Nhập số lượng sản phẩm muốn thêm: ");
		int addCount;
		String temp = null;
		String[] info = new String[10] ;
		addCount = Integer.parseInt(sc.nextLine());
		for(int i=0;i<addCount;i++) {
			System.out.print("Nhập mã đồ uống hoặc tên đồ uống muốn mua: ");
			temp = sc.nextLine();
			System.out.print("Nhập số lượng : ");
			sub_quanity = Integer.parseInt(sc.nextLine());
			while(true) {			
				if(quanity > 0) 
					break;
					System.err.print("Nhập sai, nhập lại: ");
					quanity=Integer.parseInt(sc.nextLine());
			}		
			sub_Quan[i] = sub_quanity;
			info[i] = temp;
		}
		for(int i=0;i<addCount;i++) {
			for(int j=0;j<ManagerProduct.len_pr;j++) {
				if(ManagerProduct.pr[j].getId().equalsIgnoreCase(info[i])||ManagerProduct.pr[j].getName().equalsIgnoreCase(info[i])) {
					s++;
					pr_sell[s-1] = ManagerProduct.pr[j];
					Quan[s-1]= sub_Quan[i];
				}
			}
		}
		for(int i=0;i<s;i++) {
			total += pr_sell[i].price*Quan[i];
		}
		// Sản phẩm trùng thì cộng dồn
		for (int i = 0; i < s - 1; i++) {
			for (int j = i + 1; j < s; j++) {
				if (pr_sell[i].id.equals(pr_sell[j].id)) {
					Quan[i] = Quan[i] + Quan[j];
					Quan[j] = Quan[j + 1];
					pr_sell[j] = pr_sell[j + 1];
					s--;
					j--;
				}
			}
		}
		// Xuất ra màn hình
		System.out.println("\n\n");
		System.out.println("-------------Sản phẩm đã chọn--------------");
		System.out.printf("%-5s %-15s %-10s %-15s\n", "Mã", "Tên", "Giá", "Số lượng");
		for (int i = 0; i < s; i++) {
			pr_sell[i].Display1();
			System.out.println(Quan[i]);
		}
		System.out.println("--------------------------------------------");
		System.out.printf("%-21s %-10s %-5s\n", "Tổng:", total, "VND");
		System.out.println("--------------------------------------------");
		System.out.println("\n\n");
	}
	static void deleteOder() {
		total = 0;
		int count = 1;
		System.out.print("Nhập số lượng sản phẩm muốn xóa: ");
		int deleteCount;
		String temp = null;
		String[] info = new String[10] ;
		deleteCount = Integer.parseInt(sc.nextLine());
		for(int i=0;i<deleteCount;i++) {
			System.out.print("Nhập mã đồ uống hoặc tên đồ uống "+count+" : ");
			temp = sc.nextLine();
			info[i] = temp;
			count++;
		}
		for(int i=0;i<deleteCount;i++) {
			for(int j=0;j<s;j++) {
				if(pr_sell[j].getId().equalsIgnoreCase(info[i])||pr_sell[j].getName().equalsIgnoreCase(info[i])) {
					for(int k = j; k<s ;k++) {
						pr_sell[k] = pr_sell[k+1];
						Quan[k]= Quan[k+1];
						//j--;
					}
					s--;
				}
			}
		}
		for(int i=0;i<s;i++) {
			total += pr_sell[i].price*Quan[i];
		}
		// Xuất ra màn hình
		System.out.println("\n\n");
		System.out.println("-------------Sản phẩm đã chọn--------------");
		System.out.printf("%-5s %-15s %-10s %-15s\n", "Mã", "Tên", "Giá", "Số lượng");
		for (int i = 0; i < s; i++) {
			pr_sell[i].Display1();
			System.out.println(Quan[i]);
		}
		System.out.println("--------------------------------------------");
		System.out.printf("%-21s %-10s %-5s\n", "Tổng:", total, "VND");
		System.out.println("--------------------------------------------");
		System.out.println("\n\n");
	}
	public static  boolean checkID(String s) {
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
}
