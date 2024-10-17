package com.projeto_biometria.controller;

import com.projeto_biometria.service.AutenticacaoService;
import com.projeto_biometria.model.Usuario;
import com.projeto_biometria.util.CameraUtil;

import java.util.Arrays;

public class LoginController {
    private static AutenticacaoService autenticacaoService;

    public LoginController() {
        autenticacaoService = new AutenticacaoService();
    }

    public static boolean login(String email) throws Exception {
        // Busca o usuário pelo e-mail
        UserDao userDao;
        Usuario usuario = UserDao.getUserByEmail(email);
        if (usuario != null) {
            // Captura uma nova imagem para comparação
            String imagemCapturada = "src/resources/login_captura.jpg";
            CameraUtil.captureImage(imagemCapturada);

            // Autenticação facial
            String imagemBanco = Arrays.toString(usuario.getImagemFacial());
            return autenticacaoService.autenticarUsuario(imagemCapturada, imagemBanco);
        } else {
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }
}
