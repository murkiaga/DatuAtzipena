public class ZatiZeroSalbuespena {

	public int divide(int a, int b) {
		return a / b;
	}
	
	public static void main(String[] args) {
		int a, b;
		a = 5; b = 2;
		try {
			System.out.println(a + "/" + b + "=" + a / b);
		} catch (ArithmeticException e) {
			System.err.println("Error al dividir: " + a + "/" + b);
		}
		try {
			b = 0;
			System.out.println(a + "/" + b + "=" + a / b);
		} catch (ArithmeticException e) {
			System.err.println("Error al dividir: " + a + "/" + b);
		}
		try {
			b = 3;
			System.out.println(a + "/" + b + "=" + a / b);
		} catch (ArithmeticException e) {
			System.err.println("Error al dividir: " + a + "/" + b);
		}
	}
}