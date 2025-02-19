import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Pokemon {
    String nome, tipo;
    int nivel;

    public Pokemon(String nome, String tipo, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + ", Nível: " + nivel + ")";
    }
}

public class GerenciadorPokemon {
    private ArrayList<Pokemon> pokemonsDisponiveis = new ArrayList<>();
    private ArrayList<Pokemon> mochila = new ArrayList<>();
    private DefaultListModel<Pokemon> modeloListaDisponiveis = new DefaultListModel<>();
    private DefaultListModel<Pokemon> modeloListaMochila = new DefaultListModel<>();
    private JList<Pokemon> listaDisponiveis, listaMochila;

    public GerenciadorPokemon() {
        // Inicializando Pokémons Disponíveis
        pokemonsDisponiveis.add(new Pokemon("Pikachu", "Elétrico", 10));
        pokemonsDisponiveis.add(new Pokemon("Charmander", "Fogo", 12));
        pokemonsDisponiveis.add(new Pokemon("Bulbasaur", "Planta", 8));
        pokemonsDisponiveis.add(new Pokemon("Squirtle", "Água", 11));
        pokemonsDisponiveis.add(new Pokemon("Eevee", "Normal", 15));
        pokemonsDisponiveis.add(new Pokemon("Jigglypuff", "Fada", 7));

        for (Pokemon p : pokemonsDisponiveis) {
            modeloListaDisponiveis.addElement(p);
        }

        criarInterface();
    }

    private void criarInterface() {
        JFrame frame = new JFrame("Gerenciador de Pokémons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar à Mochila");
        JButton btnVisualizar = new JButton("Visualizar Mochila");
        JButton btnRemover = new JButton("Remover da Mochila");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnVisualizar);
        painelBotoes.add(btnRemover);
        frame.add(painelBotoes, BorderLayout.NORTH);

        listaDisponiveis = new JList<>(modeloListaDisponiveis);
        listaMochila = new JList<>(modeloListaMochila);

        frame.add(new JScrollPane(listaDisponiveis), BorderLayout.WEST);
        frame.add(new JScrollPane(listaMochila), BorderLayout.EAST);

        frame.setVisible(true);

        btnAdicionar.addActionListener(e -> adicionarPokemon());
        btnVisualizar.addActionListener(e -> visualizarMochila());
        btnRemover.addActionListener(e -> removerPokemon());
    }

    private void adicionarPokemon() {
        if (mochila.size() >= 6) {
            JOptionPane.showMessageDialog(null, "A mochila já está cheia (máximo 6 Pokémons).", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Pokemon selecionado = listaDisponiveis.getSelectedValue();
        if (selecionado != null) {
            mochila.add(selecionado);
            modeloListaMochila.addElement(selecionado);
            modeloListaDisponiveis.removeElement(selecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Pokémon para adicionar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void visualizarMochila() {
        JOptionPane.showMessageDialog(null, new JScrollPane(listaMochila), "Pokémons na Mochila", JOptionPane.INFORMATION_MESSAGE);
    }

    private void removerPokemon() {
        Pokemon selecionado = listaMochila.getSelectedValue();
        if (selecionado != null) {
            mochila.remove(selecionado);
            modeloListaMochila.removeElement(selecionado);
            modeloListaDisponiveis.addElement(selecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Pokémon para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GerenciadorPokemon::new);
    }
}

    
