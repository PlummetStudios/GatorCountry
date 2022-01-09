package com.plummetstudios.gator_country.common.world.structure.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GatorNestStructure extends Feature<NoneFeatureConfiguration> {
    public GatorNestStructure(Codec<NoneFeatureConfiguration> p_65786_) {
        super(NoneFeatureConfiguration.CODEC);
    }


    public GenerationStep.Decoration getDecorationStage() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159749_) {
        return false;
    }
}
