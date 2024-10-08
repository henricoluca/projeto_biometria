package com.projeto_biometria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AmbienteDao {

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

    // Função para criar um registro de Meio Ambiente
    public void saveMeioAmbiente(MeioAmbiente meioAmbiente) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(meioAmbiente);
            transaction.commit();
            System.out.println("Registro de Meio Ambiente salvo com sucesso: " + meioAmbiente.getTitle());
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Transação revertida.");
                transaction.rollback();
            }
            System.err.println("Erro ao salvar o registro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Função para buscar todos os registros de Meio Ambiente
    public List<MeioAmbiente> getAllMeioAmbiente() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from MeioAmbiente", MeioAmbiente.class).list();
        }
    }

    // Função para atualizar um registro de Meio Ambiente
    public void updateMeioAmbiente(MeioAmbiente meioAmbiente) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(meioAmbiente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Transação revertida.");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Função para deletar um registro de Meio Ambiente pelo ID
    public void deleteMeioAmbiente(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            MeioAmbiente meioAmbiente = session.get(MeioAmbiente.class, id);
            if (meioAmbiente != null) {
                session.remove(meioAmbiente);
                transaction.commit();
                System.out.println("Registro de Meio Ambiente deletado com sucesso: " + meioAmbiente.getTitle());
            }
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Transação revertida.");
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
