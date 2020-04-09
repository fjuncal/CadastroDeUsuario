package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.UsuarioDAO;
import entidade.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuDAO;
	

	public void setUsuDAO(UsuarioDAO dao) {
		usuDAO = dao;
	}

	public UsuarioDAO getUsuDAO() {
		return usuDAO;
	}
	
	

	@RequestMapping(value = "/index")
	public String showForm() {
		return "index";
	}

	@RequestMapping(value = "/autentica", method = RequestMethod.POST)
	public String gravarUsu(@RequestParam String nome, @RequestParam String email, @RequestParam String senha,
			HttpSession session) {

		Usuario usr = new Usuario();

		usr.setEmail(email);
		usr.setNome(nome);
		usr.setSenha(senha);

		usuDAO.cria(usr);

		session.setAttribute("usuario", usr);

		return "usuarioGravado";

	}
	
	@RequestMapping(value = "/logar", method = RequestMethod.POST)
	public String logarUsu() {
		return "logarNaConta";
	}
	
	@RequestMapping(value = "/usuarioLogado", method = RequestMethod.POST)
	public String conecta(@RequestParam String email, @RequestParam String senha, HttpSession session) {
				
		Usuario usr = usuDAO.buscaPorEmail(email);		
		
		
		if (usr.autentica(senha)) {
			session.setAttribute("usuario", usr);
			return "login-sucesso";			
		}	
		
		return "usuario-erro";
	}
	
	@RequestMapping(value = "/atualizarSenha")
	public String formSenha() {
		return "formSenha";
	}
		
	@RequestMapping(value = "/senhaAtualizada")
	public String atualizandoSenha(@RequestParam String email, @RequestParam String senha, HttpSession session) {
		
		Usuario user = usuDAO.buscaPorEmail(email);
		user.setSenha(senha);
		
		usuDAO.atualiza(user);
		return "logarNaConta";
	}
	
	@GetMapping(value = "/deletarConta")
	public String formDeletarConta() {
		return "formDeletar";
	}
	
	@RequestMapping(value = "/usuarioDeletado")
	public String removendoUsuario(@RequestParam String email, @RequestParam String senha) {
		Usuario user = usuDAO.buscaPorEmail(email);
		usuDAO.remove(user);
		return "index";
	}

}
