package CF;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class ManagerEmployee implements Function {
	public static Scanner sc = new Scanner(System.in);
	static Employee emp[] = new Employee[50];
	static int len_emp ;
	
	public void ListAllEmployee() {
		Fulltime ft = new Fulltime();
		Parttime pt = new Parttime();
		
		ft.ReadList();
		pt.ReadList();
		
		len_emp = Fulltime.lenFT+ Parttime.lenPT;
		System.arraycopy(Parttime.PT, 0, emp, 0 ,Parttime.lenPT);
		System.arraycopy(Fulltime.FT, 0, emp, Fulltime.lenFT, Fulltime.lenFT);		
	}
	public static void DisplayListEmployee() {
		System.out.println("---------------------------------------------------------------DANH SÁCH NHÂN VIÊN-------------------------------------------------------\n");
		System.err.printf("%-7s %-25s %-20s %-25s %-20s %-25s %-5s\n","Mã NV","Họ Và Tên","Tuổi","Địa chỉ","Số điện thoại","Mail","Ca làm việc\n");
		App.wait(500);
		for(int i = 0;i<len_emp;i++)
        	emp[i].Display();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
	}
	@Override
	public void add() {
		len_emp++;
		System.out.println("1.Thêm nhân viên fulltime: ");
		System.out.println("2.Thêm nhân viên parttime: ");
		App.wait(500);
		System.out.print("Nhập lựa chọn muốn thêm: ");
		int choose = Integer.parseInt(sc.nextLine());
		if(choose ==1) {
			Fulltime ft = new Fulltime();
			ft.Input();
			emp[len_emp-1] = ft;
			System.err.println("Đã thêm thành công !");
		}
		if(choose ==2) {
			Parttime pt = new Parttime();
			pt.Input();
			emp[len_emp-1] = pt;
			System.err.println("Đã thêm thành công !");
		}
	}
	@Override
	public void delete() {
		boolean check = false;
		String info;
		System.out.print("Nhập mã nhân viên cần xóa: ");
		info = sc.nextLine();
		int location = 0; // tim vi tri xoa
		for (int i = 0; i < len_emp; i++) {
			if (emp[i].getId().equalsIgnoreCase(info) || emp[i].getName().equalsIgnoreCase(info)) {
				check = true;
				location = i;
				for ( i = location; i < len_emp - 1; i++) {
					emp[i] = emp[i + 1];
				}
				len_emp--; // cap nhat lai danh sach
				System.err.println("Đã xóa thành công !");
				break;
			}
		}
		if(check == false)
			System.err.println("Nhân viên không tồn tại !");
	}
	@Override
	public void update() {
		boolean check = false;
		System.out.print("Nhập thông tin nhân viên cần sửa: ");
		String info = sc.nextLine();
		System.out.print("Nhập tên mới: ");
		String name_new = sc.nextLine();
		for (int i = 0; i < len_emp; i++) {
			if (emp[i].getId().equalsIgnoreCase(info) || emp[i].getName().equalsIgnoreCase(info)) {
				check = true;
				emp[i].name = name_new;
				System.err.println("đã sữa thành công !");
			}
		}
		if(check == false) {
			System.err.println("Nhân viên không tồn tại !");
		}			
	}
	@Override
	public void showMenu() {
		System.out.println("1.Xuất danh sách nhân viên");
		System.out.println("2.Thêm Nhân Viên");
		System.out.println("3.Xóa Nhân Viên");
		System.out.println("4.Sửa Tên Nhân Viên");
		System.out.println("5.Tìm kiếm nhân viên");
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
					 DisplayListEmployee();					 
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
	public void find() {
		boolean check = false;
		System.out.print("Nhập thông tin nhân viên cần tìm: ");
		String info = sc.nextLine();
		for (int i = 0; i < len_emp; i++) {
			if (emp[i].getId().equalsIgnoreCase(info) || emp[i].getName().contains(info)
					||emp[i].getAddress().contains(info) || String.valueOf(emp[i].getYear()).equalsIgnoreCase(info) ) {
				emp[i].Display();
				check = true;
			}
		}
		if(check == false) {
		System.err.println(" Không tìm thấy nhân viên !");
		}
	}

	public static void WriteToFile() {
		try {
			FileWriter fw1 = new FileWriter("EmployeeFulltime.txt");
			BufferedWriter bw1 = new BufferedWriter(fw1);
			FileWriter fw2 = new FileWriter("EmployeeParttime.txt");
			BufferedWriter bw2 = new BufferedWriter(fw2);

			for (int i = 0; i < len_emp; i++) {
				if (emp[i].id.contains("FEMP")) {
					bw1.write(emp[i].toString());
					bw1.newLine();
				}
				if (emp[i].id.contains("PEMP")) {
					bw2.write(emp[i].toString());
					bw2.newLine();
				}

			}

			System.err.println("Cập nhật dữ liệu thành công !");
			bw1.close();
			bw2.close();
			fw1.close();
			fw2.close();
		} catch (Exception e) {
			System.err.println("Không thể Cập nhật dữ liệu !");
		}
	}
}
