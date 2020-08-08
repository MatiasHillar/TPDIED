package excepciones;

public class ExcepcionNoExisteElemento extends Exception{

	public ExcepcionNoExisteElemento() {
		super("No existe un elemento con ese identificador");
	}
}
