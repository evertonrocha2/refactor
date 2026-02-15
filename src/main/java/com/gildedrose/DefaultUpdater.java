package com.gildedrose;

/**
 * Updater padrão para itens normais/comuns.
 * 
 * Regra de negócio:
 * - Perde 1 de qualidade por dia
 * - Após passar da data de venda (sellIn < 0), perde 2 de qualidade por dia
 * - Qualidade nunca é negativa
 */
public class DefaultUpdater implements ItemUpdater {
    
    @Override
    public void update(Item item) {
        // Decrementa qualidade (mínimo 0)
        if (item.quality > 0) {
            item.quality--;
        }
        
        // Decrementa sellIn
        item.sellIn--;
        
        // Após passar da data de venda, degrada 2x mais rápido
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
    }
}
