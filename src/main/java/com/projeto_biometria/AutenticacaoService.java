package com.projeto_biometria;

import com.projeto_biometria.Usuario;
import com.projeto_biometria.UserDao;
import com.projeto_biometria.ReconhecimentoFacial;

public class AutenticacaoService {
    private UserDao userDao;
    private ReconhecimentoFacial reconhecimentoFacial;

    public AutenticacaoService() {
        this.userDao = new UserDao();
        this.reconhecimentoFacial = new ReconhecimentoFacial();
    }

    public boolean autenticarUsuario(String email, String imageFilePath) throws Exception {
        Usuario user = userDao.getUserByEmail(email); // Recupera o usuário pelo email

        if (user != null) {
            // Supondo que você armazene a imagem de referência do usuário no banco de dados ou em algum local específico
            String imagemReferenciaUsuario = "path/para/imagem/referencia/usuario.jpg"; // Coloque o caminho correto

            // Compara a imagem enviada com a imagem de referência do usuário
            boolean faceMatches = reconhecimentoFacial.compareFaces(imageFilePath, imagemReferenciaUsuario, 70F);

            if (faceMatches) {
                System.out.println("Autenticação facial bem-sucedida para o usuário: " + user.getName());
                return true;
            } else {
                System.out.println("Falha na autenticação facial para o usuário: " + user.getName());
                return false;
            }
        }

        System.out.println("Usuário não encontrado com o email: " + email);
        return false;
    }
    public boolean checkNivelAcesso(Usuario user, MeioAmbiente registro) {
        int nivelAcessoUsuario = Integer.parseInt(user.getNivel_acesso());
        int nivelAcessoRequerido = Integer.parseInt(registro.getNivel_acesso_requerido());

        return nivelAcessoUsuario >= nivelAcessoRequerido;
    }

}
