public class BichinhoVirtual {
    private static final int MIN_VALOR = 0;
    private static final int MAX_VALOR = 100;

    private final String nome;
    private int fome;
    private int energia;
    private int felicidade;
    private boolean doente;

    public BichinhoVirtual(String nome) {
        this.nome = nome;
        this.fome = 0;          // Inicia sem fome
        this.energia = 100;     // Inicia totalmente energizado
        this.felicidade = 100;  // Inicia totalmente feliz
        this.doente = false;
    }

    // --- Métodos Principais ---

    public void alimentar() {
        System.out.println("\n--> " + nome + " está comendo...");
        setFome(this.fome - 30);
        setFelicidade(this.felicidade + 5);

        // Recupera da doença se a fome baixar do nível máximo
        if (this.doente && this.fome < MAX_VALOR) {
            this.doente = false;
            System.out.println("✨ " + nome + " se recuperou da doença!");
        }
    }

    public void brincar() {
        if (this.doente) {
            System.out.println("\n⚠️ " + nome + " está doente com fome máxima e se recusa a brincar!");
            return;
        }

        System.out.println("\n--> " + nome + " está brincando!");
        setFelicidade(this.felicidade + 20);
        setEnergia(this.energia - 15);
        setFome(this.fome + 10);
    }

    public void dormir() {
        System.out.println("\n--> " + nome + " foi dormir... zZZz");
        setEnergia(MAX_VALOR);
        setFome(this.fome + 20);
    }

    public boolean estaFeliz() {
        return this.felicidade >= 70;
    }

    public void status() {
        System.out.println("----------------------------------------");
        System.out.println("Bichinho: " + nome + (doente ? " [DOENTE 🤒]" : " [SAUDÁVEL 😊]"));
        System.out.println("Fome:       " + fome + "/100");
        System.out.println("Energia:    " + energia + "/100");
        System.out.println("Felicidade: " + felicidade + "/100");
        System.out.println("Está Feliz? " + (estaFeliz() ? "Sim" : "Não"));
        System.out.println("----------------------------------------");
    }


    public String getNome() {
        return nome;
    }

    public int getFome() {
        return fome;
    }

    private void setFome(int valor) {
        this.fome = clamp(valor);
        if (this.fome >= MAX_VALOR) {
            this.doente = true;
            System.out.println("🚨 " + nome + " atingiu 100 de fome e ficou DOENTE!");
        }
    }

    public int getEnergia() {
        return energia;
    }

    private void setEnergia(int valor) {
        this.energia = clamp(valor);
    }

    public int getFelicidade() {
        return felicidade;
    }

    private void setFelicidade(int valor) {
        this.felicidade = clamp(valor);
    }

    public boolean isDoente() {
        return doente;
    }


    private int clamp(int valor) {
        return Math.max(MIN_VALOR, Math.min(MAX_VALOR, valor));
    }


    public static void main(String[] args) {
        BichinhoVirtual pet = new BichinhoVirtual("Pou");

        System.out.println("=== INÍCIO DO DIA ===");
        pet.status();

        // Atividades do dia
        pet.brincar();
        pet.brincar();
        pet.status();

        // Forçando o limite de fome para testar a regra do bônus (doença)
        System.out.println("\n... Passaram-se várias horas sem comida ...");
        pet.brincar();
        pet.brincar();
        pet.brincar();
        pet.brincar();
        pet.brincar(); // Fome atinge 100 aqui
        pet.status();

        // Tentando brincar estando doente
        pet.brincar();

        // Cuidando do bichinho
        pet.alimentar();
        pet.alimentar();
        pet.dormir();

        System.out.println("\n=== FIM DO DIA ===");
        pet.status();
    }
}

