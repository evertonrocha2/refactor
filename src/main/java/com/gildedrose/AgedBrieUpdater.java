package com.gildedrose;

/**
 * Updater para itens do tipo "Aged Brie".
 * 
 * Regra de negócio:
 * - Aumenta qualidade com o tempo (contrário dos itens normais)
 * - Qualidade máxima é 50
 * - Após passar da data de venda, aumenta qualidade 2x mais rápido
 */
public class AgedBrieUpdater implements ItemUpdater {
    
    @Override
    public void update(Item item) {
        // Aumenta qualidade (máximo 50)
        if (item.quality < 50) {
            item.quality++;
        }
        
        // Decrementa sellIn
        item.sellIn--;
        
        // Após passar da data de venda, aumenta qualidade ainda mais
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality++;
        }
    }
}
