package br.com.slifesys.patrimonio.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.slifesys.patrimonio.model.Item;
import br.com.slifesys.patrimonio.repository.ItemRepository;

@RestController
//@CrossOrigin("${origem-permitida}")
public class ItemResource {

	@Autowired
	private ItemRepository itemRepository;

	// Consulta
	@GetMapping("/itens")
	//@CrossOrigin(maxAge = 10, origins = {"http://localhost:8000"})
	public List<Item> listar() {
	//public ResponseEntity<?> listar(Pageable pageable) {
		//return new ResponseEntity<> (itemRepository.findAll(pageable),HttpStatus.OK);
		return itemRepository.findAll();
	}

	// Inclusão
	@PostMapping("/itens")
	public Item adicionar(@RequestBody @Valid Item item) {
		return itemRepository.save(item);
	}

	@PostMapping("/itens/criar")
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Item> criar(@Valid @RequestBody Item item, HttpServletResponse response) {
		Item itemSalvo = itemRepository.save(item);
		//publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(itemSalvo);
	}
	
	
	// Exclusão
	@DeleteMapping("/exclusaoItem")
	public ResponseEntity<?> exclusao(@RequestBody Item item) {
		Item i = itemRepository.findOne(item.getId());
		if (i != null) {
			itemRepository.delete(i);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/itens/{id}")
	public  ResponseEntity<?> excluir(@PathVariable Long id) {
		Item item = itemRepository.findOne(id);
		if (item != null) {
		   itemRepository.delete(id);
		   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}	
}
