package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

/**
 * GILDED ROSE - REFATORAÇÃO
 *
 * Objetivo:
 * Refatorar o método updateQuality, separando regras por tipo de item e
 * deixando o código mais fácil de manter e estender.
 *
 * Evolução:
 * 1) Extração de métodos
 *    - Separação inicial das regras de Aged Brie, Backstage passes, Sulfuras
 *      e itens comuns em métodos dedicados.
 *
 * 2) Strategy por tipo de item
 *    - Interface ItemUpdater (update(Item)).
 *    - Implementações: AgedBrieUpdater, BackstagePassUpdater, SulfurasUpdater,
 *      DefaultUpdater.
 *    - Mapeamento nome -> updater via Map.
 *
 * 3) Novo tipo (Conjured)
 *    - Inclusão via ConjuredItemUpdater e registro no Map, sem alterar lógica existente.
 *
 * 4) Testes
 *    - Testes JUnit cobrindo os cenários principais para garantir comportamento.
 *
 * Avaliação (SOLID):
 * - OCP: novos itens entram criando um novo ItemUpdater e registrando no Map.
 * - SRP: cada updater concentra apenas as regras do seu tipo; GildedRose orquestra.
 * - LSP: qualquer ItemUpdater pode substituir outro no contrato update(Item)
 *   mantendo as invariantes de qualidade (0..50, exceto Sulfuras) e atualização esperada.
 * - ISP: interface pequena, com um único método.
 * - DIP: GildedRose depende da abstração ItemUpdater, não de classes concretas.
 */

class GildedRose {
    Item[] items;
    private Map<String, ItemUpdater> updaters;

    public GildedRose(Item[] items) {
        this.items = items;
        initializeUpdaters();
    }

    /**
     * Inicializa o mapa de updaters para cada tipo de item conhecido.
     */
    private void initializeUpdaters() {
        updaters = new HashMap<>();
        updaters.put("Aged Brie", new AgedBrieUpdater());
        updaters.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdater());
        updaters.put("Sulfuras, Hand of Ragnaros", new SulfurasUpdater());
        // Exercício 3: Adicionado suporte para itens Conjurados
        updaters.put("Conjured", new ConjuredItemUpdater());
    }

    /**
     * Atualiza a qualidade de todos os itens.
     * Delega a atualização para o ItemUpdater apropriado.
     */
    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = getUpdaterForItem(item);
            updater.update(item);
        }
    }

    /**
     * Retorna o updater apropriado para o item.
     * Se não houver updater específico, retorna o DefaultUpdater.
     * 
     * Suporta itens "Conjured" que começam com o prefixo "Conjured".
     */
    private ItemUpdater getUpdaterForItem(Item item) {
        // Verifica se o item começa com "Conjured"
        if (item.name.startsWith("Conjured")) {
            return updaters.get("Conjured");
        }
        return updaters.getOrDefault(item.name, new DefaultUpdater());
    }
}
