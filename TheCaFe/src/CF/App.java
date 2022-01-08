package CF;

import java.util.Scanner;

public class App {
	static ManagerProduct MP = new ManagerProduct();
	static ManagerCustomer MC = new ManagerCustomer();
	static ManagerEmployee ME = new ManagerEmployee();
	static Oder o = new Oder();
	static OderDetail od = new OderDetail();
	public static void wait(int ms){
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
	public static void menuEmployee() {
		System.out.printf("%-18s%-20s","\t","Chức Năng Nhân Viên\n");
		System.out.printf("%-15s%-15s","\t","---------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 1.Chọn hàng\t\t|\n");
		System.out.printf("%-15s%-15s","\t","---------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 2.Chỉnh sửa chọn hàng\t|\n");
		System.out.printf("%-15s%-15s","\t","---------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 3.Xuất Bill\t\t|\n");
		System.out.printf("%-15s%-15s","\t","---------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 4.Trở về Menu chính\t|\n");
		System.out.printf("%-15s%-15s","\t","---------------------------\n");
	}
	public static void menuManager() {
		System.out.printf("%-18s%-20s","\t","Chức Năng Cho Quản Lý\n");
		System.out.printf("%-15s%-15s","\t","-----------------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 1.Quản lý sản phẩm\t\t|\n");
		System.out.printf("%-15s%-15s","\t","-----------------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 2.Quản lý khách hàng\t\t|\n");		
		System.out.printf("%-15s%-15s","\t","-----------------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 3.Quản lý nhân viên\t\t|\n");		
		System.out.printf("%-15s%-15s","\t","-----------------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 4.Thống kê thu nhập theo tháng\t|\n");
		System.out.printf("%-15s%-15s","\t","-----------------------------------\n");
		System.out.printf("%-15s%-15s","\t","|\t 5.Trở về Menu chính\t\t|\n");
		System.out.printf("%-15s%-15s","\t","-----------------------------------\n");
	}
	public static void main(String[] args) {
		MP.ListAllProduct();
		ME.ListAllEmployee();
		MC.ReadListCustomer();
		int choose2, choose1,choose3;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n");
			System.out.printf("%-15s%-15s","\t","---------------------------------------------------------------------------\n");
			System.out.printf("%-15s%-15s","\t","|\t\t\t\t\t MENU\t\t\t\t\t|\n");
			System.out.printf("%-15s%-15s","\t","---------------------------------------------------------------------------\n");
			System.out.printf("%-15s%-15s","\t","|\t 1:Bán Hàng\t|\t 2:Quản Lý\t|\t3:Thoát chương trình\t|\n");
			System.out.printf("%-15s%-15s","\t","---------------------------------------------------------------------------\n");
			App.wait(300);
			System.err.print("\t\t\tMời nhập vào lựa chọn: ");
			choose1 = Integer.parseInt(sc.nextLine());
			switch(choose1) {				
				case 1:
					System.out.println("Đăng nhập thông tin nhân viên ");
					Login.loginEmployee();
					do {
						menuEmployee();						
						App.wait(500);
						System.err.print("Mời nhập vào lựa chọn: ");
						choose2 = Integer.parseInt(sc.nextLine());
						switch (choose2) {
							case 1:
								o.Sell();
								break;
							case 2:
								if(o.pr_sell[0] == null) {
									System.err.println("Chưa có đơn hàng, hãy mua hàng !!!");
									break;
								}
								System.out.println("1.Thêm đồ uống");
								System.out.println("2.Xóa đồ uống");
								System.out.print("Nhập vào lựa chọn: ");
								int k = Integer.parseInt(sc.nextLine());
								if(k == 1)
									o.addOder();
								else
									o.deleteOder();
								break;
							case 3:
								if(o.pr_sell[0] == null) {
									System.err.println("Chưa có đơn hàng, hãy mua hàng !!!");
									break;
								}
								od.Bill();
								break;						
							case 4:
								System.out.println("\n");
								break;
							default:
							    System.err.println("Lựa chọn không chính xác, vui lòng nhập lại: \n");
							    break;
						}
					}while(choose2 != 4);
					break;
				case 2:
					Login.loginManager();
					App.wait(200);
					do {
						menuManager();					
						App.wait(500);
						System.err.print("Mời nhập vào lựa chọn: ");
						choose3 = Integer.parseInt(sc.nextLine());
						switch (choose3) {
							case 1:
								MP.Manager();
								break;
							case 2:
								MC.Manager();
								break;
							case 3:
								ME.Manager();
								break;
							case 4:
								OderDetail.TotalOfMonth();
								break;
							case 5:
								System.out.println("\n");
								break;
							default:
							    System.err.println("Lựa chọn không chính xác, vui lòng nhập lại: \n");
							    break;
						}
					}while(choose3 != 5);
					break;
				case 3:
					System.err.println("Bạn đã thoát chương trình!!!");
					System.err.println("Cám ơn bạn đã sử dụng dịch vụ !!!");
					break;
				default:
				    System.err.println("Lựa chọn không chính xác, vui lòng nhập lại: \n");
				    break;
			}
		}while(choose1 != 3);
	}
}
