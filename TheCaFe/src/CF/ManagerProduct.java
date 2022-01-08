package CF;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class ManagerProduct implements Function   {
	static product [] pr = new product [100];
	static int len_pr;
	static product pr_sell[] = new product[20]; //danh sách sp đã mua
	public static Scanner sc = new Scanner(System.in);
	//static int sd = 0, c =0, hm=0,n=0,k=0;
	
	
	public void ListAllProduct() {
		softDrink sd = new softDrink();
		cake c= new cake();
		homeMake hm = new homeMake();
		
		sd.ReadList();
		c.ReadList();
		hm.ReadList();
		
		len_pr = softDrink.len_sd + cake.len_ca + homeMake.len_hm;
		System.arraycopy(softDrink.list_SD, 0, pr, 0, softDrink.len_sd);
		System.arraycopy(homeMake.list_HM, 0, pr,softDrink.len_sd ,homeMake.len_hm);
		System.arraycopy(cake.list_Cake, 0, pr,softDrink.len_sd + homeMake.len_hm ,cake.len_ca);
		
	}
	public static void DisplayListProduct() {
		System.out.println("----------------------------------Danh sách sản phẩm----------------------------------");
		System.err.printf("%-5s %-15s %-15s %-35s %-25s\n","Mã","Tên","Giá","Nhà cung cấp","Ngày hết hạn");
		App.wait(500);
		for(int i = 0;i<len_pr;i++)
        	pr[i].Display();
        System.out.println("----------------------------------------------------------------------------------------");
	}
	@Override
	public void showMenu() {
		System.out.println("1.Xuất danh sách sản phẩm");
		System.out.println("2.Thêm sản phẩm");
		System.out.println("3.Xóa sản phẩm");
		System.out.println("4.Sửa giá sản phẩm");
		System.out.println("5.Tìm kiếm sản phẩm");
		System.out.println("6.Sắp xếp sản phẩm theo giá tiền từ thấp đến cao");
		System.out.println("7.Cập nhật dữ liệu");
		System.out.println("8.Thoát");
		App.wait(500);
		System.err.print("Nhập vào lựa chọn: ");
	}

	public void Manager() {
		int choose;
		do {
			App.wait(50);
			showMenu();
			choose = Integer.parseInt(sc.nextLine());
			switch (choose) { 
				case 1:
					DisplayListProduct();
					break;
				case 2:
					add();					
					break;
				case 3:
					delete();
					App.wait(100);
					break;
				case 4:
					update();
					App.wait(100);
					break;
				case 5:
					find();
					break;
				case 6:
					Sort();
					break;
				case 7:
					WriteToFile();
					break;
				case 8:
					System.out.print("\n");
					break;
				default:
				    System.err.println("Lựa chọn không chính xác, vui lòng nhập lại: \n");
			}
		}while(choose != 8);
	}
	@Override
	public void add() {
		len_pr++;
		System.out.println("1.Thêm loại đồ uống đống lon, chai");
		System.out.println("2.Thêm loại đồ uống pha chế");
		System.out.println("3.Thêm đồ tráng miệng");
		App.wait(500);
		System.out.print("Nhập loại sản phẩm muốn thêm: ");
		int choose = Integer.parseInt(sc.nextLine());
		if(choose ==1) {
			softDrink pro = new softDrink();
			pro.Input();
			pr[len_pr-1] = pro;
			System.err.println("Đã thêm thành công !");
		}
		if(choose ==2) {
			homeMake pro = new homeMake();
			pr[len_pr-1] = pro;
			System.err.println("Đã thêm thành công !");
		}
		if(choose ==3) {
			cake pro = new cake();
			pro.Input();
			pr[len_pr-1] = pro;
			System.err.println("Đã thêm thành công !");
		}
		
	}
	@Override
	public void delete() {
		boolean check = false;
		String info;
		System.out.print("Nhập thông tin sản phẩm cần xóa: ");
		info = sc.nextLine();
		int location = 0; // tim vi tri xoa
		for (int i = 0; i < len_pr; i++) {
			if (pr[i].getId().equalsIgnoreCase(info) || pr[i].getName().equalsIgnoreCase(info)) {
				check = true;
				location = i;
				for ( i = location; i < len_pr - 1; i++) {
					pr[i] = pr[i + 1];
				}
				len_pr--; // cap nhat lai danh sach
				System.err.println("Đã xóa thành công !");
			}
		}
		if(check == false) {
			System.err.println("Sản phẩm không tồn tại !");
		}
		
	}
	@Override
	public void update() {
		System.out.print("Nhập thông tin sản phẩm cần sửa: ");
		String info = sc.nextLine();
		System.out.println("Nhập giá mới:");
		int price_new = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < len_pr; i++) {
			if (pr[i].getId().equalsIgnoreCase(info) || pr[i].getName().equalsIgnoreCase(info)) {
				pr[i].price = price_new;
				System.err.println("Đã sữa thành công !");
			}
			if(i == len_pr) {
				System.err.println("Sản phẩm không tồn tại !");
				App.wait(100);
			}
		}
		
	}
	@Override
	public void find() {
		boolean check = false;
		System.out.print("Nhập thông tin sản phẩm cần tìm: ");
		String info = sc.nextLine();
		for (int i = 0; i < len_pr; i++) {
			if (pr[i].getId().equalsIgnoreCase(info) || pr[i].getName().contains(info) 
					|| String.valueOf(pr[i].getPrice()).equalsIgnoreCase(info) ) {				
				pr[i].Display();
				check = true;
			}
		}
		if(check == false) {
			System.err.println("Không tìm thấy sản phẩm!");
		}
	}

	public void Sort() {
		product p = new cake();
		for (int i = 0; i < len_pr; i++) {
			for (int j = i + 1; j < len_pr; j++) {
				if (pr[i].price > pr[j].price) {
					p = pr[i];
					pr[i] = pr[j];
					pr[j] = p;
				}
			}
		}
	}
	public static void WriteToFile() {
		try {
            FileWriter fw1 = new FileWriter("softDrink.txt");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            FileWriter fw2 = new FileWriter("homeMake.txt");
            BufferedWriter bw2 = new BufferedWriter(fw2);
            FileWriter fw3 = new FileWriter("cake.txt");
            BufferedWriter bw3 = new BufferedWriter(fw3);
            for(int i=0;i<len_pr;i++) {
            	if(pr[i].id.contains("SD")) {
            		bw1.write(pr[i].toString());
            		bw1.newLine();
            	}
            	if(pr[i].id.contains("FRU")||pr[i].id.contains("TEA")||pr[i].id.contains("CFE")) {
            		bw2.write(pr[i].toString());
            		bw2.newLine();
            	}
            	if(pr[i].id.contains("CA")) {
            		bw3.write(pr[i].toString());
            		bw3.newLine();
            	}
            }
         
            System.err.println("Cập nhật dữ liệu thành công !");
            bw1.close();
            bw2.close();
            bw3.close();
            fw1.close();
            fw2.close();
            fw3.close();
        } catch (Exception e) {
            System.err.println("Không thể ghi file Cập nhật dữ liệu !");
        }
	}
}
