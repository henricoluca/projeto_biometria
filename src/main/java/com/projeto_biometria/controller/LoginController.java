package com.projeto_biometria.controller;

import com.projeto_biometria.service.AutenticacaoService;
import com.projeto_biometria.model.Usuario;

public class LoginController {
    private static AutenticacaoService autenticacaoService;

    public LoginController() {
        this.autenticacaoService = new AutenticacaoService();
    }

    public static boolean login(String email, String caminhoImagemUsuario) throws Exception {
        // Busca o usuário pelo e-mail
        Usuario usuario = UserDao.getUserByEmail(email);
        if (usuario != null) {
            // Chama o método de autenticação facial da AutenticacaoService
            return autenticacaoService.autenticarUsuario(email, caminhoImagemUsuario);
        } else {
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }
}
