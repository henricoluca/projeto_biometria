package com.projeto_biometria;

import java.util.Arrays;

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
            // Recupera a imagem facial armazenada do usuário
            byte[] imagemReferenciaUsuario = user.getImagemFacial();

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
}
