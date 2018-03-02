package extension.tests;

public class ExtensionConstructorTest {

	public static void main(String[] args) {
		Mercedes m = new Mercedes();
		System.out.println(m.name);
		System.out.println(m.year);
		System.out.println(m.kilometers);
	}

}

class Car {

	String name;
	int year;

	public Car() {
		this.name = " ";
		this.year = 0;
		System.out.println("Car");
	}

} // end of Car class

class Mercedes extends Car {

	int kilometers;

	public Mercedes() {
		this.kilometers = 0;
		System.out.println("Mercedes");
	}

} // end of mercedes class