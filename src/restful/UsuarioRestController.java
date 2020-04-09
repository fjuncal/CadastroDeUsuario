package restful;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dao.UsuarioDAO;
import entidade.Usuario;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioDAO usrDao;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> findById(@PathVariable("id") Long id) {
		Optional<Usuario> usr = Optional.ofNullable(usrDao.buscaPorId(id));
		if (usr.isPresent())
			return new ResponseEntity<Usuario>(usr.get(), HttpStatus.OK);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Usuario usr, UriComponentsBuilder ucBuilder) {

		Usuario novo = usrDao.cria(usr);

		if (novo.getId() != null) {
			System.out.println("Posso inserir");
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(novo.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}

	@GetMapping(value = "/buscaEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> findByEmail(@PathVariable("email") String email) {

		Usuario usr = usrDao.buscaPorEmail(email);

		if (usr != null) {
			return new ResponseEntity<Usuario>(usr, HttpStatus.OK);
		}
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

}
