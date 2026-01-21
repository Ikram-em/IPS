package giis.demo.tkrun.suplemento;

public class SuplementoModel {
	private SuplementoDAO dao = new SuplementoDAO();

	public String[] getAbonos() {
		return dao.getAbonos();
	}

	public String[] getPartidosConSuplemento() {
		return dao.getPartidosConSuplemento();
	}

	public boolean pagarSuplemento(int idAbono, int idPartido, double precio) {
		return dao.pagarSuplemento(idAbono, idPartido, precio);
	}
}
