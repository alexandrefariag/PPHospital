/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;
import java.io.Serializable;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre
 */
public class Sistema  implements Serializable{      //declarar as listas todas que vão ser utilizadas
    private Utilizador utilizadorLigado;
    private ListaUtilizadores utilizadores;
    private ListaDoentes doentes;
    private ListaEnfermarias enfermarias;
    private ListaHospitais hospitais;
    private ListaEquipamentos equipamentos;
    
    public Sistema() {    //construtor
    utilizadores = new ListaUtilizadores();
    doentes = new ListaDoentes();
    enfermarias = new ListaEnfermarias();
    hospitais = new ListaHospitais();
    equipamentos = new ListaEquipamentos();
    }
    
    public ListaUtilizadores getListaUtilizadores() {
    return utilizadores;
    }
    public Utilizador getUtilizadorLigado() {
    return utilizadorLigado;
    }
    public void setUtilizadorLigado(Utilizador utilizador){
    this.utilizadorLigado = utilizador;
    }
    public ListaEnfermarias getListaEnfermarias() {
    return enfermarias;
    }
    public ListaHospitais getListaHospitais() {
    return hospitais;
    }
    public ListaEquipamentos getListaEquipamentos(){
    return equipamentos;
    }
    public ListaDoentes getListaDoentes() {
        return doentes;
    }
    
    
    public boolean autenticarUtilizador(String codigo, String password) {   //recebe os dados do frontend
        if (utilizadores.existeUtilizador(codigo)) {                        //verifica se o utilizador existe percorrendo a lista dos utilizadores
            Utilizador u = utilizadores.getUtilizador(codigo);                
            if (u.getPassword().equals(password)){                          //verifica se a password escrita no front é igual à associada ao código recebido do front
                utilizadorLigado = u;                                       
                return true;
            }                                      
        }        
        return false;
    }

    public void inicializar() {
    /*
    Hospital h1 = new Hospital("H001", "Hospital de S. Marcos", "Braga");   //cria o objeto de um hospital
    hospitais.adicionar(h1);
    Enfermaria e1 = new Enfermaria("Enfermaria 1", "Normal", 10, h1);
    h1.getEnfermarias().adicionar(e1);
    */
    Utilizador u1 = new Utilizador ("A001", "admin", "12345");         //cria objeto de um user
   Utilizador a = new Utilizador ("user1", "gestor", "12345");
   
    utilizadores.adicionar(u1);                                             //adicionar os objetos à lista
    utilizadores.adicionar(a);
    /*LocalDate dataEntrada1 = LocalDate.of(2020, 4, 28);
    LocalDate dataEntrada2 = LocalDate.of(2020, 5, 30);

    Doente d1 = new Doente ("d123", "João", "Braga", dataEntrada1);
    Doente d2 = new Doente ("d124", "Diogo", "Porto", dataEntrada2);
    h1.getDoentes().adicionar(d1);
    h1.getDoentes().adicionar(d2);
   */
    }
    
    public void terminar() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente terminar o programa?", "A sair...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
