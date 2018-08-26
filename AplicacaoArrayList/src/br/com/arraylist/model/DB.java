/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.arraylist.model;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Aluno
 */
public class DB {

    private static DB instance = null;
    private Connection connection = null;
    private int clients = 0;

    //Cria construtor privado - padrão Singleton
    /* O padrão Singleton  tem como  definição garantir que uma classe tenha
        * Apenas uma instancia
        *de si mesmo e que forneça um ponto global de acesso a ela.
        * Ou seja, uma classe gerencia a própria instancia dela alem de
        *evitar que qualquer outra classe crie uma instancia dela.
        * Para criar a instancia tem-se que passar pela classe obrigatoriamente,
        *nenhuma outra classe pode instanciar ela.
        * O padrão Singleton tambem oferece um ponto global de acesso a sua
        *instancia.
        * A propria classe sempre vai oferecer a propria instancia dela
        *e caso não tenha ainda uma instancia, então ela mesma cria e retorna
        *essa nova instancia.
     */
    private DB() {
        try {
            //Properties - usado para ler arquivos de texto
            Properties prop = new Properties();
            prop.load(new FileInputStream("../AplicacaoArrayList/src/br/com/arraylist/model/DB.properties"));
            String dbDriver = prop.getProperty("db.driver").trim();
            String dbUrl = prop.getProperty("db.url").trim();
            String dbUser = prop.getProperty("db.user").trim();
            String dbPwd = prop.getProperty("db.pwd").trim();
            Class.forName(dbDriver);
            if (dbUser.length() != 0) { //Para ter acesso com usuario e senha
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            } else { //Para acesso sem usuario e senha
                connection = DriverManager.getConnection(dbUrl);
            }
            System.out.println("DB[conexao OK]");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe);
        } catch (IOException ioe) {
            System.err.println(ioe);
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    //Retorna uma instancia unica    
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    //Retorna a conexão
    public Connection getConnection() {
        if (connection == null) {
            throw new RuntimeException("connection=null");
        }
        clients++;
        System.out.println("DB[conexao cliente]" + clients);
        return connection;
    }

    //Fecha a conexão
    public void shutdown() {
        System.out.println("DB[shutdown cliente]");
        clients--;
        if (clients > 0) {
            return;
        }
        try {
            connection.close();
            instance = null;
            System.out.println("DB[conexao fechada]");
            connection = null;
        } catch (SQLException sqle) {
            System.err.print(sqle);
        }
    }

}
