interface Bloco{
    public void quebra(String ferramenta);
}


class Madeira implements Bloco{
    public void quebra(String ferramenta){
        System.out.println("Madeira foi dropada");
    }

}

class Grama implements Bloco{
    public void quebra(String ferramenta){
        System.out.println("Terra foi dropada");
    }

}

class Terra implements Bloco{
    public void quebra(String ferramenta){
        System.out.println("Terra foi dropada");
    }

}

class Pedra implements Bloco{
    public void quebra(String ferramenta){
        if (ferramenta == "picareta") {
            System.out.println("Pedregulho foi dropado");
        }
    }
}

class Pedregulho implements Bloco{
    public void quebra(String ferramenta){
        System.out.println("Pedregulho foi dropado");
    }

}



class Mini_mine{
    public static void main(String[] args) {
        Bloco blocos[] = { new Madeira(), new Grama(), new Terra(), new Pedra(), new Pedregulho()};

        for (Bloco b: blocos) {
            b.quebra("picareta");
        }
    }

}