package giis.demo.tkrun.partidos.precios;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TribunePricesDTO {
	private Map<Integer, SimpleEntry<String, Double>> precioPorSeccion = new HashMap<Integer, SimpleEntry<String, Double>>();
	
	public void setPrecioPorId(int id, String priceDescription, Double price) {
		precioPorSeccion.put(id, new SimpleEntry<>(priceDescription, price));
	}
	
	public Map<Integer, Double> getPriceBySection(List<Integer> sectionIds) {
		Map<Integer, Double> precioSecciones = new HashMap<Integer, Double>();
		for (Integer sectionId : sectionIds) {
			SimpleEntry<String, Double> sectionValue = getPriceById(sectionId);
			if (sectionValue == null) {
				continue;
			}
			precioSecciones.put(sectionId, sectionValue.getValue());
		}
		
		return precioSecciones;
	}
	
	public void setPrecioGeneral(Double precioGeneral, String tipoPrecio) {
		precioPorSeccion.clear();
		precioPorSeccion.put(0, new SimpleEntry<String, Double>(tipoPrecio, precioGeneral));
	}
	
	public void removePrecioGeneral() {
		precioPorSeccion.clear();
		precioPorSeccion.put(0, new SimpleEntry<String, Double>("Tribuna", null));
	}
	
	public Map<String, Double> getSpecificPrices() {
		Map<String, Double> specificPrices = new HashMap<String, Double>();
		for (Map.Entry<Integer, SimpleEntry<String, Double>> precioPorId : precioPorSeccion.entrySet()) {
			if (precioPorId.getValue().getKey() == "Tribuna" || precioPorId.getValue().getKey() == "Partido" || precioPorId.getValue().getValue() == null) {
				continue;
			}
			specificPrices.put(precioPorId.getValue().getKey(), precioPorId.getValue().getValue());
		}
		if (precioPorSeccion.get(0).getValue() != null) {
			specificPrices.put("General " + precioPorSeccion.get(0).getKey(), precioPorSeccion.get(0).getValue());
		}
		
		return specificPrices;
	}
		
	public SimpleEntry<String, Double> getPriceById(int id) {
		SimpleEntry<String, Double> sectionValue = precioPorSeccion.get(id);
		if (sectionValue != null && sectionValue.getValue() != null) {
			return new SimpleEntry<String, Double>("Sección", sectionValue.getValue());
		}
		SimpleEntry<String, Double> tribuneValue = precioPorSeccion.get(0);
		if (tribuneValue != null && tribuneValue.getValue() != null) {
			return new SimpleEntry<String, Double>("Precio general " + tribuneValue.getKey().toLowerCase(), tribuneValue.getValue());
		}
		return null;
	}
}
