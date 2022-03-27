package com.plummetstudios.gator_country.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties SLAB_OF_GATOR = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0), 0.5F).meat().build();
}
