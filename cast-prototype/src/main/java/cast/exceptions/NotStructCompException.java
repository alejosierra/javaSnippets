package cast.exceptions;

public class NotStructCompException extends RuntimeException {

	private Class a;
	private Class b;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3159178894347259889L;

	public NotStructCompException(Class a,Class b){
		this.a=a;
		this.b=b;
	}
}
