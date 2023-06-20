package ProjectCRUD;

import ProjectCRUD.dao.ClienteMapDAO;
import ProjectCRUD.dao.IClienteDAO;
import ProjectCRUD.domain.Cliente;


import javax.swing.*;

public class App {
    private static IClienteDAO iClienteDAO;
    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteracão, ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)){
            if("".equals(opcao)){
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida, Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteracão, ou 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "digite os dados do cliente separado por virgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Numero, Cidade, Estado", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                cadastrar(dados);
            } else if(isConsultar(opcao)){
                String dados = JOptionPane.showInputDialog(null, "digite o CPF", "Consultar", JOptionPane.INFORMATION_MESSAGE);

                consultar(dados);
                
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteracão, ou 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String dados) {
        if (dados == null || dados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CPF inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if(cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString(),"Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)){
            return true;
        }
        return false;
    }

    private static void cadastrar(String dados) {
        if (dados == null || dados.equals("0")) {
            // Se o usuário clicar no botão "Cancelar" ou digitar "0"
            JOptionPane.showMessageDialog(null, "Operação cancelada.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); // Fecha o programa
        }

        String[] dadosSeparados = dados.split(",");
        if (dadosSeparados.length < 7) {
            // Caso os dados não tenham informações suficientes
            JOptionPane.showMessageDialog(null, "Dados incompletos. É necessário fornecer pelo menos 7 informações separadas por vírgula.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Sai do método cadastrar
        }

        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);


        }
    }




    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)){
            return true;
        }
        return false;

    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)){
            return true;
        }
        return false;

    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);


    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoCadastro(String opcao) {
        if ("1".equals(opcao)){
            return true;
        }
        return false;
    }


}
