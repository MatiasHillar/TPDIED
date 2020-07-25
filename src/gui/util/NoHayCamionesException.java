package gui.util;

public class NoHayCamionesException extends Exception {
	public NoHayCamionesException() {
		super("No hay camiones disponibles para asignar a este envio");
	}
}
