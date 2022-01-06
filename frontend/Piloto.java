package frontend;

import backend.ListaUtilizadores;
import backend.Serializacao;
import backend.Sistema;
import backend.Utilizador;
import javax.swing.JOptionPane;

/**
 *
 * @author nunom
 */
public class Piloto {
    public static void main(String[] args)  {
        Sistema sistema;        
        String ficheiroDados = String.format("%s\\utilizadores.data", System.getProperty("user.dir"));
        System.out.println(String.format("Ficheiro de dados: %s.", ficheiroDados));
        Serializacao bd = new Serializacao(ficheiroDados);        
        
        //Se o ficheiro de base de dados nao existir
        if (! bd.getFicheiro().exists()) {
            //Cria uma instancia do sistema
            sistema = new Sistema();      
            //Adiciona dois utilizadores para que possa ser possivel entrar no sistema
            sistema.getListaUtilizadores().adicionar(new Utilizador("A001", "admin", "12345"));                  
        }else{
            sistema = bd.carregar();            
        }                 
                      
        Login login = new Login(sistema, bd);               
        login.setVisible(true);
            
        
    } 
}
