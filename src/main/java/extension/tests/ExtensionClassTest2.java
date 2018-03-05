package extension.tests;

public class ExtensionClassTest2 {

	public static void main(String[] args) {
		Parent p = new Parent();
		D1 d1 = new D1();
		@SuppressWarnings("unused")
		D2 d2 = new D2();
		p = d1;
		System.out.println(p);
	}

}

class Parent {
}

class D1 extends Parent {
}

class D2 extends Parent {
}
