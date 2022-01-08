package CF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Parttime extends Employee {
	static Employee PT[] = new Employee[50];
	static int lenPT=0;
	public Parttime() {
		super();
	}

	public Parttime(String id, String name, int year, String address, String phone, String mail, String shift) {
		super(id, name, year, address, phone, mail,shift);
	}

	@Override
	public void Input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập mã số nhân viên: ");
		id = sc.nextLine();
		while(checkID(id)) {
			System.err.println("Nhân viên đã tồn tại !");
			System.out.print("Nhập lai mã số nhân viên: ");
			id = sc.nextLine();
		}
		System.out.print("Nhập họ tên nhân viên: ");
		name = sc.nextLine();
		System.out.print("Nhập năm sinh nhân viên: ");
		setYear(year = Digital());
		System.out.print("Nhập địa chỉ nhân viên: ");
		address = sc.nextLine();
		System.out.print("Nhập số điện thoại nhân viên: ");
		setPhone(phone = sc.nextLine());
		System.out.print("Nhập mail nhân viên: ");
		mail = sc.nextLine();
		while(!checkEmail(mail)) {
			System.err.println("Mail Không hợp lệ! Nhập lại");
			mail = sc.nextLine();
		}
		System.out.print("Nhập ca làm việc: ");
		shift = sc.nextLine();
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
	public boolean checkID(String s) {
		try {
			for(int i = 0 ; i < ManagerEmployee.emp.length ; i++) {
				if(ManagerEmployee.emp[i].getId().equals(s)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean checkEmail(String s) {
		return s.matches("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$");
	}
	public String toString() {
		return id+","+name+","+this.getAge()+","+address+","+phone+","+mail+","+shift;
	}
	public String toString2() {
		return String.format("%-7s %-25s %-7s %-40s %-15s %-30s %-5s\n",id,name,this.getAge(),address,phone,mail,shift);
	}
	@Override
	public void Display() {
		System.out.println(toString2());		
	}
	public String toString1() {
		return String.format("%-5s %-7s %-15s %-15s\n","NHÂN VIÊN : ",id,name,phone);
	}
	@Override
	public void Display1() {
		System.out.println(toString1());
	}

	@Override
	public void ReadList() {
		try {
			FileReader fr = new FileReader("EmployeePartTime.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while(true) {
				line = br.readLine();
				if(line == null)
					break;
				String txt[] = line.split(",");
				String id = txt[0];
				String name = txt[1];
				int year = Integer.parseInt(txt[2]);
				String address = txt[3];
				String phone = txt[4];
				String mail = txt[5];
				String shift = txt[6];
				PT[lenPT] = new Parttime(id,name,year,address,phone,mail,shift);				
				lenPT++;
			}
			System.out.println("Cập nhật liệu thành công !"); 
		} 
		catch (Exception e) {
	        System.err.println("Không thể lấy dữ liệu"); 
	    }
	}
	
}
