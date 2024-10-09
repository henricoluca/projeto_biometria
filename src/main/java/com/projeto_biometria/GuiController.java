package com.projeto_biometria;


import java.util.Arrays;
import java.util.List;

public class GuiController {
    private UserDao userDao = new UserDao();
    private AmbienteDao ambienteDao = new AmbienteDao();
    private AutenticacaoService autenticacaoService = new AutenticacaoService();

    public void cadastrarUsuario(String email, String name, String nivelAcesso, byte[] imagemFacial) {
        userDao.saveUserGUI(name, email, nivelAcesso, imagemFacial);
    }

    public boolean realizarLogin(String email, byte[] imagemUsuario) throws Exception {
        return LoginController.login(email, Arrays.toString(imagemUsuario));
    }

    public List<MeioAmbiente> mostrarDadosMeioAmbiente(Usuario usuario) {
        return (List<MeioAmbiente>) ambienteDao.getAllMeioAmbiente(usuario);
    }
}
