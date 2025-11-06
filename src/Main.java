import dao.UsuarioDAO;
import model.Usuario;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== SISTEMA DE CADASTRO ===");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Buscar usuário por ID");
            System.out.println("4. Atualizar usuário");
            System.out.println("5. Deletar usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            
            switch (opcao) {
                case 1:
                    cadastrarUsuario(usuarioDAO, scanner);
                    break;
                case 2:
                    listarUsuarios(usuarioDAO);
                    break;
                case 3:
                    buscarUsuario(usuarioDAO, scanner);
                    break;
                case 4:
                    atualizarUsuario(usuarioDAO, scanner);
                    break;
                case 5:
                    deletarUsuario(usuarioDAO, scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void cadastrarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        
        Usuario usuario = new Usuario(nome, email, idade);
        usuarioDAO.criarUsuario(usuario);
    }
    
    private static void listarUsuarios(UsuarioDAO usuarioDAO) {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
    
    private static void buscarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.print("ID do usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario);
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
    
    private static void atualizarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.print("ID do usuário a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        System.out.print("Novo nome (" + usuario.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            usuario.setNome(nome);
        }
        
        System.out.print("Novo email (" + usuario.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            usuario.setEmail(email);
        }
        
        System.out.print("Nova idade (" + usuario.getIdade() + "): ");
        String idadeStr = scanner.nextLine();
        if (!idadeStr.isEmpty()) {
            usuario.setIdade(Integer.parseInt(idadeStr));
        }
        
        usuarioDAO.atualizarUsuario(usuario);
    }
    
    private static void deletarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.print("ID do usuário a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        usuarioDAO.deletarUsuario(id);
    }
}
