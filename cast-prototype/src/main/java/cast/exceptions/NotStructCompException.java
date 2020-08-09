package cast.exceptions;

public class NotStructCompException extends RuntimeException {

	private Class<? extends Object> a;
	private Class<? extends Object> b;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3159178894347259889L;

	public NotStructCompException(Class<? extends Object> a,Class<? extends Object> b){
		this.setA(a);
		this.setB(b);
	}

	public Class<? extends Object> getA() {
		return a;
	}

	public void setA(Class<? extends Object> a) {
		this.a = a;
	}

	public Class<? extends Object> getB() {
		return b;
	}

	public void setB(Class<? extends Object> b) {
		this.b = b;
	}
}
