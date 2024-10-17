package com.projeto_biometria.controller;

import com.projeto_biometria.model.MeioAmbiente;
import com.projeto_biometria.model.Usuario;
import com.projeto_biometria.service.AutenticacaoService;
import com.projeto_biometria.util.CameraUtil;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GuiController {
    private UserDao userDao = new UserDao();
    private AmbienteDao ambienteDao = new AmbienteDao();
    private AutenticacaoService autenticacaoService = new AutenticacaoService();

    // Método para capturar a imagem e salvar o usuário no banco
    public void cadastrarUsuario(String email, String name, String nivelAcesso) throws Exception {
        // Captura a imagem
        String imagePath = CameraUtil.captureImage("cadastro_image.jpg");

        // Converte a imagem em byte array
        byte[] imagemFacial = Files.readAllBytes(Paths.get(imagePath));

        // Cria o objeto usuário e salva no banco
        Usuario user = new Usuario();
        user.setName(name);
        user.setEmail(email);
        user.setNivel_acesso(nivelAcesso);
        user.setImagemFacial(imagemFacial);

        userDao.saveUser(user);
    }

    // Método para realizar o login
    public boolean realizarLogin(String email) throws Exception {
        // Captura a imagem no login
        String imagePath = CameraUtil.captureImage("login_image.jpg");

        // Realiza a autenticação com a imagem capturada
        return autenticacaoService.autenticarUsuario(email, imagePath);
    }

    // Exibir dados do meio ambiente conforme nível de acesso
    public List<MeioAmbiente> mostrarDadosMeioAmbiente(Usuario usuario) {
        return ambienteDao.getAllMeioAmbiente(usuario);
    }
}
