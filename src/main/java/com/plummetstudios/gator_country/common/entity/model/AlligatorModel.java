package com.plummetstudios.gator_country.common.entity.model;

import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.entity.custom.AlligatorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AlligatorModel extends AnimatedGeoModel<AlligatorEntity> {


    @Override
    public ResourceLocation getModelLocation(AlligatorEntity object) {
        return new ResourceLocation(GatorCountry.MOD_ID, "geo/alligatorentity.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AlligatorEntity object) {
        return new ResourceLocation(GatorCountry.MOD_ID, "textures/entity/alligator.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AlligatorEntity animatable) {
        return new ResourceLocation(GatorCountry.MOD_ID, "animations/alligator.animation.json");
    }
}
