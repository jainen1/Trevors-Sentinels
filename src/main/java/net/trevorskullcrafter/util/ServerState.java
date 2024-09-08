package net.trevorskullcrafter.util;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import net.trevorskullcrafter.TrevorsSentinels;
import org.jetbrains.annotations.NotNull;

public class ServerState extends PersistentState {
    public int worldLevel; boolean keepingTrack;

	public ServerState(int worldLevel, boolean keepingTrack){ this.worldLevel = worldLevel; this.keepingTrack = keepingTrack; }
	public ServerState() { this(1, true); }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("trevorssentinels:worldLevel", worldLevel);
        nbt.putBoolean("trevorssentinels:worldLevelTracking", keepingTrack);
        return nbt;
    }

    private static final PersistentState.Type<ServerState> type = new Type<>(ServerState::new, ServerState::createFromNbt, DataFixTypes.WORLD_GEN_SETTINGS);

    public static ServerState createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup){
        return new ServerState(tag.getInt("trevorssentinels:worldLevel"), tag.getBoolean("trevorssentinels:worldLevelTracking"));
    }

    public static @NotNull ServerState getServerState(MinecraftServer server){
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
        ServerState state = persistentStateManager.getOrCreate(type, TrevorsSentinels.MOD_ID);
        state.markDirty();
        return state;
    }
}
