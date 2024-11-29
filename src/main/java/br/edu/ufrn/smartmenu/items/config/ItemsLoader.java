package br.edu.ufrn.smartmenu.items.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.items.models.Category;
import br.edu.ufrn.smartmenu.items.repositories.ItemRepository;
import br.edu.ufrn.smartmenu.items.repositories.CategoryRepository;

@Component
public class ItemsLoader implements CommandLineRunner{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {

            Category entradas = new Category("Entradas");
            Category saladas = new Category("Saladas");
            Category massas = new Category("Massas");
            Category carnes = new Category("Carnes e Aves");
            Category peixes = new Category("Peixes e Frutos do Mar");
            Category hamb = new Category("Hambúrgueres e Sanduíches");
            Category pizza = new Category("Pizzas");
            Category sobremesas = new Category("Sobremesas");
            Category drinks = new Category("Drinks");
            Category bebidas = new Category("Bebidas Não Alcoólicas");
            Category acompanhamentos = new Category("Acompanhamentos");


            categoryRepository.saveAll(List.of(entradas, saladas, massas, carnes, peixes, hamb, pizza, sobremesas, drinks, bebidas, acompanhamentos));


            Item bruschetta = new Item("Bruschetta Clássica", "Fatias de pão italiano torrado, cobertas com tomate, manjericão e azeite.", 18.0, entradas);
            Item sopa = new Item("Sopa do Dia", "Sopa fresca preparada diariamente com ingredientes sazonais.", 15.0, entradas);
            Item carpaccio = new Item("Carpaccio de Carne", "Finas fatias de carne bovina com azeite, alcaparras e parmesão ralado.", 35.0, entradas);
            Item bolinho = new Item("Bolinho de Bacalhau", "Bolinhos crocantes feitos com bacalhau desfiado e temperos.", 25.0, entradas);
            Item cesta = new Item("Cesta de Pães Artesanais", "Pães variados servidos com manteiga de ervas e azeite.", 20.0, entradas);
            Item salada_caesar = new Item("Salada Caesar", "Folhas de alface, croutons, queijo parmesão e molho Caesar.", 28.0, saladas);
            Item salada_tropical = new Item("Salada Tropical", "Mix de folhas verdes, frutas da estação, nozes e molho de laranja.", 30.0, saladas);
            Item salada_caprese = new Item("Salada Caprese", "Tomates frescos, mozzarella de búfala, manjericão e azeite de oliva.", 32.0, saladas);
            Item salada_quinoa = new Item("Salada de Quinoa", "Quinoa com legumes frescos, grão-de-bico e molho cítrico.", 34.0, saladas);
            Item fettuccine = new Item("Fettuccine Alfredo", "Massa ao molho cremoso de parmesão e manteiga.", 42.0, massas);
            Item ravioli = new Item("Ravioli de Ricota e Espinafre", "Massa recheada com ricota e espinafre, acompanhada de molho de tomate caseiro.", 44.0, massas);
            Item espaguete = new Item("Espaguete à Bolonhesa", "Massa tradicional com molho de carne moída e tomates frescos.", 38.0, massas);
            Item penne = new Item("Penne ao Pesto", "Massa ao molho pesto de manjericão e nozes.", 36.0, massas);
            Item canelone = new Item("Canelone de Frango e Catupiry", "Massa recheada com frango desfiado e catupiry, ao molho branco.", 45.0, massas);
            Item file = new Item("Filé Mignon ao Molho Madeira", "Filé mignon grelhado servido com molho madeira, purê de batatas e legumes salteados.", 58.0, carnes);
            Item frango = new Item("Frango Grelhado com Ervas", "Peito de frango temperado com ervas finas, servido com arroz e salada.", 38.0, carnes);
            Item costelinha = new Item("Costelinha BBQ", "Costela de porco assada ao molho barbecue, acompanhada de batatas rústicas.", 55.0, carnes);
            Item picanha = new Item("Picanha na Chapa", "Picanha fatiada com farofa e vinagrete.", 75.0, carnes);
            Item salmao = new Item("Salmão Grelhado", "Filé de salmão grelhado com molho de maracujá, arroz e legumes.", 60.0, peixes);
            Item moqueca = new Item("Moqueca de Camarão", "Camarões cozidos em leite de coco, azeite de dendê, cebola e pimentões.", 75.0, peixes);
            Item risoto = new Item("Risoto de Frutos do Mar", "Arroz arbóreo com camarões, lulas e mexilhões.", 68.0, peixes);
            Item bacalhau = new Item("Bacalhau à Brás", "Bacalhau desfiado com batata palha, ovos e cebola.", 70.0, peixes);
            Item cheeseburger = new Item("Cheeseburger Clássico", "Pão artesanal, carne bovina, queijo cheddar, alface, tomate e maionese.", 32.0, hamb);
            Item hamburger = new Item("Hambúrguer Vegetariano", "Hambúrguer de grão-de-bico com molho de iogurte e salada.", 30.0, hamb);
            Item sanduiche = new Item("Sanduíche de Frango Crocante", "Frango empanado, maionese especial, alface e picles no pão brioche.", 35.0, hamb);
            Item marguerita = new Item("Margherita", "Molho de tomate, mozzarella, manjericão e azeite de oliva.", 42.0, pizza);
            Item pepperoni = new Item("Pepperoni", "Molho de tomate, mozzarella e fatias de pepperoni.", 48.0, pizza);
            Item quatro_queijos = new Item("Quatro Queijos", "Molho de tomate, mozzarella, gorgonzola, parmesão e catupiry.", 50.0, pizza);
            Item petit_gateau = new Item("Petit Gâteau", "Bolinho de chocolate com recheio cremoso, servido com sorvete de baunilha.", 20.0, sobremesas);
            Item cheesecake = new Item("Cheesecake de Frutas Vermelhas", "Cheesecake cremoso com calda de frutas vermelhas.", 22.0, sobremesas);
            Item mousse = new Item("Mousse de Chocolate", "Sobremesa aerada de chocolate meio amargo.", 18.0, sobremesas);
            Item mojito = new Item("Mojito", "Rum branco, hortelã fresca, açúcar, limão e água com gás.", 22.0, drinks);
            Item margarita = new Item("Margarita", "Tequila, licor de laranja, suco de limão e gelo, servido na taça com borda de sal.", 25.0, drinks);
            Item pina = new Item("Piña Colada", "Rum, leite de coco e suco de abacaxi batidos com gelo.", 28.0, drinks);
            Item aperol = new Item("Aperol Spritz", "Aperol, espumante, água com gás e fatia de laranja.", 30.0, drinks);
            Item gin = new Item("Gin Tônica", "Gin, água tônica, limão e especiarias", 28.0, drinks);
            Item caipirinha = new Item("Caipirinha de Limão", "Cachaça, limão, açúcar e gelo.", 18.0, drinks);
            Item suco_naturao = new Item("Suco Natural", "Suco feito na hora com frutas frescas.", 10.0, bebidas);
            Item refrigerante = new Item("Refrigerante", "Escolha entre cola, guaraná ou limão.", 8.0, bebidas);
            Item agua_gas = new Item("Água com Gás", "Garrafa de água mineral com gás.", 6.0, bebidas);
            Item batata = new Item("Batatas Fritas", "Crocantes e servidas com molho especial.", 20.0, acompanhamentos);
            Item arroz_amendoas = new Item("Arroz com Amêndoas", "Arroz branco misturado com amêndoas laminadas e douradas.", 18.0, acompanhamentos);


            itemRepository.saveAll(List.of(bruschetta, sopa, carpaccio, bolinho, cesta, salada_caesar, salada_tropical, salada_caprese, salada_quinoa, fettuccine, ravioli, espaguete, penne, canelone, file, frango, costelinha, picanha, salmao, moqueca, risoto, bacalhau, cheeseburger, hamburger, sanduiche, marguerita, pepperoni, quatro_queijos, petit_gateau, cheesecake, mousse, mojito, margarita, pina, aperol, gin, caipirinha, suco_naturao, refrigerante, agua_gas, batata, arroz_amendoas));
        }
    }

}
