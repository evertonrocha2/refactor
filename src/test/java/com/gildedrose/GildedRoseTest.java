package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * EXERCÍCIO 4: Testes Automatizados para Refatoração Segura
 * 
 * Esta classe contém testes abrangentes que garantem que a refatoração
 * não alterou os comportamentos esperados do sistema.
 * 
 * Os testes cobrem:
 * 1. Itens normais
 * 2. Aged Brie
 * 3. Sulfuras
 * 4. Backstage passes
 * 5. Itens Conjurados (novo requisito)
 */
class GildedRoseTest {

    // ========== TESTES DE ITENS NORMAIS ==========
    
    @Test
    @DisplayName("Item normal deve perder 1 de qualidade por dia")
    void normalItem_LosesQualityByOne() {
        Item[] items = new Item[] { new Item("Normal Item", 10, 20) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(19, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    @DisplayName("Item normal perde qualidade 2x mais rápido após a data de venda")
    void normalItem_LosesQualityTwiceAsFastAfterSellDate() {
        Item[] items = new Item[] { new Item("Normal Item", 0, 20) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(18, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    @DisplayName("Qualidade de item nunca é negativa")
    void normalItem_QualityNeverNegative() {
        Item[] items = new Item[] { new Item("Normal Item", 5, 0) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(0, items[0].quality);
    }

    // ========== TESTES DE AGED BRIE ==========

    @Test
    @DisplayName("Aged Brie aumenta qualidade com o tempo")
    void agedBrie_IncreasesQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 20) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(21, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    @DisplayName("Aged Brie aumenta qualidade 2x mais rápido após a data de venda")
    void agedBrie_IncreasesQualityTwiceAsFastAfterSellDate() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 20) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(22, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    @DisplayName("Aged Brie: qualidade nunca passa de 50")
    void agedBrie_QualityNeverAbove50() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(50, items[0].quality);
    }

    @Test
    @DisplayName("Aged Brie: qualidade máxima é 50 mesmo após data de venda")
    void agedBrie_QualityMaxIs50EvenAfterSellDate() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 49) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(50, items[0].quality);
    }

    // ========== TESTES DE SULFURAS ==========

    @Test
    @DisplayName("Sulfuras não sofre alteração na qualidade")
    void sulfuras_NeverChangesQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(80, items[0].quality);
        assertEquals(10, items[0].sellIn);
    }

    @Test
    @DisplayName("Sulfuras não sofre alteração no sellIn")
    void sulfuras_NeverChangesSellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(80, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    // ========== TESTES DE BACKSTAGE PASSES ==========

    @Test
    @DisplayName("Backstage pass aumenta qualidade em 1 quando faltam mais de 10 dias")
    void backstagePass_IncreasesQualityBy1WhenMoreThan10Days() {
        Item[] items = new Item[] { 
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) 
        };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(21, items[0].quality);
        assertEquals(14, items[0].sellIn);
    }

    @Test
    @DisplayName("Backstage pass aumenta qualidade em 2 quando faltam 10 dias ou menos")
    void backstagePass_IncreasesQualityBy2When10DaysOrLess() {
        Item[] items = new Item[] { 
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) 
        };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(22, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    @DisplayName("Backstage pass aumenta qualidade em 3 quando faltam 5 dias ou menos")
    void backstagePass_IncreasesQualityBy3When5DaysOrLess() {
        Item[] items = new Item[] { 
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) 
        };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(23, items[0].quality);
        assertEquals(4, items[0].sellIn);
    }

    @Test
    @DisplayName("Backstage pass: qualidade zera após a data de venda")
    void backstagePass_QualityDropsToZeroAfterConcert() {
        Item[] items = new Item[] { 
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) 
        };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    @DisplayName("Backstage pass: qualidade nunca passa de 50")
    void backstagePass_QualityNeverAbove50() {
        Item[] items = new Item[] { 
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) 
        };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(50, items[0].quality);
    }

    // ========== TESTES DE ITENS CONJURADOS (EXERCÍCIO 3) ==========

    @Test
    @DisplayName("Conjured: perde qualidade 2x mais rápido que itens normais")
    void conjuredItem_LosesQualityTwiceAsFast() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 20) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(18, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    @DisplayName("Conjured: perde qualidade 4x mais rápido após a data de venda")
    void conjuredItem_LosesQualityFourTimesAsFastAfterSellDate() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 20) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(16, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    @DisplayName("Conjured: qualidade nunca é negativa")
    void conjuredItem_QualityNeverNegative() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 5, 1) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(0, items[0].quality);
    }

    @Test
    @DisplayName("Conjured: reconhece qualquer item que comece com 'Conjured'")
    void conjuredItem_RecognizesConjuredPrefix() {
        Item[] items = new Item[] { new Item("Conjured Magic Sword", 10, 20) };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        
        assertEquals(18, items[0].quality);
    }

    // ========== TESTE DE MÚLTIPLOS DIAS ==========

    @Test
    @DisplayName("Testa comportamento ao longo de múltiplos dias")
    void multipleItems_UpdateCorrectlyOverMultipleDays() {
        Item[] items = new Item[] {
            new Item("Normal Item", 5, 10),
            new Item("Aged Brie", 5, 10),
            new Item("Sulfuras, Hand of Ragnaros", 5, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10),
            new Item("Conjured Mana Cake", 5, 10)
        };
        GildedRose app = new GildedRose(items);
        
        // Simula 3 dias
        for (int i = 0; i < 3; i++) {
            app.updateQuality();
        }
        
        // Normal: qualidade 7 (10-1-1-1), sellIn 2 (5-1-1-1)
        assertEquals(7, items[0].quality);
        assertEquals(2, items[0].sellIn);
        
        // Aged Brie: qualidade 13 (10+1+1+1), sellIn 2
        assertEquals(13, items[1].quality);
        assertEquals(2, items[1].sellIn);
        
        // Sulfuras: sem alterações
        assertEquals(80, items[2].quality);
        assertEquals(5, items[2].sellIn);
        
        // Backstage pass: qualidade 19 (10+3+3+3), sellIn 2
        assertEquals(19, items[3].quality);
        assertEquals(2, items[3].sellIn);
        
        // Conjured: qualidade 4 (10-2-2-2), sellIn 2
        assertEquals(4, items[4].quality);
        assertEquals(2, items[4].sellIn);
    }
}

