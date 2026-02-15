package com.gildedrose;

/**
 * Updater para itens do tipo "Backstage passes to a TAFKAL80ETC concert".
 * 
 * Regra de negócio:
 * - Aumenta qualidade conforme se aproxima da data do show
 * - Mais de 10 dias: +1 qualidade por dia
 * - 6-10 dias: +2 qualidade por dia
 * - 1-5 dias: +3 qualidade por dia
 * - Após o show (sellIn < 0): qualidade cai para 0
 * - Qualidade máxima é 50
 */
public class BackstagePassUpdater implements ItemUpdater {
    
    @Override
    public void update(Item item) {
        // Aumenta qualidade baseado em quanto tempo falta para o show
        if (item.quality < 50) {
            item.quality++;
            
            // Bônus adicional quando faltam 10 dias ou menos
            if (item.sellIn < 11 && item.quality < 50) {
                item.quality++;
            }
            
            // Bônus adicional quando faltam 5 dias ou menos
            if (item.sellIn < 6 && item.quality < 50) {
                item.quality++;
            }
        }
        
        // Decrementa sellIn
        item.sellIn--;
        
        // Após o show, qualidade zera
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}
