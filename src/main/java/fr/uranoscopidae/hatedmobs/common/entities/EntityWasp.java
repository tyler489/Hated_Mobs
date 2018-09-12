package fr.uranoscopidae.hatedmobs.common.entities;

import fr.uranoscopidae.hatedmobs.common.items.ItemSwatter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityWasp extends EntityMob
{
    public EntityWasp(World worldIn)
    {
        super(worldIn);
        setSize(0.5f, 0.5f);
    }

    @Override
    protected PathNavigate createNavigator(World worldIn)
    {
        PathNavigateFlying fly = new PathNavigateFlying(this, worldIn);
        fly.setCanEnterDoors(true);
        fly.setCanFloat(true);
        return fly;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAIWanderAvoidWaterFlying(this, 0.30));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1, false));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source)
    {
        Entity attacker = source.getTrueSource();

        if(attacker instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)attacker;
            ItemStack item = player.getHeldItemMainhand();

            return !(item.getItem() instanceof ItemSwatter);
        }
        return super.isEntityInvulnerable(source);
    }

    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4D);
    }
}