package encriptado;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Encriptado extends JFrame implements ActionListener {

    public JButton botonIngreso  = new JButton("Codificar Cadena");    
    
    public JLabel label = new JLabel("Digite el mensaje. Solo se aceptan caracteres de la 'a' a la 'z' : ");
    public JLabel label6 = new JLabel("Sin comprimir: ");
    public JLabel label7 = new JLabel(" * 8 = ");
    public JLabel label8 = new JLabel("Bits");
    public JLabel label9 = new JLabel("-");
    public JLabel label10 = new JLabel("-");
    
    public JTextField tfIngreso = new JTextField("danielalejandroroapalacios");
    
    ArbolHuffman arbol = new ArbolHuffman();
    
    private List <Nodo> listaArbol = new ArrayList();
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    private String entrada;
    private Nodo raiz;
    
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
        
        c.add(botonIngreso);

        c.add(scrollPane1);
        
        botonIngreso.addActionListener(this);
        
        label.setBounds(200, 25, 500, 20);
        label6.setBounds(500, 60, 200, 20);
        label7.setBounds(620, 60, 200, 20);
        label8.setBounds(700, 60, 200, 20);
        label9.setBounds(595, 60,370,20);
        label10.setBounds(650, 60,370,20);
        
        tfIngreso.setBounds(560, 25, 320, 20);
        
        botonIngreso.setBounds(900, 25, 200, 20);
        botonIngreso.setBackground(Color.ORANGE);
        
        scrollPane.setBounds(0, 200, 2500, 2500);
        scrollPane.setPreferredSize(new Dimension(2500, 2500));  
        scrollPane.setBackground(Color.LIGHT_GRAY);
        
        scrollPane1.setBounds(0, 200, 1280, 460);
        scrollPane1.setPreferredSize(new Dimension(1280, 460));
        scrollPane1.setBackground(Color.BLUE);
        
    }
    
    public void crearMatriz (String cadena) {
        
        for(int i = 0; i<cadena.length(); i++){
        
            char temp = cadena.charAt(i);

            if(!letras.contains(temp)){
            
                letras.add(temp);
                frecuencia.add(0);
                
            }
            
            int index = letras.indexOf(temp);
            int nuevaFrecuencia = 1 + frecuencia.get(index);
            
            frecuencia.set(index, nuevaFrecuencia);
                
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
        
        while(tempColum < ((letras.size()*2) - 1)){
            
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
                
                if(indice1 < letras.size()){
                
                    hijoDer = new Nodo(matriz[0][indice1]);
                    
                } else {
                
                    hijoDer = new Nodo(String.valueOf(menor1));
                    
                }
                
                if(indice2 < letras.size()){
                
                    hijoIzq = new Nodo(matriz[0][indice2]);
                    
                } else {
                
                    hijoIzq = new Nodo(String.valueOf(menor2));
                    
                }
                
                matriz[4][tempColum] = Integer.toString(indice1);
                matriz[5][tempColum] = Integer.toString(indice2); 
               
                matriz[3][indice1] = Integer.toString(2);
                matriz[3][indice2] = Integer.toString(1); 
               
            } else {
                
                if(indice1 < letras.size()){
                
                    hijoIzq = new Nodo(matriz[0][indice1]);
                    
                } else {
                
                    hijoIzq = new Nodo(String.valueOf(menor1));
                    
                }
                
                if(indice2 < letras.size()){
                
                    hijoDer = new Nodo(matriz[0][indice2]);
                    
                } else {
                
                    hijoDer = new Nodo(String.valueOf(menor2));
                    
                }
                
                matriz[4][tempColum] = Integer.toString(indice1);
                matriz[5][tempColum] = Integer.toString(indice2); 
                
                matriz[3][indice1] = Integer.toString(1);
                matriz[3][indice2] = Integer.toString(2); 
               
            }

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
        
        raiz = arbol.crearArbol(padres);
        
    }
    
    private boolean todoNull(List list) {
        
        for(Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
    
    public void listarArbol(List<Nodo> nodos, int nivel, int profundidad){
        
        if (nodos.isEmpty() || todoNull(nodos)){
        
            return;

        }
       
        int ubicacion = profundidad - nivel;
        List<Nodo> newNodes = new ArrayList<>();
        
        for (Nodo node : nodos) {
            if (node != null) {
                
                newNodes.add(node.getIzquierdo());
                newNodes.add(node.getDerecho());
                listaArbol.add(node.getIzquierdo());
                listaArbol.add(node.getDerecho());
                
                
            } else {
                
                newNodes.add(null);
                newNodes.add(null);
                listaArbol.add(null);
                listaArbol.add(null);
            
            }
        }
        
        listarArbol(newNodes, nivel + 1, profundidad);
            
    
    }
    
    void pintarArbol(){
        
        int exponente = 0;
        int i=0;
        int j=0;
        int coorX = 1000;
        int coorY = 30;
        
        JLabel numeros[]=new JLabel[listaArbol.size()];
        
        while(i<listaArbol.size()){
        
            if(j >= Math.pow(2, exponente)){           
                
                exponente=exponente+1;
                j=0;
                coorY = coorY + 70;
                coorX = (int) ((2000/(Math.pow(2, (exponente + 1)))));
                
            }
            
            if(j != 0){
                coorX = (int) ( coorX + (2000/(Math.pow(2, (exponente) ))) );
            }
            
            if(listaArbol.get(i) != null){
            
                if(listaArbol.get(i).getIzquierdo() == null && listaArbol.get(i).getDerecho() == null){
                 
                    numeros[i]= new JLabel(listaArbol.get(i).getValor() + " - " + listaArbol.get(i).getCodigo());
                    numeros[i].setBounds(coorX, coorY, 100, 30);
                   
                } else {
                    
                    numeros[i]= new JLabel(listaArbol.get(i).getValor());
                    numeros[i].setBounds(coorX, coorY, 30, 30);
                    
                }
                
                JLabel img1 = new JLabel();
                
                int escala = (int) (450/((Math.pow(2, (exponente)))));
                
                if(listaArbol.get(i).getDerecho() != null){
                
                    
                    ImageIcon imgIcon = new ImageIcon(getClass().getResource("flecha.png"));
                    
                    Image imgEscalada = imgIcon.getImage().getScaledInstance(escala,50, Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);
                    img1.setBounds(coorX+20 , coorY + 30, escala, 50);
                    img1.setIcon(iconoEscalado);
                    
                    scrollPane.add(img1);
            
                    
                } 
                
                img1 = new JLabel();
                
                if(listaArbol.get(i).getIzquierdo()!= null){
                
                    ImageIcon imgIcon = new ImageIcon(getClass().getResource("fder.png"));
            
                    Image imgEscalada = imgIcon.getImage().getScaledInstance(escala,50, Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);
                    img1.setBounds(coorX-escala , coorY + 30, escala, 50);
                    img1.setIcon(iconoEscalado);
                    
                    scrollPane.add(img1);
                    
                }
                
                scrollPane.add(numeros[i]);
            
            
            } else {

                numeros[i]= new JLabel("");
                scrollPane.add(numeros[i]);

            }
            
            i++;
            j++;
        }
         
        scrollPane.repaint();
        
    }
    
    void dibujar(){
        
        List <Nodo> lista = new ArrayList();
        lista.add(raiz);
        listaArbol = new ArrayList();
        listaArbol.add(raiz);
        listarArbol(lista, 0, arbol.getMax());

        pintarArbol();
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.botonIngreso){
            
            entrada = tfIngreso.getText();
            
            int caracteres = entrada.length();
            
            label9.setText(Integer.toString(caracteres));
            label10.setText(Integer.toString(8*caracteres));
            
            crearMatriz(entrada);
            
            scrollPane.removeAll();
            
            dibujar();
            
            scrollPane.repaint();
            scrollPane1.setViewportView(scrollPane);
            
        }
        
    }
    
}
