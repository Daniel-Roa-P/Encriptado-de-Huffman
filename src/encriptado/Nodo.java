
package encriptado;

public class Nodo {
  
    private String valor;   
    private String tipo; 
    private Nodo padre;
    private Nodo izquierdo;
    private Nodo derecho;
    
    
    Nodo(String n){
        
        this.valor = n;
        this.izquierdo = null;
        this.derecho   = null;
        this.padre = null;
        
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    
}

