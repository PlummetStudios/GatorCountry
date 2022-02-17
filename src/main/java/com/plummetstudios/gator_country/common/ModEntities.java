package com.plummetstudios.gator_country.common;

import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.entity.custom.AlligatorEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, GatorCountry.MOD_ID);

    public static final RegistryObject<EntityType<AlligatorEntity>> ALLIGATOR = create("alligator", EntityType.Builder.of(AlligatorEntity::new, MobCategory.CREATURE).sized(3f, 1f));
    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(GatorCountry.MOD_ID + "." + name));
    }

    public static void register(IEventBus eventBus) {ENTITY_TYPES.register(eventBus);
    }
}
