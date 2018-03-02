package extension.tests;

public class ExstensionCastTest {

	public static void main(String[] args) {
		A a = new A();
		B b = (B) a;
		System.out.println(b);
	}

}

class A {
}

class B extends A {
}
