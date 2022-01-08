package CF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class ManagerCustomer implements Function {
	public static Scanner sc = new Scanner(System.in);
	static Customer cus[] = new Customer[50];
    static int len_cus;
	
	public void ReadListCustomer() {
		try {
			FileReader fr = new FileReader("Customer.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while(true) {
				line = br.readLine();
				if(line == null)
					break;
				String txt[] = line.split(",");
				String id = txt[0];
				String rank = txt[1];
				String name = txt[2];
				String phone = txt[3];
				cus[len_cus] = new Customer(id,rank,name,phone);				
				len_cus++;
			}
			System.out.println("Cập nhật liệu thành công !"); 
		} 
		catch (Exception e) {
	        System.err.println("Không thể lấy dữ liệu"); 
	    }
	}
	public static void DisplayCustomer() {
		System.out.println("----------------DANH SÁCH KHÁCH HÀNG-------------------------------\n");
		System.err.printf("%-5s %-5s %-15s %-15s\n","Mã","Hạng","Họ Và Tên","Số điện thoại");
		App.wait(500);
		for(int i =0;i<len_cus;i++)
			cus[i].Display();
        System.out.println("-------------------------------------------------------------------");
	}

	@Override
	public void showMenu() {
		System.out.println("1.Xuất danh sách khách hàng");
		System.out.println("2.Thêm khách hàng");
		System.out.println("3.Xóa khách hàng");
		System.out.println("4.Sửa độ thân thiết khách hàng");
		System.out.println("5.Tìm kiếm khách hàng");
		System.out.println("6.Cập nhật dữ liệu");
		System.out.println("7.Thoát");
		App.wait(500);
		System.err.print("Nhập vào lựa chọn: ");
	}
	public void Manager() {
		int choose;
		do {
			showMenu();
			choose = Integer.parseInt(sc.nextLine());
			switch (choose) { 
				case 1:
					DisplayCustomer();
					break;
				case 2:
					add();
					break;
				case 3:
					delete();
					break;
				case 4:
					update();
					break;
				case 5:
					find();
					break;
				case 6:
					WriteToFile();
					break;
				case 7:
					System.out.print("\n");
					break;
				default:
				    System.err.println("Lựa chọn không chính xác, vui lòng nhập lại: \n");
			}
		}while(choose != 7);
	}
	@Override
	public void add() {
		len_cus++;
		Customer ctm = new Customer();
		ctm.Input();
		cus[len_cus-1] = ctm;
		System.err.println("Đã thêm thành công !");
	}
	@Override
	public void delete() {
		boolean check = false;
		String info;
		System.out.print("Nhập mã khách hàng cần xóa: ");
		info = sc.nextLine();
		int location = 0; // tim vi tri xoa
		for (int i = 0; i < len_cus; i++) {
			if (cus[i].getId().equalsIgnoreCase(info) || cus[i].getName().equalsIgnoreCase(info)) {
				check = true;
				location = i;
				for ( i = location; i < len_cus - 1; i++) {
					cus[i] = cus[i + 1];
				}
				len_cus--; // cap nhat lai danh sach
				System.err.println("Đã xóa thành công !");
			}
		}
		if(check  == false)
			System.err.println("Khách hàng không tồn tại !");
	}
	@Override
	public void update() {
		boolean check = false;
		System.out.print("Nhập mã khách hàng cần sửa: ");
		String info = sc.nextLine();
		System.out.print("Nhập loại khách hàng mới: ");
		String rank_new = sc.nextLine();
		for (int i = 0; i < len_cus; i++) {
			if (cus[i].getId().equalsIgnoreCase(info) || cus[i].getName().equalsIgnoreCase(info)) {
				check = true;
				cus[i].rank = rank_new.toUpperCase();
				System.err.println("Đã sửa thành công !");
			}
		}
		if( check == false)
			System.err.println("Khách hàng không tồn tại !");
	}
	@Override
	public void find() {
		boolean check = false;
		System.out.print("Nhập thông tin khách hàng cần tìm: ");
		String info = sc.nextLine();
		for (int i = 0; i < len_cus; i++) {
			if (cus[i].getId().equalsIgnoreCase(info) || cus[i].getName().contains(info)
					||cus[i].getRank().equalsIgnoreCase(info) || cus[i].getPhone().contains(info)) {
				cus[i].Display();
				check = true;
			}
		}
		if(check == false) 
			System.err.println(" Không tìm thấy nhân viên !");
	}
	public static void  WriteToFile() {
		try {
            FileWriter fw = new FileWriter("Customer.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0;i<len_cus;i++) {
           	bw.write(cus[i].toString2());
            	bw.newLine();
            }
         
            System.err.println("Cập nhật dữ liệu thành công !");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println("Không thể cập nhật dữ liệu !");
        }
	}
}
