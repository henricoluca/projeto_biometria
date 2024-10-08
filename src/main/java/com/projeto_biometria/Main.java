package com.projeto_biometria;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();

        Usuario user = new Usuario();
        user.setName("João Silva");
        user.setRole("Administrador");
        user.setEmail("joao.silva@meioambiente.gov.br");
        user.setPassword("senha123");
        user.setNivel_acesso("3");

        userDao.saveUser(user);

        List<Usuario> users = userDao.getAllUsers();
        users.forEach(u -> System.out.println(u.getName()));
    }
}