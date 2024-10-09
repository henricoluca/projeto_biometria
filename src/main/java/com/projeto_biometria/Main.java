package com.projeto_biometria;

import com.projeto_biometria.service.AutenticacaoService;

public class Main {
    public static void main(String[] args) {

        AutenticacaoService autenticacaoService = new AutenticacaoService();

        // Supondo que o usuário enviou uma imagem para autenticação
        String email = "usuario@example.com";  // Email do usuário
        String imageFilePath = "path/para/imagem/facial.jpg"; // Caminho da imagem facial

        try {
            boolean autenticado = autenticacaoService.autenticarUsuario(email, imageFilePath);

            if (autenticado) {
                System.out.println("Usuário autenticado com sucesso. Acesso permitido.");
                // Aqui você pode continuar com o processo de autenticação e checar o nível de acesso
            } else {
                System.out.println("Falha na autenticação. Acesso negado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}