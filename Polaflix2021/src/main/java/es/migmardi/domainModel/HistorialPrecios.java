package es.migmardi.domainModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class HistorialPrecios {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idHistorialPrecios;
	@ElementCollection
	private Map<Calendar, Float> mapHistorialPrecios;

	public HistorialPrecios() {
		mapHistorialPrecios=new HashMap<Calendar, Float>();
	}
	
	public void setPrecioAtTime(Calendar calendar, float precio) {
		mapHistorialPrecios.put(calendar, precio);
	}
	
	public float getPrecioAtTime(Calendar date) {
		return mapHistorialPrecios.get(date);
	}
	
	public ArrayList<Calendar> getDatesFromHistorialPrecios(){
		return new ArrayList<Calendar>(mapHistorialPrecios.keySet());
	}
}
