package CF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Login {
	static Login log  = new Login();
	static int len_log = 0;
	public static  String user;
	public static String password;
	public static Scanner sc = new Scanner(System.in);
	
	public Login() {
		super();
	}

	public Login(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public static void loginEmployee() {
		boolean check = false;
		do{
			App.wait(50);
			System.out.print("*Nhập tài khoản:");
			user = sc.nextLine();
			System.out.print("*Nhập mật khẩu:");
			password = sc.nextLine();
			for (int i = 0; i < ManagerEmployee.len_emp; i++) {
				if (ManagerEmployee.emp[i].id.equals(user) && password.equals("Admin")) {
					System.err.println("Nhân viên đăng nhập thành công ! \n");
					check = true;
					break;
				}
				if(i == ManagerEmployee.len_emp - 1)
					System.err.println("Sai thông tin đăng nhập !");
			}
		}while (check == false);
	}
	
	public static  void loginManager() {
		boolean check = false;
		take();
		do {
			App.wait(200);
			System.out.print("*Nhập tài khoản: ");
			String user1 = sc.nextLine();
			System.out.print("*Nhập mật khẩu: ");
			String password1 = sc.nextLine();
			if(user1.equals(log.user) && password1.equals(log.password)) {
				System.err.println("Quản lý đăng nhập thành công ! \n");
				check = true;
			}
			else
				System.err.println("Sai thông tin đăng nhập !");
		}while(check == false);
	}

	public static void take() {
		try {
			FileReader fr = new FileReader("Login.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while(true) {
				line = br.readLine();
				if(line == null)
					break;
				String txt[] = line.split(",");
				String user = txt[0];
				String password = txt[1];
				Login log = new Login(user,password);
			}
		} 
		catch (Exception e) {         
        }
	}
}
	
