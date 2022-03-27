package com.plummetstudios.gator_country.common.entity.custom;

import com.mojang.datafixers.TypeRewriteRule;
import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.ModEntities;
import com.plummetstudios.gator_country.common.ModSounds;
import com.plummetstudios.gator_country.common.block.custom.GatorEggBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

import java.util.List;
import java.util.Random;

public class AlligatorEntity extends Animal implements IAnimatable {
    private static final EntityDataAccessor<Integer> TEST = SynchedEntityData.defineId(AlligatorEntity.class, EntityDataSerializers.INT);
    long timeOfDay = level.getDayTime() % 24000;
    float baskingTime;
    boolean isLandNavigator;
    boolean isAngered;
    boolean isBaby;
    boolean isSwampy;
    int Variant;
    float Wellness;
    public boolean stopMoving;
    public static final ResourceLocation ALLIGATOR_STRESSED_LOOT = new ResourceLocation("gatorcountry", "entities/alligator_stressed");
    Random rand = new Random();

    private final AnimationFactory factory = new AnimationFactory(this);


    public AlligatorEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
        setGatorTexture();
        this.moveControl = new MoveHelperController(this);
        this.isAngered = false;
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putFloat("baskingTime", this.baskingTime);
        compound.putFloat("Variant", this.Variant);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.baskingTime = compound.getFloat("baskingTime");
        this.Variant = compound.getInt("Variant");
    }
    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        return new GroundAndSwimmingNavigator(this, level);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    public void setGatorTexture() {
        float changeVariant = this.getRandom().nextFloat();

        if (changeVariant >= 0.35) {
            if (changeVariant >= 0.98) {
                Variant = 2;
            } else {
                Variant = 0;
            }
        } else if (changeVariant <= 0.35) {
            Variant = 1;
        }
    }

    public boolean isSwampy() {
        String name = ChatFormatting.stripFormatting(this.getName().getString());
        return name != null && name.toLowerCase().contains("swampy");
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationEvent animationEvent) {
        if (this.baskingTime > 0 && !level.isThundering()) {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.basking_with_mouth_open", true));
        } else if (isInWater()) {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.swimidle", true));
        } else if (!(animationEvent.getLimbSwingAmount() > -0.15F && animationEvent.getLimbSwingAmount() < 0.15F)) {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.walk", true));
        } else {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.idle", true));
        }

        return PlayState.CONTINUE;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.FOLLOW_RANGE, 15).add(Attributes.ARMOR, 8.0D).add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.4F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Override
    public void tick() {
        super.tick();
        Biome currentBiome = level.getBiome(this.blockPosition());
        if (currentBiome.coldEnoughToSnow(this.blockPosition())) {
            this.isAngered = true;
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 5));
        }

        System.out.println(isAngered);
        baskingTime--;
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        isSwampy = s != null && s.toLowerCase().contains("swampy");
    }

    @Nullable
    protected ResourceLocation getDefaultLootTable() {
        return this.isAngered ? ALLIGATOR_STRESSED_LOOT : super.getDefaultLootTable();
    }

    public int gatorTexture() {


        return this.Variant;
    }


    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ALLIGATOR_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.ALLIGATOR_HURT.get();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, Ingredient.of(Items.SEAGRASS), false));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D, 10) {
            @Override
            public boolean canUse() {
                return !this.mob.isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1.0D, 1) {
            @Override
            public boolean canUse() {
                return super.canUse() && isInWater();
            }
        });

    }


    static class MoveHelperController extends MoveControl {
        private final AlligatorEntity gator;

        MoveHelperController(AlligatorEntity gator) {
            super(gator);
            this.gator = gator;
        }

        private void updateSpeed() {
            if (this.gator.isInWater()) {
                this.gator.setDeltaMovement(this.gator.getDeltaMovement().add(10.0D, 0.005D, 10.0D));

                if (this.gator.isBaby()) {
                    this.gator.setSpeed(Math.max(this.gator.getSpeed() / 3.0F, 0.06F));
                }
            } else if (this.gator.onGround) {
                this.gator.setSpeed(Math.max(this.gator.getSpeed(), 0.06F));
            }
        }
    }


    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob p_146744_) {
        return ModEntities.ALLIGATOR.get().create(serverLevel);


    }
}
