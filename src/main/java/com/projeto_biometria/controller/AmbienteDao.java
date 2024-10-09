package com.projeto_biometria.controller;

import com.projeto_biometria.util.HibernateUtil;
import com.projeto_biometria.model.MeioAmbiente;
import com.projeto_biometria.model.Usuario;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AmbienteDao {

    public List<MeioAmbiente> getAllMeioAmbiente(Usuario usuario) {
        List<MeioAmbiente> registrosFiltrados = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Obtém todos os registros de MeioAmbiente
            List<MeioAmbiente> todosOsRegistros = session.createQuery("from MeioAmbiente", MeioAmbiente.class).list();

            // Filtra os registros de acordo com o nível de acesso do usuário
            for (MeioAmbiente registro : todosOsRegistros) {
                if (checkNivelAcesso(usuario, registro)) {
                    registrosFiltrados.add(registro); // Adiciona o registro se o acesso for permitido
                }
            }
        }

        return registrosFiltrados; // Retorna todos os registros filtrados
    }

    public boolean checkNivelAcesso(Usuario user, MeioAmbiente registro) {
        int nivelAcessoUsuario = Integer.parseInt(user.getNivel_acesso());
        int nivelAcessoRequerido = Integer.parseInt(registro.getNivel_acesso_requerido());

        return nivelAcessoUsuario >= nivelAcessoRequerido;
    }
}

