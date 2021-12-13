package net.runelite.client.plugins.OSInt;

import lombok.ToString;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.Player;
import net.runelite.api.kit.KitType;
import java.util.*;

@ToString
public class PlayerEquipment {
    private Map<KitType, String> playerEquipmentMap = new HashMap<>();

    public PlayerEquipment(Client client, Player player) {
        Map<KitType, ItemComposition> playerEquipment = null;
        playerEquipment = getPlayerEquipment(client, player);
        for (KitType equip : KitType.values()) {
            if (playerEquipment.get(equip) != null) {
                playerEquipmentMap.put(equip, getEquipment(playerEquipment, equip));
            }
        }
    }

    private Map<KitType, ItemComposition> getPlayerEquipment(Client client, Player player) {
        Map<KitType, ItemComposition> playerEquipment = new HashMap<>();

        for (KitType kitType : KitType.values()) {
            int itemId = Objects.requireNonNull(player.getPlayerComposition()).getEquipmentId(kitType);
            if (itemId != -1) {
                ItemComposition itemComposition = client.getItemDefinition(itemId);
                playerEquipment.put(kitType, itemComposition);
            }
        }
        return playerEquipment;
    }

    private String getEquipment(Map<KitType, ItemComposition> playerEquipment, KitType kitType) {
        if (playerEquipment != null && kitType != null && playerEquipment.get(kitType) != null) {
            return playerEquipment.get(kitType).getName();
        } else {
            return "";
        }
    }
	