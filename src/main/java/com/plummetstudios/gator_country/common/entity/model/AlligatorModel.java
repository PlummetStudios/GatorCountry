package com.plummetstudios.gator_country.common.entity.model;

import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.entity.custom.AlligatorEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class AlligatorModel extends AnimatedGeoModel<AlligatorEntity> {

    private static final ResourceLocation TEXTURE_ORIGINAL = new ResourceLocation("gatorcountry:textures/entity/alligator.png");
    private static final ResourceLocation TEXTURE_SWAMPY = new ResourceLocation("gatorcountry:textures/entity/alligator_swampy.png");
    private static final ResourceLocation TEXTURE_BLACK = new ResourceLocation("gatorcountry:textures/entity/alligator_dark.png");
    private static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation("gatorcountry:textures/entity/alligator_albino.png");


    public AlligatorModel() {
    }


    @Override
    public ResourceLocation getModelLocation(AlligatorEntity object) {
        return new ResourceLocation(GatorCountry.MOD_ID, "geo/alligatorentity.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AlligatorEntity object) {
        if (object.isSwampy()) {
            return TEXTURE_SWAMPY;
        }

        else {
            switch (object.gatorTexture()) {
                case 1:
                    return TEXTURE_BLACK;

                case 2:
                    return TEXTURE_ALBINO;

                default:
                    return TEXTURE_ORIGINAL;
            }
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AlligatorEntity animatable) {
        return new ResourceLocation(GatorCountry.MOD_ID, "animations/alligator.animation.json");
    }

    public void setLivingAnimations(AlligatorEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");


        EntityModelData extraData = (EntityModelData)customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * 0.017453292F);
        head.setRotationY(extraData.netHeadYaw * 0.017453292F);
            if(entity.isBaby())
        {
            head.setScaleY(1.7F);
            head.setScaleX(1.7F);
            head.setScaleZ(1.7F);}
    }
}
