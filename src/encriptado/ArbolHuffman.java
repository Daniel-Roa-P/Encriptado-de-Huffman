
package encriptado;

import java.util.ArrayList;

public class ArbolHuffman {
    
    private Nodo raiz = null;
    
    public Nodo crearArbol(ArrayList <Nodo> padres){
        
        raiz = padres.get(padres.size()-1);
        
        Nodo tempRaiz, tempHijo;
        
        int i = padres.size()-1;
        boolean vacio1 = true, vacio2 = true;
        
        while(!padres.isEmpty()){

            if(i!=0){
            
                tempRaiz = padres.get(padres.size()-1);
                tempHijo = padres.get(i-1);

                if( tempHijo.getValor().equals(tempRaiz.getDerecho().getValor()) && vacio1 ){

                    tempRaiz.setDerecho(tempHijo);
                    i--;
                    vacio1=false;
                    
                }else if( tempHijo.getValor().equals(tempRaiz.getIzquierdo().getValor()) && vacio2 ){

                    tempRaiz.setIzquierdo(tempHijo);
                    i--;
                    vacio2=false;
                    
                } else if ((i-1) == 0) {

                    padres.remove(padres.size()-1);
                    i = padres.size()-1;
                    vacio1 = true;
                    vacio2 = true;

                } else {

                    i--;

                }

            } else {
                
                break;
                
            }
            
        }
        
        return raiz;
        
    }
    
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    
}

