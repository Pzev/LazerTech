package com.pzev.lazertech.item;

import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemSword;

public class ItemElectricSpear extends ItemLT
{
    public ItemElectricSpear()
    {
        super();
        this.setUnlocalizedName("electricSpear");
        this.maxStackSize = 1;
        this.setMaxDamage(1000);
    }


    private float ATKDamage = 8.0F;
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.ATKDamage, 0));
        return multimap;
    }
}
