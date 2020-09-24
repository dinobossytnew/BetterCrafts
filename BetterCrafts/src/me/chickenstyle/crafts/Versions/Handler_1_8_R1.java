package me.chickenstyle.crafts.Versions;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.chickenstyle.crafts.NMSHandler;
import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;

public class Handler_1_8_R1 implements NMSHandler {
	
	@Override
	public ItemStack addIDTag(ItemStack item,int id) {
		net.minecraft.server.v1_8_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		itemCompound.setInt("IDTag", id);
		nmsItem.setTag(itemCompound);
		return CraftItemStack.asBukkitCopy(nmsItem);
	}
	
	@Override
	public boolean hasIDTag(ItemStack item) {
		net.minecraft.server.v1_8_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		return itemCompound.hasKey("IDTag");
	}

	
	
	@Override
	public int getID(ItemStack item) {
		net.minecraft.server.v1_8_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		return itemCompound.getInt("IDTag");
	}
	
	@Override
	public void playParticles(Location loc, String particle, int amount) {
		PacketPlayOutWorldParticles packet
        = new PacketPlayOutWorldParticles(EnumParticle.valueOf(particle.toString()),
        false,
        (float) loc.getX(),
        (float) loc.getY(),
        (float) loc.getZ(),
        0,
        0,
        0,
        1,
        amount);
		for (Player player:loc.getWorld().getPlayers()) {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
		
	}
	
}
