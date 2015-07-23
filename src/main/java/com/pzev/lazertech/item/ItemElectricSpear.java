package com.pzev.lazertech.item;

import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;

public class ItemElectricSpear extends ItemLT
{
    public ItemElectricSpear()
    {
        super();
        this.setUnlocalizedName("electricSpear");
        this.maxStackSize = 1;
        this.setMaxDamage(1000);
        this.isDamageable();
    }

    // Causes item to take 1 damage every time it hits an entity
    @Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        p_77644_1_.damageItem(1, p_77644_3_);
        return true;
    }

    // Sets the attack damage that it does, 8
    
    private float ATKDamage = 8.0F;
    @Override
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.ATKDamage, 0));
        return multimap;
    }
}
