package fr.uranoscopidae.hatedmobs.common.blocks;

import fr.uranoscopidae.hatedmobs.HatedMobs;
import fr.uranoscopidae.hatedmobs.common.ConfigurationHandler;
import fr.uranoscopidae.hatedmobs.common.entities.EntityWasp;
import fr.uranoscopidae.hatedmobs.common.tileentities.TileEntityWaspNest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockWaspNest extends Block
{

    public BlockWaspNest()
    {
        super(Material.ROCK);
        this.setCreativeTab(HatedMobs.TAB);
        setRegistryName(new ResourceLocation(HatedMobs.MODID, "wasp_nest"));
        setUnlocalizedName("wasp_nest");
        blockHardness(1.0F);
    }

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, BlockState state)
    {
        return new TileEntityWaspNest();
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, BlockState state)
    {
        if(ConfigurationHandler.MOB_TOGGLE.wasp)
        {
            if (!worldIn.isRemote)
            {
                for (int i = 0; i < 6; i++)
                {
                    EntityWasp wasp = new EntityWasp(worldIn);
                    wasp.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
                    worldIn.addEntity(wasp);
                    wasp.spawnExplosionParticle();
                }
            }
        }
    }
}
