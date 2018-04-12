/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dani.libreriagithub;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GitHub;

/**
 * Clase con los métodos para realizar las acciones de crear repositorio, hacer
 * commit, inicializar repositorio, hacer push y clonar.
 *
 * @author dani
 */
public class Metodos{

    /**
     * Método para crear un repositorio en el usuario de github que se establece
     * en el archivo ".github".
     *
     * @param nombre Recibe el nombre que se le quiere poner al nuevo
     * repositorio.
     */
    public static void crear(String nombre){
        try{
            GitHub github=GitHub.connect();
            GHCreateRepositoryBuilder builder;
            builder=github.createRepository(nombre);
            builder.create();
        }catch(IOException ex){
            System.out.println("Error:"+ex);
        }
    }

    /**
     * Método para clonar un repositorio de GitHub.
     *
     * @param url Dirección Web del repositorio en cuestión.
     * @param nombre Nombre que se le da al proyecto en local.
     */
    public static void clonar(String url, String nombre){
        try{
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File("/home/dani/NetBeansProjects/"+nombre))
                    .call();
        }catch(GitAPIException ex){
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para realizar un commit de un repositorio local.
     *
     * @param ruta Ruta del repositorio en local.
     * @param msn Mensaje para el commit.
     */
    public static void hacerCommit(String ruta, String msn){
        try{
            FileRepositoryBuilder repositoryBuilder=new FileRepositoryBuilder();
            Repository repository=repositoryBuilder.setGitDir(new File(ruta))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();

            Git git=new Git(repository);
            AddCommand add=git.add();
            add.addFilepattern(ruta).call();
            CommitCommand commit=git.commit();
            commit.setMessage(msn).call();
        }catch(IOException ex){
            System.out.println("Error:"+ex);
        }catch(GitAPIException ex){
            System.out.println("Error:"+ex);
        }

    }

    /**
     * Método para inicializar un repositorio.
     *
     * @param ruta Ruta del repositorio que desea inicializar.
     */
    public static void inicializarRepo(String ruta){
        InitCommand repositorio=new InitCommand();
        try{
            repositorio.setDirectory(new File(ruta)).call();
        }catch(GitAPIException ex){
            System.out.println("Error:"+ex);
        }
    }

    public static void push(String url, String repositorio){
        try{
            Repository localRepo=new FileRepository(repositorio);
            Git git=new Git(localRepo);
            
            // add remote repo:
            RemoteAddCommand remoteAddCommand=git.remoteAdd();
            remoteAddCommand.setName("origin");
            remoteAddCommand.setUri(new URIish(url));
            // you can add more settings here if needed
            remoteAddCommand.call();
            
            // push to remote:
            PushCommand pushCommand=git.push();
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("username", "password"));
            // you can add more settings here if needed
            pushCommand.call();
        }catch(IOException ex){
            System.out.println("Error: "+ex);
        }catch(URISyntaxException ex){
            System.out.println("Error: "+ex);
        }catch(GitAPIException ex){
            System.out.println("Error: "+ex);
        }
    }
}
