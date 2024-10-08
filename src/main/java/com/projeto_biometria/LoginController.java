package com.projeto_biometria;

public class LoginController {
    private AutenticacaoService autenticacaoService;

    public LoginController() {
        this.autenticacaoService = new AutenticacaoService();
    }

    public boolean login(String email, String senha, String caminhoImagemUsuario) throws Exception {
        // Busca o usuário pelo e-mail
        Usuario usuario = UserDao.getUserByEmail(email);
        if (usuario != null) {
            // Verifica se a senha está correta
            if (usuario.getPassword().equals(senha)) {
                // Chama o método de autenticação facial da AutenticacaoService
                return autenticacaoService.autenticarUsuario(email, caminhoImagemUsuario);
            } else {
                System.out.println("Senha incorreta.");
                return false;
            }
        } else {
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }
}
