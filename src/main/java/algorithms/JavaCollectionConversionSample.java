package algorithms;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

public class JavaCollectionConversionSample {

	private static final double FX_RATE = 1.49;

	public static Collection<Price> convert(Collection<Price> prices) {
		return prices.stream()
				.distinct()
				.map(JavaCollectionConversionSample::toFixedRate)
				.sorted(Comparator.comparingDouble(Price::getPrice))
				.collect(toList());
	}

	public static void main(String[] args) {
		Collection<Price> prices = new ArrayList<Price>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Price(0.34));
				add(new Price(0.35));
				add(new Price(11.23));
				add(new Price(40.67));
				add(new Price(0.34));
				add(new Price(1.34));
				add(new Price(1.31));
				add(new Price(11.23));
			}
		};

		Collection<Price> expected = new LinkedList<Price>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Price(0.5066));
				add(new Price(0.5215));
				add(new Price(1.9519));
				add(new Price(1.9966000000000002));
				add(new Price(16.7327));
				add(new Price(60.5983));
			}
		};

		System.out.println(expected.equals(convert(prices)));
		convert(prices).forEach(System.out::println);
	}

	private static Price toFixedRate(Price p) {
		return new Price(p.getPrice() * FX_RATE);
	}

	private static class Price {
		private final double price;

		public Price(double price) {
			this.price = price;
		}

		public double getPrice() {
			return this.price;
		}

		@Override
		public String toString() {
			return "price=" + price;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(price);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Price other = (Price) obj;
			if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
				return false;
			return true;
		}
	}

}
