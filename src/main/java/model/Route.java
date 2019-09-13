package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Route {
	private LocalDate dato;
	private LocalTime afgangsTid;
	private LocalTime ankomstTid;
	private String afstangsAdresse;
	private String ankomstAdresse;
	private User driver;
	
	
	
	public Route(LocalDate dato, LocalTime afgangsTid, LocalTime ankomstTid, String afstangsAdresse,
			String ankomstAdresse, User driver) {
		super();
		this.dato = dato;
		this.afgangsTid = afgangsTid;
		this.ankomstTid = ankomstTid;
		this.afstangsAdresse = afstangsAdresse;
		this.ankomstAdresse = ankomstAdresse;
		this.driver = driver;
	}
	public LocalDate getDato() {
		return dato;
	}
	public void setDato(LocalDate dato) {
		this.dato = dato;
	}
	public LocalTime getAfgangsTid() {
		return afgangsTid;
	}
	public void setAfgangsTid(LocalTime afgangsTid) {
		this.afgangsTid = afgangsTid;
	}
	public LocalTime getAnkomstTid() {
		return ankomstTid;
	}
	public void setAnkomstTid(LocalTime ankomstTid) {
		this.ankomstTid = ankomstTid;
	}
	public String getAfstangsAdresse() {
		return afstangsAdresse;
	}
	public void setAfstangsAdresse(String afstangsAdresse) {
		this.afstangsAdresse = afstangsAdresse;
	}
	public String getAnkomstAdresse() {
		return ankomstAdresse;
	}
	public void setAnkomstAdresse(String ankomstAdresse) {
		this.ankomstAdresse = ankomstAdresse;
	}
	public User getDriver() {
		return driver;
	}
	public void setDriver(User driver) {
		this.driver = driver;
	}
	
	
	

}
