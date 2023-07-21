package dev.carl.eggwars.item;

import com.google.common.collect.Lists;
import dev.carl.eggwars.util.UtilColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemBuilder {
    private Material material;
    private int amount;
    private String name;
    private Iterable<String> lore;
    private String[] arrLore;
    private String key;
    private Tag tag;
    private boolean skullMeta;

    public ItemStack builder() {
        if (material == null || amount == 0) {
            return null;
        }

        ItemStack is = new ItemStack(material, amount);

        if (name != null || lore != null || arrLore != null) {
            setMeta(is, name, lore, arrLore);
        }

        if (skullMeta) {
            setSkullMeta(is, name);
        }

        if (key != null && tag != null) {
            is = setNbt(is, key, tag);
        }

        return is;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public ItemBuilder setLore(Iterable<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.arrLore = lore;
        return this;
    }


    public ItemBuilder setNbtBase(Tag tag) {
        this.tag = tag;
        return this;
    }

    public ItemBuilder setSkullMeta(boolean skullMeta) {
        this.skullMeta = skullMeta;
        return this;
    }

    public static ItemStack setNbt(ItemStack item, String key, Tag tag) {
        net.minecraft.world.item.ItemStack nmsIs = CraftItemStack.asNMSCopy(item);
        CompoundTag ncIs = getNbt(item);

        if (ncIs == null) {
            return item;
        }

        ncIs.put(key, tag);
        nmsIs.setTag(ncIs);
        item = CraftItemStack.asBukkitCopy(nmsIs);
        return item;
    }

    public static CompoundTag getNbt(ItemStack is) {
        net.minecraft.world.item.ItemStack nmsIs = CraftItemStack.asNMSCopy(is);
        return nmsIs.hasTag() ? nmsIs.getTag() : new CompoundTag();
    }

    private void setSkullMeta(ItemStack head, String name) {
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(Bukkit.getPlayer(name));
        head.setItemMeta(meta);
    }

    private void setMeta(ItemStack item, String name, Iterable<String> lore, String... arrLore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(UtilColor.toColor("&e%s", name));
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(im);
        List<String> desc = lore == null ? (arrLore == null ? new ArrayList<>() : Lists.newArrayList(arrLore)) : Lists.newArrayList(lore);

        if (!desc.isEmpty()) {
            im.setLore(desc.stream().map(UtilColor::toColor).collect(Collectors.toList()));
            item.setItemMeta(im);
        }
    }
}
