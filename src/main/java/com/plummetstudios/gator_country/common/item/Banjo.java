package com.plummetstudios.gator_country.common.item;

import com.plummetstudios.gator_country.client.BanjoRenderProperties;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraft.util.*;


import java.util.Random;
import java.util.function.Consumer;

public class Banjo extends Item {
    public Banjo(Properties p_41383_) {
        super(p_41383_);
    }
Random rand = new Random();
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand Hand) {
        worldIn.playSound(null, playerIn.blockPosition(), SoundEvents.NOTE_BLOCK_BANJO, SoundSource.PLAYERS, 1f,0.5F+rand.nextFloat());
        return super.use(worldIn, playerIn, Hand);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
    pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150));
        pTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40));
        pAttacker.level.playSound(null, pAttacker.blockPosition(), SoundEvents.NOTE_BLOCK_BANJO, SoundSource.PLAYERS, 1.5f,0.5F+rand.nextFloat());
        pAttacker.level.playSound(null, pAttacker.blockPosition(), SoundEvents.NOTE_BLOCK_BANJO, SoundSource.PLAYERS, 1.5f,0.5F+rand.nextFloat());
        pAttacker.level.playSound(null, pAttacker.blockPosition(), SoundEvents.NOTE_BLOCK_BANJO, SoundSource.PLAYERS, 1.5f,0.5F+rand.nextFloat());
        pAttacker.level.playSound(null, pAttacker.blockPosition(), SoundEvents.NOTE_BLOCK_BANJO, SoundSource.PLAYERS, 1.5f,0.5F+rand.nextFloat());
        pAttacker.level.playSound(null, pAttacker.blockPosition(), SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, SoundSource.PLAYERS, 0.5f,1F);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {

        return 1;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(BanjoRenderProperties.RENDER_PROPERTIES);

    }
}
