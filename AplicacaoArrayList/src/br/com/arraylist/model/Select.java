/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.arraylist.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Aluno
 */
public class Select {
    
    public ArrayList<String> pesquisar (String table){
    
        ArrayList<String> item = new ArrayList<String>();
        
        Connection con = DB.getInstance().getConnection();
        
        Statement stmt = null;
        try
        {
            stmt = con.createStatement();
        }
        catch (SQLException err)
                {
                    err.printStackTrace();
                }
        
        ResultSet rs = null;
        try 
        {
            rs = stmt.executeQuery("select * from " + table);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            while(rs.next())
            {
                item.add(rs.getString("usuario"));
                item.add(rs.getString("senha"));
            }
            
        }
         catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        try 
        {
            rs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        try 
            {
                stmt.close();
            }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
       DB.getInstance().shutdown();
       
       
        
       
       return item; 
    }
    
    
}
