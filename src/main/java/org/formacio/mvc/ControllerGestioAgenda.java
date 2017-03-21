package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author migekun
 */
@Controller
public class ControllerGestioAgenda {

	@Autowired
	private AgendaService agendaService;
	
	@RequestMapping(path="/nombre", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public int obteNombreContactes() {
		return agendaService.nombreContactes();
	}
	
	@RequestMapping(path="/telefon", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String obteTelefon(@RequestParam(value="id") String id) {
		return agendaService.recupera(id).getTelefon();
	}

	@RequestMapping(path="/contacte/{id}", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<Persona> obteContacte(@PathVariable(value="id") String id) {
		Persona contacte = agendaService.recupera(id); 
		return new ResponseEntity<>(contacte, contacte != null ? HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path="/afegir", method=RequestMethod.POST)
	@ResponseBody
	public void afegirContacte(@RequestParam String id, @RequestParam String nom, @RequestParam String telefon) {
		agendaService.inserta(id, nom, telefon);
	}
	
}
