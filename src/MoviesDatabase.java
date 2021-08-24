/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MoviesDatabase{
    private static Connection connect;
    public static Connection getConnection()
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connect= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    
        return connect;
    }
}
