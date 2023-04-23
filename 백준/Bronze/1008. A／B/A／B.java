import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		double A = sc.nextInt();
		double B = sc.nextInt();
		double result = A/B;
		
		String resultS = String.format("%.9f", result);
		
		System.out.println(resultS);
		
		sc.close();
	}

}