package com.gildedrose;

/**
 * Updater para itens do tipo "Sulfuras, Hand of Ragnaros".
 * 
 * Regra de negócio:
 * - Item lendário que nunca precisa ser vendido
 * - Nunca diminui qualidade
 * - Não decrementa sellIn
 */
public class SulfurasUpdater implements ItemUpdater {
    
    @Override
    public void update(Item item) {
        // Sulfuras é um item legendário e não sofre alterações
        // Não faz nada - qualidade e sellIn permanecem constantes
    }
}
