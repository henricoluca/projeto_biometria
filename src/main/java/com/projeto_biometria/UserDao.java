package com.projeto_biometria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDao {
    private static SessionFactory sessionFactory;
    // Inicializa a sessionFactory
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Criação de sessão falhou." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    // Função para cadastrar um usuário
    public void saveUser(Usuario user) {
        Transaction transaction = null;
        System.out.println("Tentando salvar o usuário: " + user.getName());
        try (Session session = sessionFactory.openSession()) {
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
    // Função para listar todos os usuários
    public List<Usuario> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Usuario", Usuario.class).list();
        }
    }
    // Função para atualizar um usuário
    public void updateUser(Usuario user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    // Função para deletar um usuário por id
    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Usuario user = session.get(Usuario.class, id);
            if (user != null) {
                session.remove(user);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Função para buscar um usuário pelo e-mail
    public static Usuario getUserByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Usuario WHERE email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
