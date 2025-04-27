import models.Livro;
import services.BibliotecaService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BibliotecaService bs = new BibliotecaService();

        int option = menu();
        do {
            switch (option) {
                case 1:
                    System.out.println("Qual tipo de material vc deseja cadastrar:");
                    System.out.println("---------------------");
                    System.out.println("1 - Livro");
                    System.out.println("2 - Artigo científico");
                    System.out.println("3 - Revista");
                    System.out.println("---------------------");
                    int tipoMaterial = sc.nextInt();
                    sc.nextLine();

                    switch (tipoMaterial) {
                        case 1:
                            System.out.println("Titulo do Livro:");
                            String tituloLivro = sc.nextLine();
                            System.out.println("Autor:");
                            String autorLivro = sc.nextLine();
                            System.out.println("ISBN:");
                            int isbn = sc.nextInt();
                            System.out.println("Ano de publicação");
                            int anoPublicacao = sc.nextInt();

                            bs.CriarLivro(autorLivro, isbn, tituloLivro, anoPublicacao);
                            break;
                        case 2:
                            System.out.println("Titulo do Artigo:");
                            String tituloArtigo = sc.nextLine();
                            System.out.println("Ano de publicação");
                            int anoPublicacaoArtigo = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Digite o nome do autores(separado por virgulas):");
                            String nomeAutores = sc.nextLine();
                            System.out.println("Nome periódico:");
                            String periodico = sc.nextLine();
                            System.out.println("DOI:");
                            String doi = sc.nextLine();

                            bs.CriarArtigoCientifico(tituloArtigo, anoPublicacaoArtigo, periodico, doi, nomeAutores);
                            break;
                        case 3:
                            System.out.println("Titulo da Revista:");
                            String tituloRevista = sc.nextLine();
                            System.out.println("Ano de publicação:");
                            int anoPublicacaoRevista = sc.nextInt();
                            System.out.println("Nome da editora:");
                            String editora = sc.nextLine();
                            System.out.println("Edição:");
                            int edicao = sc.nextInt();

                            bs.CriarRevista(tituloRevista, anoPublicacaoRevista, editora, edicao);
                            break;
                    }
                    option = menu();
                    break;
                case 2:
                    bs.TodosMateriais();
                    option = menu();
                    break;
                case 3:
                    bs.materiaisDisponiveis();
                    option = menu();
                    break;
                case 4:
                    System.out.println("Digite o titulo do material:");
                    sc.nextLine();
                    String tituloMaterialBusca = sc.nextLine();

                    bs.BuscarTitulo(tituloMaterialBusca);
                    option = menu();
                    break;
            }
        } while (option != 0);

    }

    public static Integer menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("0 - sair");
        System.out.println("1 - Cadastrar material:");
        System.out.println("2 - Listar todos os materiais:");
        System.out.println("3 - Materiais disponíveis:");
        System.out.println("4 - Buscar Material por titulo:");

        System.out.println("---------------------");
        return sc.nextInt();
    }
}