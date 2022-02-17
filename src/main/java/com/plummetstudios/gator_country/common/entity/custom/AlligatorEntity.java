package com.plummetstudios.gator_country.common.entity.custom;

import com.plummetstudios.gator_country.common.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

import java.util.Random;

public class AlligatorEntity extends Animal implements IAnimatable {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(AlligatorEntity.class, EntityDataSerializers.INT);
    long timeOfDay = level.getDayTime() % 24000;
    float baskingTime;
    boolean isLandNavigator;
    boolean isAngered;
    public boolean stopMoving;
   private final AnimationFactory factory = new AnimationFactory(this);


    public AlligatorEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }


public void addAdditionalSaveData(CompoundTag compound)
{
    super.addAdditionalSaveData(compound);
    compound.putFloat("baskingTime", this.baskingTime);
}

    public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        this.baskingTime = compound.getFloat("baskingTime");
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationEvent animationEvent) {
        if (this.baskingTime > 0 && !level.isThundering())

        {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.basking_with_mouth_open", true));
        }

        else if (isInWater())
        {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.swimidle", true));
        }
        else if (!(animationEvent.getLimbSwingAmount() > -0.15F && animationEvent.getLimbSwingAmount() < 0.15F)) {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.walk", true));
        }

        else
        {
            animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alligator.idle", true));
        }

        return PlayState.CONTINUE;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.FOLLOW_RANGE, 15).add(Attributes.ARMOR, 8.0D).add(Attributes.ATTACK_DAMAGE, 10.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.4F).add(Attributes.MOVEMENT_SPEED, 2.25F);
    }

    @Override
    public void tick() {
        super.tick();
        System.out.println(baskingTime);
        isLandNavigator = !this.isInWater();
        if (timeOfDay == 1000)
        {
            baskingTime = 5000;
        }

        baskingTime--;
    }



    protected void registerGoals() {
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1.0D, 7));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.25D, Ingredient.of(Items.CHICKEN, Items.COOKED_CHICKEN), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
    }
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.CHICKEN || stack.getItem() == Items.COOKED_CHICKEN;
    }
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(isBreedingItem(itemstack)){
            this.setTarget(null);
        }
        return super.mobInteract(player, hand);
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
