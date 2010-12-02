package shz.mock_comparison.domain;

public class Product {

	private double _price;
	private String _description;
	private String _id;

	public Product(String id, String description, double price) {
		_id = id;
		_description = description;
		_price = price;
	}

	public Product(String id) {
		_id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_description == null) ? 0 : _description.hashCode());
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		if (_description == null) {
			if (other._description != null) {
				return false;
			}
		} else if (!_description.equals(other._description)) {
			return false;
		}
		if (_id == null) {
			if (other._id != null) {
				return false;
			}
		} else if (!_id.equals(other._id)) {
			return false;
		}
		if (Double.doubleToLongBits(_price) != Double
				.doubleToLongBits(other._price)) {
			return false;
		}
		return true;
	}

}
