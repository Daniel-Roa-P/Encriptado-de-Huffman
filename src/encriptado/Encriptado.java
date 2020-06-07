
package encriptado;

import java.util.ArrayList;


public class Encriptado {

    
    public static void main(String[] args) {
        
        String cadena = "bggeafeafabcfcfdceg";
        
        String [][] matriz;
        
        ArrayList letras = new ArrayList();
        ArrayList <Integer> frecuencia = new ArrayList();
        
        for(int i = 0; i<cadena.length(); i++){
        
            char temp = cadena.charAt(i);

            if(!letras.contains(temp)){
            
                letras.add(temp);
                frecuencia.add(0);
                
            }
            
            int index = letras.indexOf(temp);
            
            frecuencia.set(index, 1 + frecuencia.get(index));
            
            
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
        
        while(tempColum < 13){
            
            for(int i = frecuencia.size()-1; i>=0; i--){
            
                int temp = frecuencia.get(i);
                
                if(menor1 >= temp){
                
                    menor2 = menor1; 
                    menor1 = temp; 
                    
                    indice2 = indice1;
                    indice1 = i;
                    
                    System.out.println("menor 1: " + menor1);
                    System.out.println("menor 2: " + menor2);
                    
                } else if (temp < menor2 && temp != menor1) {
                    
                    menor2 = temp;
                    indice2 = i;
                    
                } 
                
            }
            
            System.out.println(" ");
            
            matriz[2][indice1] = Integer.toString(tempColum);
            matriz[2][indice2] = Integer.toString(tempColum);
            
            frecuencia.add(menor1 + menor2);
            matriz[1][tempColum] = Integer.toString(menor1 + menor2);
            
            frecuencia.set(indice1, 999);
            frecuencia.set(indice2, 999);
            
            if(menor1 > menor2){
            
               matriz[4][tempColum] = Integer.toString(indice1);
               matriz[5][tempColum] = Integer.toString(indice2); 
               
               matriz[3][indice1] = Integer.toString(2);
               matriz[3][indice2] = Integer.toString(1); 
               
            } else {
                
               matriz[4][tempColum] = Integer.toString(indice1);
               matriz[5][tempColum] = Integer.toString(indice2); 
                
               matriz[3][indice1] = Integer.toString(1);
               matriz[3][indice2] = Integer.toString(2); 
               
            }
          
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
        
    }
    
    
}
