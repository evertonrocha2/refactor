package com.gildedrose;

/**
 * Interface que define o contrato para atualização de itens.
 * 
 * EXERCÍCIO 2: Modularização por Tipo de Item
 * 
 * Esta interface permite que cada tipo de item tenha sua própria
 * implementação de regras de atualização, seguindo o princípio
 * de responsabilidade única (SRP) e o princípio aberto-fechado (OCP).
 */
public interface ItemUpdater {
    /**
     * Atualiza a qualidade e o sellIn de um item.
     * 
     * @param item O item a ser atualizado
     */
    void update(Item item);
}
