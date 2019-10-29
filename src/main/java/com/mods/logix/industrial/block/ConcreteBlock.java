package com.mods.logix.industrial.block;

import com.mods.logix.API.block.LogixObjBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import static com.mods.logix.industrial.LogixIndustrialSolutions.ModID;
import static com.mods.logix.industrial.atlas.LogixIndustrialGroups.DECORATIVES;
import static com.mods.logix.industrial.atlas.LogixIndustrialNames.NAME_BLOCK_CONCRETE;

public class ConcreteBlock extends LogixObjBlock// implements IWaterLoggable
{
    /*public static final BooleanProperty WATERLOGGED;

    static
    {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
    }*/

    //@SuppressWarnings("UnnecessaryBoxing")
    public ConcreteBlock()
    {
        super(ModID, NAME_BLOCK_CONCRETE, DECORATIVES, Material.IRON, MaterialColor.IRON, SoundType.METAL);
        //this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.valueOf(false)));
    }

    /*protected void fillStateContainer(StateContainer.Builder<Block, BlockState> state)
    {
        state.add(WATERLOGGED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockPos pos = context.getPos();
        BlockState blockState = context.getWorld().getBlockState(pos);
        if (blockState.getBlock() == this)
        {
            return blockState.with(WATERLOGGED, false);
        }
        else
        {
            IFluidState fluidState = context.getWorld().getFluidState(pos);
            return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public IFluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean receiveFluid(IWorld world, BlockPos blockPos, BlockState blockState, IFluidState fluidState)
    {
        if (!blockState.get(BlockStateProperties.WATERLOGGED) && fluidState.getFluid() == Fluids.WATER)
        {
            if (!world.isRemote())
            {
                world.setBlockState(blockPos, blockState.with(BlockStateProperties.WATERLOGGED, true), 3);
                world.getPendingFluidTicks().scheduleTick(blockPos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean canContainFluid(IBlockReader blockReader, BlockPos pos, BlockState state, Fluid fluid)
    {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing.getOpposite() == stateIn.get(FACING) && !stateIn.isValidPosition(worldIn, currentPos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (stateIn.get(WATERLOGGED)) {
                worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            }

            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }*/
}