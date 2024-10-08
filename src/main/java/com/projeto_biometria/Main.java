package com.projeto_biometria;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        AmbienteDao ambienteDao = new AmbienteDao();

        MeioAmbiente ambiente = new MeioAmbiente();
        ambiente.setTitle("Queimadas");
        ambiente.setDescription("Informações confidenciais");
        ambiente.setCategory("Informações confidenciais");
        ambiente.setNivel_acesso_requerido("3");

        ambienteDao.saveMeioAmbiente(ambiente);

        List<MeioAmbiente> ambientes = ambienteDao.getAllMeioAmbiente();
        ambientes.forEach(u -> System.out.println(u.getTitle()));
    }
}