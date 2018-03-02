package parameters.tests;

public class ObjectArgument {
	public static void main(String[] args) {
		Person p = new Person("ABC");
		p.setName("DFG");
		changePerson(p);
		System.out.println(p.getName());
	}

	private static void changePerson(Person p) {
		p.setName("GHI");
		p = new Person("JKL");
		p.setName("MNO");
		System.out.println(p.getName());
	}

}

class Person {
	private String name;

	public Person(String s) {
		this.name = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}