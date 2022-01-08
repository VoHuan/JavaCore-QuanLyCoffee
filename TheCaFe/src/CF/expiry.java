package CF;

import java.util.Scanner;

public class expiry {
	public Scanner sc = new Scanner(System.in);
	private int year;
	protected int month;
	protected int day;
	
	public expiry() {
		
	}

	public expiry(int day, int month, int year) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		while(true) {			
			if(year > 2021 ) 
				break;
				System.err.print("Nhap sai, nhap lai:");
				year=Integer.parseInt(sc.nextLine());
			this.year = year;
		}		
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		while(true) {			
			if(month > 0 && month < 13  ) 
				break;
				System.err.print("Nhap sai, nhap lai:");
				month=Integer.parseInt(sc.nextLine());
			this.month = month;
		}		
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		while(true) {			
			if(day > 0 && day < 32  ) 
				break;
				System.err.print("Nhap sai, nhap lai:");
				day=Integer.parseInt(sc.nextLine());
			this.day = day;
		}		
	}

	void Input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhập vào ngày: ");
		setDay(day = Digital());
		System.out.print("Nhập vào tháng:" );
		setMonth(month = Digital());
		System.out.print("Nhập vào năm: ");
		setYear(year = Digital());
	}
	@Override
	public String toString() {
		return  day + "/" + month+ "/" + year  ;
	}
	public String ToFile() {
		return  day + "," + month+ "," + year  ;
	}
	void Display() {
		System.out.println(toString());
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
}
