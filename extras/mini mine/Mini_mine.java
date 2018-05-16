interface Bloco{
    public Bloco quebra(String ferramenta);

    public String toString();
}


class Madeira implements Bloco{
    public Bloco quebra(String ferramenta){
        return this;
    }

    public String toString(){
        return "= Madeira";
    }
}

class Grama implements Bloco{
    public Bloco quebra(String ferramenta){
        return new Terra();
    }

    public String toString(){
        return "= Grama";
    }

}

class Terra implements Bloco{
    public Bloco quebra(String ferramenta){
        return this;
    }

    public String toString(){
        return "= Terra";
    }
}

class Pedra implements Bloco{
    public Bloco quebra(String ferramenta){
        if (ferramenta == "picareta")
            return new Pedregulho();
        else
            return null;
    }

    public String toString(){
        return "= Pedra";
    }
}

class Pedregulho implements Bloco{
    public Bloco quebra(String ferramenta){
        return this;
    }

    public String toString(){
        return "= Pedregulho";
    }

}



class Mini_mine{
    public static void main(String[] args) {
        Bloco b1 =  new Madeira(), b2 = new Grama(), b3 = new Terra(), b4 = new Pedra(), b5 = new Pedregulho(), b6 = new Pedra();

        System.out.println("Bloco a serem quebrados com nada " + b1);
        System.out.println("Bloco a serem quebrados com nada " + b2);
        System.out.println("Bloco a serem quebrados com nada " + b3);
        System.out.println("Bloco a serem quebrados com picareta " + b4);
        System.out.println("Bloco a serem quebrados com nada " + b5);
        System.out.println("Bloco a serem quebrados com nada " + b6);

        b1 = b1.quebra("");
        b2 = b2.quebra("");
        b3 = b3.quebra("");
        b4 = b4.quebra("picareta");
        b5 = b5.quebra("");
        b6 = b6.quebra("");

        System.out.println("Objeto resultante " + b1);
        System.out.println("Objeto resultante " + b2);
        System.out.println("Objeto resultante " + b3);
        System.out.println("Objeto resultante " + b4);
        System.out.println("Objeto resultante " + b5);
        System.out.println("Objeto resultante " + b6);

    }

}