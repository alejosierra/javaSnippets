package cast;

import java.lang.reflect.Field;
import cast.exceptions.NotStructCompException;

public class CastHelper {
	
	/***
	 * Convierte el objeto o a un nuevo objeto de la clase c
	 * @param o objeto a convertir
	 * @param c nueva clase
	 * @return nuevo objeto de la clase c con los atributos de la 
	 * @throws ClassCastException
	 * @throws NotStructCompException si las clases no son compatibles estructuralmente
	 */
	public static Object cast(Object o,Class<? extends Object> c) throws ClassCastException,NotStructCompException{
		Class<? extends Object> original=o.getClass();
		try {
			Object res=c.getConstructor().newInstance();
			for (Field f:c.getDeclaredFields()){
				Field oF=original.getDeclaredField(f.getName());
				if (f.getType().equals(oF.getType())){
					f.setAccessible(true);
					oF.setAccessible(true);
					f.set(res, oF.get(o));
				}
			}
			return res;
		}  catch (NoSuchFieldException e) {
			throw new NotStructCompException(original, c); 
		} catch (Exception e) {
			ClassCastException cce=new ClassCastException("Excepción tratando de convertir de "+original.getCanonicalName()+ " a "+c.getCanonicalName());
			cce.setStackTrace(e.getStackTrace());
			throw cce;
		}
	}
}
