/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.arraylist.view;

import br.com.arraylist.model.DB;
import java.sql.Connection;

/**
 *
 * @author Aluno
 */
public class Teste {
     public static void main(String[] args) {
        Connection con = DB.getInstance().getConnection();
     }
}
