package com.gildedrose;

/**
 * Updater para itens do tipo "Conjured".
 * 
 * EXERCÍCIO 3: Inclusão de Novo Tipo - Itens Conjurados
 * 
 * Regra de negócio:
 * - Perde 2 de qualidade por dia (2x mais rápido que itens normais)
 * - Após passar da data de venda (sellIn < 0), perde 4 de qualidade por dia
 * - Qualidade nunca é negativa
 * 
 * Observação importante:
 * Graças à arquitetura baseada em Strategy Pattern implementada no Exercício 2,
 * foi possível adicionar este novo tipo de item sem modificar nenhuma classe
 * existente, apenas criando uma nova implementação de ItemUpdater.
 * Isso demonstra o Princípio Aberto-Fechado (OCP) em ação.
 */
public class ConjuredItemUpdater implements ItemUpdater {
    
    @Override
    public void update(Item item) {
        // Decrementa qualidade em 2 (2x mais rápido que itens normais)
        if (item.quality > 0) {
            item.quality--;
            if (item.quality > 0) {
                item.quality--;
            }
        }
        
        // Decrementa sellIn
        item.sellIn--;
        
        // Após passar da data de venda, degrada 2x mais rápido (total de 4 por dia)
        if (item.sellIn < 0) {
            if (item.quality > 0) {
                item.quality--;
                if (item.quality > 0) {
                    item.quality--;
                }
            }
        }
    }
}
