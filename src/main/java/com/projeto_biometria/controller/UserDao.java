package com.projeto_biometria.controller;

import com.projeto_biometria.util.HibernateUtil;
import com.projeto_biometria.model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDao {
    // Substitua a criação do SessionFactory pela chamada à HibernateUtil
    public void saveUser(Usuario user) {
        Transaction transaction = null;
        System.out.println("Tentando salvar o usuário: " + user.getName());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            System.out.println("Usuário salvo com sucesso: " + user.getName());
        } catch (Exception e) {
            System.err.println("Erro no bloco try: " + e.getMessage());
            if (transaction != null && transaction.isActive()) {
                System.out.println("Transação revertida.");
                transaction.rollback();
            }
            System.err.println("Erro ao salvar o usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Usuario> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Usuario", Usuario.class).list();
        }
    }

    public static Usuario getUserByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario WHERE email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveUserGUI(String name, String email, String nivelAcesso, byte[] imagemFacial) {
        Usuario user = new Usuario();
        user.setName(name);
        user.setEmail(email);
        user.setNivel_acesso(nivelAcesso);
        user.setImagemFacial(imagemFacial);
        saveUser(user); // Chama o método existente
    }

}
