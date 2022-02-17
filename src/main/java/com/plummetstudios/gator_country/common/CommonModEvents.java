package com.plummetstudios.gator_country.common;

import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.entity.custom.AlligatorEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GatorCountry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.ALLIGATOR.get(), AlligatorEntity.createAttributes().build());
    }
}
