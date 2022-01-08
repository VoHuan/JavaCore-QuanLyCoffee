package CF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OderDetail extends Oder {
	
	static Calendar calendar = Calendar.getInstance();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static int hour = calendar.get(Calendar.HOUR_OF_DAY);
	static int minute = calendar.get(Calendar.MINUTE);
	static int second = calendar.get(Calendar.SECOND);
	static int month = calendar.get(Calendar.MONTH);
	static int year = calendar.get(Calendar.YEAR);
	static float t =0;
	static int ptm ; //Lây % khuyến mãi
	static final int tax = 5; //Thuế cố định
	
	public  void Bill() {
		if(text.equals("A"))
			ptm =20;
		if(text.equals("B"))
			ptm =10;
		if(text.equals("C"))
			ptm =5;
		
		float pricePromotion =( total*ptm/100)*-1;           // Tính tiền khuyến mãi
		float priceTax = total*tax/100;                 // Tính tiền thuế
		float detailTotal = total + priceTax + pricePromotion; // Tính tổng tiền cuối cùng
		
		System.out.println("-------------HÓA ĐƠN BÁN HÀNG---------------------");
		for(int i =0;i<ManagerCustomer.len_cus;i++) {
			if(ManagerCustomer.cus[i].getId().compareTo(id_cus) == 0)
				ManagerCustomer.cus[i].Display1();
		}
		System.out.println("--------------------------------------------------");
		System.out.printf("%-5s %-15s %-10s %-15s\n","Mã","Tên","Giá","Số lượng");
		for(int i=0;i<s;i++) {
			pr_sell[i].Display1();
			System.out.println(Quan[i]);
			System.out.println("\n");
		}
		System.out.println("--------------------------------------------------");
		System.out.printf("%-5s %-1s %-5s %-5s \n","Khuyến mãi:",ptm,"%",pricePromotion);
		System.out.printf("%-11s %-1s %-7s %-5s\n","Thuế:",tax,"%",priceTax);
		System.out.println("--------------------------------------------------");
		System.out.printf("%-21s %-10s %-5s\n","Tổng:",detailTotal,"VND");
		System.out.println("--------------------------------------------------");
		for(int i = 0;i<ManagerEmployee.len_emp;i++) {
			if(ManagerEmployee.emp[i].getId().compareTo(Login.user) == 0)
				ManagerEmployee.emp[i].Display1();
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Time: "+hour+"h:"+minute+"m:"+second+"s"+"        "+ sdf.format(calendar.getTime()));
		System.out.println("\n\n");
		
		String index1 = String.valueOf(detailTotal);
		String index2 = String.valueOf(month+1);
		String index3 = String.valueOf(year);
		try {
			FileWriter fw = new FileWriter("Bill.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);       
           	bw.write(index1);
        	bw.write(",");
        	bw.write(index2);
        	bw.write(",");
        	bw.write(index3);
            bw.newLine();                      
            bw.close();
            fw.close();
        } catch (Exception e) {        
        }
	}
	public static void TotalOfMonth() {
		t=0;
		System.out.print("Nhập tháng cần thống kê: ");
		int m = Integer.parseInt(sc.nextLine());
		System.out.print("Nhập năm cần thống kê: ");
		int y = Integer.parseInt(sc.nextLine());
		while(true) {			
			if(m > 0 && m < 13) 
				break;
			System.err.print("Nhập sai, nhập lại: ");
			m=Integer.parseInt(sc.nextLine());
		}
		while(true) {			
			if(y > 0 && m <= year) 
				break;
			System.err.print("Nhập sai, nhập lại: ");
			m=Integer.parseInt(sc.nextLine());
		}
		try {
			FileReader fr = new FileReader("Bill.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while(true) {
				line = br.readLine();
				if(line == null)
					break;
				String txt[] = line.split(",");			
				float detailtotal_file = Float.parseFloat(txt[0]);
				int month_file = Integer.parseInt(txt[1]);
				int year_file = Integer.parseInt(txt[2]);
				if(month_file == m && year_file == y) {
					t +=  detailtotal_file;
				}
			}
			fr.close();
			br.close();
		}
		catch (Exception e) {
	        System.err.println("Không thể thống kê"); 
	    }
		System.out.println("\n\nTổng Thu Nhập Tháng "+m+"/"+y+": "+t+" VND\n\n");
	}
}
