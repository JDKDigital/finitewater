package cy.jdkdigital.finitewater;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(FiniteWater.MODID)
public class FiniteWater
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String MODID = "finitewater";

    public static final TagKey<Biome> HAS_INFINITE_WATER = TagKey.create(Registries.BIOME, new ResourceLocation(MODID, "has_infinite_water"));

    public FiniteWater()
    {
        MinecraftForge.EVENT_BUS.addListener(this::createFluidSource);

    }

    private void createFluidSource(BlockEvent.CreateFluidSourceEvent event)
    {
        if (event.getState().getFluidState().is(Fluids.WATER) && !event.getLevel().getBiome(event.getPos()).is(HAS_INFINITE_WATER)) {
            event.setResult(Event.Result.DENY);
        }
    }
}
