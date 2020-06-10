
package encriptado;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Encriptado extends JFrame implements ActionListener {

    public JButton botonCrear  = new JButton("Crear arbol");    
    
    public JLabel label = new JLabel("Digite el mensaje. Solo se aceptan caracteres de la 'a' a la 'z' : ");
    public JLabel label6 = new JLabel("Sin comprimir: ");
    public JLabel label7 = new JLabel(" * 8 = ");
    public JLabel label8 = new JLabel("Bits");
    public JLabel label9 = new JLabel("-");
    public JLabel label10 = new JLabel("-");
    
    public JTextField tfIngreso = new JTextField("bggeafeafabcfcfdceg");
    
    private List <Nodo> listaArbol = new ArrayList();
    private JPanel jpan;
    private String entrada;
    
    String [][] matriz;
    
    ArrayList letras = new ArrayList();
    ArrayList <Integer> frecuencia = new ArrayList();    
    ArrayList <Nodo> padres = new ArrayList();
    
    public static void main(String[] args) {
        
        Encriptado encriptado = new Encriptado();
        encriptado.setSize(1300, 700);
        encriptado.setTitle("Arboles Huffman");
        encriptado.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        encriptado.setVisible(true);   
        
    }
    
    Encriptado(){
    
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(label);
        
        c.add(label6);
        c.add(label7);
        c.add(label8);
        c.add(label9);
        c.add(label10);
        
        c.add(tfIngreso);
        
        c.add(botonCrear);
        
        jpan = new JPanel();       
        
        jpan.setBounds(0, 200, 1300, 500);
        jpan.setOpaque(false);
        jpan.setBackground(new Color(0,0,0,0));
        add(jpan);
        
        botonCrear.addActionListener(this);
        
        label.setBounds(200, 25, 500, 20);
        label6.setBounds(500, 60, 200, 20);
        label7.setBounds(620, 60, 200, 20);
        label8.setBounds(700, 60, 200, 20);
        label9.setBounds(595, 60,370,20);
        label10.setBounds(730, 60,370,20);
        
        tfIngreso.setBounds(560, 25, 320, 20);
        
        botonCrear.setBounds(900, 25, 200, 20);
        botonCrear.setBackground(Color.ORANGE);
        
    }
    
    public void crearMatriz (String cadena) {
        
        for(int i = 0; i<cadena.length(); i++){
        
            char temp = cadena.charAt(i);

            if(!letras.contains(temp)){
            
                letras.add(temp);
                frecuencia.add(0);
                
            }
            
            int index = letras.indexOf(temp);
            
            frecuencia.set(index, 1 + frecuencia.get(index));
            
            
        }
        
        for(int i=0;i < frecuencia.size() + 1; i++) {
            
            for(int j=i+1; j < frecuencia.size(); j++) {

                if(frecuencia.get(i) > frecuencia.get(j)) {

                        int temp = frecuencia.get(i);
                        frecuencia.set(i, frecuencia.get(j));
                        frecuencia.set(j, temp);

                        String tempB = String.valueOf(letras.get(i));
                        letras.set(i, String.valueOf(letras.get(j)));
                        letras.set(j, tempB);

                    }

            }

        }
        
        System.out.println(letras);
        System.out.println(frecuencia);
        
        matriz = new String [6][(letras.size()*2) - 1];
        
        for(int i = 0; i < 6; i++){
        
            for(int j = 0; j < ((letras.size()*2) - 1); j++){
                
                if(i < 2 && j < letras.size()){
                    
                    matriz[0][j] = String.valueOf(letras.get(j));
                    matriz[1][j] = String.valueOf(frecuencia.get(j));
                
                } else {
                    
                  matriz[i][j] = "0";  
                    
                }
            }

        }
        
        int tempColum = letras.size();
        
        int menor1 = 999, menor2 = 999;
        int indice1 = 0, indice2 = 0;
        
        Nodo padre, hijoDer, hijoIzq;
        
        while(tempColum < 13){
            
            for(int i = frecuencia.size()-1; i>=0; i--){
            
                int temp = frecuencia.get(i);
                
                if(menor1 >= temp){
                
                    menor2 = menor1; 
                    menor1 = temp; 
                    
                    indice2 = indice1;
                    indice1 = i;
                    
                } else if (temp < menor2 && temp != menor1) {
                    
                    menor2 = temp;
                    indice2 = i;
                    
                } 
                
            }
            
            matriz[2][indice1] = Integer.toString(tempColum);
            matriz[2][indice2] = Integer.toString(tempColum);
            
            frecuencia.add(menor1 + menor2);
            padre = new Nodo(String.valueOf(menor1 + menor2));
            matriz[1][tempColum] = Integer.toString(menor1 + menor2);
            
            frecuencia.set(indice1, 999);
            frecuencia.set(indice2, 999);
            
            if(menor1 > menor2){           
                
                hijoDer = new Nodo(String.valueOf(menor1));
                hijoIzq = new Nodo(String.valueOf(menor2));
                
                matriz[4][tempColum] = Integer.toString(indice1);
                matriz[5][tempColum] = Integer.toString(indice2); 
               
                matriz[3][indice1] = Integer.toString(2);
                matriz[3][indice2] = Integer.toString(1); 
               
            } else {
                
                hijoDer = new Nodo(String.valueOf(menor2));
                hijoIzq = new Nodo(String.valueOf(menor1));
                
                matriz[4][tempColum] = Integer.toString(indice1);
                matriz[5][tempColum] = Integer.toString(indice2); 
                
                matriz[3][indice1] = Integer.toString(1);
                matriz[3][indice2] = Integer.toString(2); 
               
            }
          
            hijoDer.setPadre(padre);
            hijoIzq.setPadre(padre);

            padre.setDerecho(hijoDer);
            padre.setIzquierdo(hijoIzq);
            
            padres.add(padre);
            
            tempColum++;
            menor1 = 999;
            menor2 = 999;
            
        }
        
        for(int i = 0; i < 6; i++){
        
            for(int j = 0; j < ((letras.size()*2) - 1); j++){
                
                System.out.print(matriz[i][j] + ", ");
                
            }
            
            System.out.println(" ");
            
        }
        
        System.out.println(frecuencia);
        System.out.println(padres);
        
        ArbolHuffman arbol = new ArbolHuffman();
        
        Nodo raiz = arbol.crearArbol(padres);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
    }
    
}
