/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dani.libreriagithub;

import java.io.IOException;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

/**
 *
 * @author dani
 */
public class Principal{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        GitHub github=GitHub.connect();
        GHCreateRepositoryBuilder builder;
        builder = github.createRepository("Repositorio creado desde NetBeans");
        builder.create();
    }

}
