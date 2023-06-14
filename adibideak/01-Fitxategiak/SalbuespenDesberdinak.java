
public class SalbuespenDesberdinak {

	public static void main(String[] args) {
		//bete array bat zenbakiekin
		int nums[][] = new int[2][3];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				nums[i][j] = i + j;
			}
		}
		
		//arraiko posizio bakoitzerako kalkulu batzuk egin
		//salbuespenak sortuko ditu
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				try {
					System.out.println(String.valueOf(5 * nums[i][j] / j).charAt(1));
				} catch(ArithmeticException e) {
					System.out.println("ERROR: aritmetikoa 5*"+nums[i][j]+"/"+j);
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("ERROR: ez da existitzen nums["+i+"]["+j+"]");
				} catch(Exception e) {
					System.out.println("ERROR: beste mota batekoa");
					System.out.println();
					e.printStackTrace();
				}
			}
		}
		
	}
}